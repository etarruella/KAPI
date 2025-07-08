package com.etarruella;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.plugin.java.JavaPlugin;

import config.ConfigManager;
import network.GameEventWebSocketServer;
import network.WebSocketServerManager;

public class KAPI extends JavaPlugin {

    private ConfigManager configManager;
    private WebSocketServerManager webSocketServerManager;
    private GameEventWebSocketServer gameEventWebSocketServer;

    @Override
    public void onEnable() {
        // Load config
        configManager = new ConfigManager(this);

        // Load WebSocketServer
        webSocketServerManager = new WebSocketServerManager(
                configManager.getNetworkHost,
                configManager.getNetworkPort());

        // Load GameEventWebSocketServer
        gameEventWebSocketServer = new GameEventWebSocketServer(
                configManager.getNetworkHost(),
                webSocketServerManager);
    }

}
