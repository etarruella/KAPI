package com.etarruella.network;

import org.java_websocket.WebSocket;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class SubscriptionManager {

    private final Map<WebSocket, Set<String>> subscriptions = new ConcurrentHashMap<>();

    public void subscribe(WebSocket conn, String topic) {
        subscriptions.computeIfAbsent(conn, k -> new CopyOnWriteArraySet<>()).add(topic);
    }

    public void unsubscribe(WebSocket conn, String topic) {
        Set<String> topics = subscriptions.get(conn);
        if (topics != null) {
            topics.remove(topic);
            if (topics.isEmpty()) {
                subscriptions.remove(conn);
            }
        }
    }

    public void removeConnection(WebSocket conn) {
        subscriptions.remove(conn);
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
