package com.etarruella.network;

import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class WebSocketServerManager {

    private final Logger logger;
    private final int port;
    private WebSocketServer server;
    private final ExecutorService executor;

    public WebSocketServerManager(int port, Logger logger) {
        this.port = port;
        this.logger = logger;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void start() {
        server = new GameEventWebSocketServer(new InetSocketAddress(port), this);
        server.start();
        logger.info("WebSocket server started on port " + port);
    }

    public void stop() {
        try {
            if (server != null) {
                server.stop();
                logger.info("WebSocket server stopped");
            }
        } catch (Exception e) {
            logger.severe("Error stopping WebSocket server: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

    public ExecutorService getExecutor() {
        return executor;
    }

}
