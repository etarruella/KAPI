package com.etarruella.network;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerEventMessage {

    public enum Action {
        subscribe,
        unsubscribe
    }

    public enum Event {
        ServerStartEvent,
        ServerStopEvent,
        ServerReloadEvent,
        ServerLoadCompleteEvent,
        ServerTickEvent,
        ServerCommandEvent,
        ServerBroadcastMessageEvent,
        ServerSaveEvent,
        ServerPlayerCountChangeEvent
    }

    private final String type;
    private final Action action;
    private final Event event;

    @JsonCreator
    public ServerEventMessage(
            @JsonProperty("type") String type,
            @JsonProperty("action") Action action,
            @JsonProperty("event") Event event
    ) {
        if (!"ServerEvent".equals(type)) {
            throw new IllegalArgumentException("type must be 'ServerEvent'");
        }
        this.type = type;
        this.action = action;
        this.event = event;
    }

    public String getType() {
        return type;
    }

    public Action getAction() {
        return action;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return "ServerEventMessage{" +
                "type='" + type + '\'' +
                ", action=" + action +
                ", event=" + event +
                '}';
    }
}
