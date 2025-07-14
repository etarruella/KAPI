package com.etarruella.network;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class GameEventWebSocketServer extends WebSocketServer {

    private final Set<WebSocket> connections = Collections.synchronizedSet(new HashSet<>());
    private final WebSocketServerManager manager;
    private final SubscriptionManager subscriptionManager = new SubscriptionManager();
    private final EventDispatcher eventDispatcher = new EventDispatcher(subscriptionManager);
    private final Logger logger;

    public GameEventWebSocketServer(InetSocketAddress address, WebSocketServerManager manager) {
        super(address);
        this.manager = manager;
        this.logger = manager.getLogger();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        connections.add(conn);
        logger.info("New WebSocket connection from " + conn.getRemoteSocketAddress());
        // Aquí podrías inicializar sesión, esperar mensajes para suscripción, etc.
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        connections.remove(conn);
        logger.info("WebSocket connection closed: " + conn.getRemoteSocketAddress() + " Reason: " + reason);
        // Limpia sesiones y suscripciones si tienes
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        logger.info("Message from " + conn.getRemoteSocketAddress() + ": " + message);
        subscriptionManager.handleClientMessage(conn, message);
    }


    @Override
    public void onError(WebSocket conn, Exception ex) {
        logger.severe("WebSocket error: " + ex.getMessage());
    }

    @Override
    public void onStart() {
        logger.info("GameEventWebSocketServer started");
    }

    public void broadcast(String message) {
        synchronized (connections) {
            for (WebSocket conn : connections) {
                conn.send(message);
            }
        }
    }

    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }
}
