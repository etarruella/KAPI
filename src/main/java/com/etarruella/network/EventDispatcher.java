package com.etarruella.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.WebSocket;

import java.util.Set;

public class EventDispatcher {

    private final SubscriptionManager subscriptionManager;
    private final ObjectMapper mapper = new ObjectMapper();

    public EventDispatcher(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    public void dispatch(String topic, Object payload) {
        try {
            String json = mapper.writeValueAsString(payload);
            Set<WebSocket> clients = subscriptionManager.getSubscribers(topic);

            for (WebSocket client : clients) {
                client.send(json);
            }

        } catch (Exception e) {
            System.err.println("Error dispatching event to clients: " + e.getMessage());
        }
    }
}
