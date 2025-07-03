package com.etarruella.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerPositionChangeEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final Location newPosition;

    public PlayerPositionChangeEvent(Player player, Location newPosition) {
        this.player = player;
        this.newPosition = newPosition;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getNewPosition() {
        return newPosition;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
