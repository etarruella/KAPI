package com.etarruella.listener;

import com.etarruella.KAPI;
import com.etarruella.event.PlayerPositionChangeEvent;
import com.etarruella.network.payload.PlayerPositionChangePayload;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerPositionListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.hasExplicitlyChangedBlock()) {
            Player player = event.getPlayer();
            PlayerPositionChangeEvent customEvent = new PlayerPositionChangeEvent(player, event.getTo());
            Bukkit.getPluginManager().callEvent(customEvent);
        }
    }

    @EventHandler
    public void onPlayerPositionChange(PlayerPositionChangeEvent event) {
        PlayerPositionChangePayload payload = new PlayerPositionChangePayload(
                "PlayerPositionChangeEvent",
                event.getPlayer().getUniqueId(),
                event.getNewPosition()
            );

        KAPI.getPlugin().getGameEventWebSocketServer().getEventDispatcher().dispatch(
                "PlayerPositionChangeEvent",
                payload
            );
    }

}
