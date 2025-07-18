package com.etarruella.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.WebSocket;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventDispatcher {

    private static final Logger logger = KAPI.getPlugin().getLogger();

    private final SubscriptionManager subscriptionManager;
    private final ObjectMapper mapper = new ObjectMapper();

    public EventDispatcher(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    public void dispatch(String topic, Object payload) {
        try {
            String json = mapper.writeValueAsString(payload);
            Set<WebSocket> clients = subscriptionManager.getSubscribers(topic);

            if (clients == null || clients.isEmpty()) {
                logger.info("No subscribers for topic: " + topic);
                return;
            }

            for (WebSocket client : clients) {
                client.send(json);
                logger.info("Sent event to client " + client.getRemoteSocketAddress());
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error dispatching event for topic '" + topic + "'", e);
        }
    }
}
