package com.etarruella.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerHealthChangeEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final double oldHealth;
    private final double healthChange; // positivo o negativo
    private final double newHealth;

    public PlayerHealthChangeEvent(Player player, double oldHealth, double healthChange, double newHealth) {
        this.player = player;
        this.oldHealth = oldHealth;
        this.healthChange = healthChange;
        this.newHealth = newHealth;
    }

    public Player getPlayer() {
        return player;
    }

    public double getOldHealth() {
        return oldHealth;
    }

    public double getHealthChange() {
        return healthChange;
    }

    public double getNewHealth() {
        return newHealth;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
