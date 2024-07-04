package org.flotares.tournaments.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/live/{uuid}")
@ApplicationScoped
public class MatchSocket {
    /*
    @Inject
    MatchService matchService;

    private static final ObjectMapper mapper = new ObjectMapper();
    private Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("uuid") String uuid) {
        System.out.println(uuid + " connected!");
        broadcast(uuid + " connected");
        sessions.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("uuid") String uuid) {
        System.out.println(uuid + " diconnected!");
        broadcast(uuid + " disconnected");
        sessions.remove(session.getId());
    }

    @OnMessage
    public void onMessage(String message, @PathParam("uuid") String uuid) throws JsonProcessingException {
        System.out.println(message);
        Action action = mapper.readValue(message, Action.class);
        switch (action.getType()) {
            case "start":
                broadcast("Starting game");
                break;
            case "end":
                broadcast("Ending game");
                break;
            case "goal":
                broadcast("Goal for team: " + action.getTeam());
                break;
            default:
                broadcast("Invalid action!");
                break;
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        sessions.remove(session);
        broadcast("User left on error: " + throwable.getMessage());
    }

    private void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send " + message);
                }
            });
        });
    }*/
}
