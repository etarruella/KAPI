package com.etarruella.network.server;

import com.etarruella.KAPI;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
        executor.execute(() -> {
            server = new GameEventWebSocketServer(new InetSocketAddress(port), this);
            server.start();
        });

        try {
            if (executor.awaitTermination(10, TimeUnit.SECONDS)){
                logger.info("WebSocket server started on port " + port);
            } else {
                logger.info("WebSocket server timed out on start...");
                // TODO: call KAPI, shutdown plugin
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public Logger getLogger() {
        return logger;
    }

    public WebSocketServer getServer() {
        return server;
    }

}
