package com.etarruella.payload;

import java.util.UUID;

public class PlayerHealthChangePayload {

    private String event;
    private UUID playerUUID;
    private double newHealth;
    private double oldHealth;

    public PlayerHealthChangePayload(String event, UUID playerUUID, double oldHealth, double newHealth) {
        this.event = event;
        this.playerUUID = playerUUID;
        this.oldHealth = oldHealth;
        this.newHealth = newHealth;
    }

    public String getEvent() {
        return event;
    }

    public String getPlayerUUID() {
        return playerUUID.toString();
    }

    public double getOldHealth() {
        return oldHealth;
    }

    public double getNewHealth() {
        return newHealth;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public void setOldHealth(double oldHealth) {
        this.oldHealth = oldHealth;
    }

    public void setNewHealth(double newHealth) {
        this.newHealth = newHealth;
    }

}
