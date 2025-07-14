package com.etarruella.payload;

public class PlayerHealthChangePayload {

    private String event;
    private String playerUUID;
    private double newHealth;
    private double oldHealth;

    public PlayerHealthChangePayload(String event, String playerUUID, double oldHealth, double newHealth) {
        this.event = event;
        this.playerUUID = playerUUID;
        this.oldHealth = oldHealth;
        this.newHealth = newHealth;
    }

    public String getEvent() { return event; }
    public String getPlayerUUID() { return playerUUID; }
    public double getOldHealth() { return oldHealth; }
    public double getNewHealth() { return newHealth; }

    public void setEvent(String event) { this.event = event; }
    public void setPlayerUUID(String playerUUID) { this.playerUUID = playerUUID; }
    public void setOldHealth(double oldHealth) { this.oldHealth = oldHealth; }
    public void setNewHealth(double newHealth) { this.newHealth = newHealth; }

}
