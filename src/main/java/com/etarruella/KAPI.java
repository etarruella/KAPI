package com.etarruella;

import org.bukkit.plugin.java.JavaPlugin;

import com.etarruella.config.ConfigManager;
import com.etarruella.network.GameEventWebSocketServer;
import com.etarruella.network.WebSocketServerManager;

import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class KAPI extends JavaPlugin {

    private final Logger LOGGER =  Logger.getLogger("KAPI");

    private ConfigManager configManager;
    private WebSocketServerManager webSocketServerManager;
    private GameEventWebSocketServer gameEventWebSocketServer;

    @Override
    public void onEnable() {
        // Load config
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        // Load WebSocketServer
        webSocketServerManager = new WebSocketServerManager(configManager.getNetworkPort(), LOGGER);
        webSocketServerManager.start();

        // Load GameEventWebSocketServer
        gameEventWebSocketServer = new GameEventWebSocketServer(
                InetSocketAddress.createUnresolved(configManager.getNetworkHost(), configManager.getNetworkPort()),
                webSocketServerManager);
    }

    @Override
    public void onDisable() {
        webSocketServerManager.stop();
    }

    // Getters
    public ConfigManager getConfigManager() {
        return configManager;
    }

    public WebSocketServerManager getWebSocketServerManager() {
        return webSocketServerManager;
    }

    public GameEventWebSocketServer getGameEventWebSocketServer() {
        return gameEventWebSocketServer;
    }

}
