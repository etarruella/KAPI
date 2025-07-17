package com.etarruella.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.WebSocket;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubscriptionManager {

    private static final Logger logger = Logger.getLogger(SubscriptionManager.class.getName());

    private final Map<WebSocket, Set<String>> subscriptions = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public void handleClientMessage(WebSocket conn, String message) {
        try {
            String type = mapper.readTree(message).get("type").asText();

            switch (type) {
                case "PlayerEvent" -> {
                    PlayerEventMessage pem = mapper.readValue(message, PlayerEventMessage.class);
                    handleAction(conn, pem.getAction().name(), pem.getEvent().name());
                }
                case "ServerEvent" -> {
                    ServerEventMessage sem = mapper.readValue(message, ServerEventMessage.class);
                    handleAction(conn, sem.getAction().name(), sem.getEvent().name());
                }
                default -> logger.warning("Unknown message type from " + conn.getRemoteSocketAddress() + ": " + type);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing message from " + conn.getRemoteSocketAddress(), e);
        }
    }

    private void handleAction(WebSocket conn, String action, String topic) {
        switch (action) {
            case "subscribe" -> subscribe(conn, topic);
            case "unsubscribe" -> unsubscribe(conn, topic);
            default -> logger.warning("Unknown action '" + action + "' from " + conn.getRemoteSocketAddress());
        }
    }

    public void subscribe(WebSocket conn, String topic) {
        subscriptions.computeIfAbsent(conn, k -> new CopyOnWriteArraySet<>()).add(topic);
        logger.info("Subscribed " + conn.getRemoteSocketAddress() + " to topic '" + topic + "'");
        conn.send("Successfully subscribed to topic '" + topic + "'");
    }

    public void unsubscribe(WebSocket conn, String topic) {
        Set<String> topics = subscriptions.get(conn);
        if (topics != null) {
            topics.remove(topic);
            logger.info("Unsubscribed " + conn.getRemoteSocketAddress() + " from topic '" + topic + "'");
            if (topics.isEmpty()) {
                subscriptions.remove(conn);
            }
        }
    }

    public void removeConnection(WebSocket conn) {
        subscriptions.remove(conn);
        logger.info("Removed connection " + conn.getRemoteSocketAddress());
    }

    public Set<WebSocket> getSubscribers(String topic) {
        Set<WebSocket> result = ConcurrentHashMap.newKeySet();
        subscriptions.forEach((conn, topics) -> {
            if (topics.contains(topic)) {
                result.add(conn);
            }
        });
        return result;
    }
}
