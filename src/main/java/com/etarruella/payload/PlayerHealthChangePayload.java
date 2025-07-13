package com.etarruella.payload;

public class PlayerHealthChangePayload {
    private String event;
    private String playerUUID;
    private double newHealth;

    public PlayerHealthChangePayload(String event, String playerUUID, double newHealth) {
        this.event = event;
        this.playerUUID = playerUUID;
        this.newHealth = newHealth;
    }

    public String getEvent() { return event; }
    public String getPlayerUUID() { return playerUUID; }
    public double getNewHealth() { return newHealth; }

    public void setEvent(String event) { this.event = event; }
    public void setPlayerUUID(String playerUUID) { this.playerUUID = playerUUID; }
    public void setNewHealth(double newHealth) { this.newHealth = newHealth; }
}
