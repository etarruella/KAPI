package com.etarruella.listener;

import com.etarruella.event.PlayerPositionChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerPositionListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getFrom().getBlockX() != event.getTo().getBlockX()
            || event.getFrom().getBlockY() != event.getTo().getBlockY()
            || event.getFrom().getBlockZ() != event.getTo().getBlockZ()) {

            Player player = event.getPlayer();
            PlayerPositionChangeEvent customEvent = new PlayerPositionChangeEvent(player, event.getTo());
            Bukkit.getPluginManager().callEvent(customEvent);
        }
    }

    @EventHandler
    public void onPlayerPositionChange(PlayerPositionChangeEvent event) {
        // Aquí conectarías con el WebSocket para enviar la actualización
        // Código pendiente por implementar

        // Ejemplo:
        // webSocketManager.sendPositionUpdate(event.getPlayer().getUniqueId(), event.getNewPosition());

        // TODO: Implementar lógica de envío por WebSocket
    }
    
}
