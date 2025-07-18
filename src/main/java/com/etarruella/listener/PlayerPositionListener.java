package com.etarruella.listener;

import com.etarruella.event.PlayerPositionChangeEvent;
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
        // Aquí conectarías con el WebSocket para enviar la actualización
        // Código pendiente por implementar

        // Ejemplo:
        // webSocketManager.sendPositionUpdate(event.getPlayer().getUniqueId(),
        // event.getNewPosition());

        // TODO: Implementar lógica de envío por WebSocket
    }

}
