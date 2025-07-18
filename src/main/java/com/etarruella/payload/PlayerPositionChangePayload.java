package com.etarruella.payload;

import java.util.UUID;
import org.bukkit.Location;

public class PlayerPositionChangePayload {

    private String event;
    private UUID playerUUID;
    private Location newLocation;

    public PlayerPositionChangePayload(String event, UUID playerUUID, Location newLocation) {
        this.event = event;
        this.playerUUID = playerUUID;
        this.newLocation = newLocation;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public Location getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(Location newLocation) {
        this.newLocation = newLocation;
    }
}
