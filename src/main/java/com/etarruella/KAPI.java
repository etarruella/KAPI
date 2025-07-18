package com.etarruella;

import com.etarruella.listener.PlayerHealthListener;
import com.etarruella.listener.PlayerPositionListener;
import org.bukkit.plugin.java.JavaPlugin;

import com.etarruella.config.ConfigManager;
import com.etarruella.network.GameEventWebSocketServer;
import com.etarruella.network.WebSocketServerManager;

import java.util.logging.Logger;

public class KAPI extends JavaPlugin {

    private static KAPI KAPI;

    private final Logger LOGGER =  Logger.getLogger("KAPI");

    private ConfigManager configManager;
    private WebSocketServerManager webSocketServerManager;
    private GameEventWebSocketServer gameEventWebSocketServer;

    public static KAPI getPlugin() {
        return KAPI;
    }

    @Override
    public void onEnable() {
        // Plugin init
        KAPI = this;

        // Load config
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        // Register listeners
        getServer().getPluginManager().registerEvents(new PlayerHealthListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerPositionListener(), this);

        // Load WebSocketServer
        webSocketServerManager = new WebSocketServerManager(configManager.getNetworkPort(), LOGGER);
        webSocketServerManager.start();

        // Load GameEventWebSocketServer
        gameEventWebSocketServer = (GameEventWebSocketServer) webSocketServerManager.getServer();
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

    public Logger getLogger() {
        return LOGGER;
    }

}
