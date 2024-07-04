package org.flotares.tournaments.websocket;

import lombok.Data;

@Data
public class Action {
    private String type;
    private String team;
}
