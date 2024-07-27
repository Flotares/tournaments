package org.flotares.tournaments.resource;

import jakarta.activation.MimetypesFileTypeMap;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.flotares.tournaments.exception.ServiceException;
import org.flotares.tournaments.model.Badge;
import org.flotares.tournaments.model.Club;
import org.flotares.tournaments.service.BadgeService;
import org.flotares.tournaments.service.ClubService;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Path("clubs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@Tag(name = "Club Resource", description = "Endpoint for clubs")
public class ClubResource {
    private static final Logger LOGGER = Logger.getLogger(ClubResource.class);

    @Inject
    ClubService clubService;
    @Inject
    BadgeService badgeService;

    @GET
    public List<Club> getAllClubs() {
        LOGGER.info("getAllClubs");

        return clubService.getAll();
    }

    @GET
    @Path("{id}")
    public Optional<Club> getClubById(@PathParam("id") long id) {
        LOGGER.info(String.format("getClubById{%d}", id));

        return clubService.getById(id);
    }

    @POST
    public Club createClub(Club c) {
        LOGGER.info(String.format("createClub{%s}", c.toString()));

        Club rc = clubService.create(c);
        return rc;
    }

    @PUT
    @Path("{id}")
    public Response updateClub(@PathParam("id") long id, Club c) {
        LOGGER.info(String.format("updateClub{%d,%s}", id, c.toString()));

        Optional<Club> toUpdate = clubService.getById(id);

        if (toUpdate.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Club rc = clubService.update(id, c);

        return Response.ok(rc).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteClub(@PathParam("id") long id) {
        LOGGER.info(String.format("deleteClub{%d}", id));

        Optional<Club> toDelete = clubService.getById(id);

        if (toDelete.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        clubService.delete(toDelete.get());
        return Response.ok().build();
    }

    @POST
    @Path("{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addBadge(@PathParam("id") long id, @MultipartForm MultipartInput input) {
        LOGGER.info(String.format("addBadge{%d}", id));

        Optional<Club> toUpdateOpt = clubService.getById(id);

        if (toUpdateOpt.isEmpty()) {
            throw new ServiceException(Response.Status.NOT_FOUND, "Club not found!");
        }

        Club toUpdate = toUpdateOpt.get();

        if (input.getParts().size() > 1) {
            throw new ServiceException(Response.Status.BAD_REQUEST, "Multiple files are not allowed!");
        }

        //delete existing badge
        if (toUpdate.getBadge() != null) {
            badgeService.delete(toUpdate.getBadge());
            toUpdate.setBadge(null);
            clubService.update(toUpdate.getId(), toUpdate);
        }

        InputPart part = input.getParts().get(0);

        byte[] data;
        try {
            data = IOUtils.toByteArray(part.getBody());
        } catch (IOException e) {
            throw new ServiceException(Response.Status.BAD_REQUEST, "Could not read file!");
        }

        Badge badge = new Badge();
        badge.setData(data);
        Badge reBadge = badgeService.create(badge);

        toUpdate.setBadge(reBadge);
        clubService.update(toUpdate.getId(), toUpdate);

        return Response.ok(reBadge).build();
    }

    @GET
    @Path("{id}/badge")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Image found",
                    content = @Content(
                            mediaType = "application/octet-stream",
                            schema = @Schema(type = SchemaType.STRING, format = "binary")
                    )
            ),
            @APIResponse(responseCode = "404", description = "Image not found")
    })
    public Response getBadgeFromClub(@PathParam("id") long id) {
        LOGGER.info(String.format("getBadgeFromClub{%d}", id));

        Optional<Club> toUpdateOpt = clubService.getById(id);

        if (toUpdateOpt.isEmpty()) {
            throw new ServiceException(Response.Status.NOT_FOUND, "Club not found!");
        }

        Club toUpdate = toUpdateOpt.get();

        if (toUpdate.getBadge() == null) {
            throw new ServiceException(Response.Status.NOT_FOUND, "Club has no badge!");
        }

        byte[] data = toUpdate.getBadge().getData();
        return Response.ok(data)
                .header("Content-Type", "image/*")
                .build();
    }
}
