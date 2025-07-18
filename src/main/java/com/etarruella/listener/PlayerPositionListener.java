package com.etarruella.listener;

import com.etarruella.KAPI;
import com.etarruella.event.PlayerPositionChangeEvent;
import com.etarruella.payload.PlayerPositionChangePayload;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerPositionListener implements Listener {

    private boolean hasChangedBlock(Location from, Location to) {
        return from.getBlockX() != to.getBlockX() ||
                from.getBlockY() != to.getBlockY() ||
                from.getBlockZ() != to.getBlockZ();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();

        if (hasChangedBlock(from, to)) {
            Player player = event.getPlayer();
            PlayerPositionChangeEvent customEvent = new PlayerPositionChangeEvent(player, to);
            Bukkit.getPluginManager().callEvent(customEvent);
        }
    }

    @EventHandler
    public void onPlayerPositionChange(PlayerPositionChangeEvent event) {
        PlayerPositionChangePayload payload = new PlayerPositionChangePayload(
                "PlayerPostitionChangeEvent", 
                event.getPlayer().getUniqueId(),
                event.getNewPosition()
            );

        KAPI.getPlugin().getGameEventWebSocketServer().getEventDispatcher().dispatch(
                "PlayerPostitionChangeEvent",
                payload
            );
    }

}
