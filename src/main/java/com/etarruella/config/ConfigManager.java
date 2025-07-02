package com.etarruella.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ConfigManager {

    private final JavaPlugin plugin;

    private boolean networkEnabled;
    private String networkHost;
    private int networkPort;
    private String loggingLevel;
    private List<String> allowedAttributes;
    private boolean pluginEnabled;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // Load or reload configuration values
    public void loadConfig() {
        // Saves default config.yml if it does not exist
        plugin.saveDefaultConfig();

        FileConfiguration config = plugin.getConfig();

        // Read values with default fallbacks
        this.networkEnabled = config.getBoolean("network.enabled", true);
        this.networkHost = config.getString("network.host", "127.0.0.1");
        this.networkPort = config.getInt("network.port", 8765);
        this.loggingLevel = config.getString("logging.level", "INFO");
        this.allowedAttributes = config.getStringList("filters.allowedAttributes");
        this.pluginEnabled = config.getBoolean("plugin.enabled", true);
    }

    // Getters

    public boolean isNetworkEnabled() {
        return networkEnabled;
    }

    public String getNetworkHost() {
        return networkHost;
    }

    public int getNetworkPort() {
        return networkPort;
    }

    public String getLoggingLevel() {
        return loggingLevel;
    }

    public List<String> getAllowedAttributes() {
        return allowedAttributes;
    }

    public boolean isPluginEnabled() {
        return pluginEnabled;
    }
}
