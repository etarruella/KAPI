package com.etarruella.payload;

import java.util.UUID;
import org.bukkit.Location;

public class PlayerPositionChangePayload {

    private String event;
    private UUID playerUUID;
    private int x, y, z;

    public PlayerPositionChangePayload(String event, UUID playerUUID, Location newLocation) {
        this.event = event;
        this.playerUUID = playerUUID;
        this.x = newLocation.getBlockX();
        this.y = newLocation.getBlockY();
        this.z = newLocation.getBlockZ();
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

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setZ(int z) {this.z = z;}

    public int getX() {return x;}
    public int getY() {return y;}
    public int getZ() {return z;}
}
