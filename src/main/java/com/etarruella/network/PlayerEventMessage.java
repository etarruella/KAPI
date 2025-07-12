package com.etarruella.network;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class PlayerEventMessage {

    public enum Action {
        subscribe,
        unsubscribe
    }

    public enum Event {
        PlayerHealthChangeEvent,
        PlayerMaxHealthChangeEvent,
        PlayerPositionChangeEvent,
        PlayerSaturationChangeEvent,
        PlayerFoodLevelChangeEvent,
        PlayerPotionEffectsChangeEvent,
        PlayerArmorChangeEvent,
        PlayerExperienceChangeEvent,
        PlayerGameModeChangeEvent,
        PlayerLevelChangeEvent,
        PlayerFlyingStatusChangeEvent,
        PlayerVelocityChangeEvent,
        PlayerSneakingStatusChangeEvent,
        PlayerSprintingStatusChangeEvent,
        PlayerExhaustionChangeEvent,
        PlayerAirChangeEvent,
        PlayerFireTicksChangeEvent,
        PlayerOnGroundStatusChangeEvent,
        PlayerOpenInventoryChangeEvent,
        PlayerItemInHandChangeEvent,
        PlayerActiveSlotChangeEvent,
        PlayerXpProgressChangeEvent,
        PlayerHungerSaturationChangeEvent,
        PlayerPingChangeEvent,
        PlayerSleepingStatusChangeEvent,
        PlayerCompassTargetChangeEvent,
        PlayerJoinEvent,
        PlayerQuitEvent,
        PlayerDeathEvent,
        PlayerRespawnEvent
    }

    private final String type;
    private final Action action;
    private final Event event;
    private final UUID playerUUID;

    @JsonCreator
    public PlayerEventMessage(
            @JsonProperty("type") String type,
            @JsonProperty("action") Action action,
            @JsonProperty("event") Event event,
            @JsonProperty("playerUUID") UUID playerUUID) {
        if (!"PlayerEvent".equals(type)) {
            throw new IllegalArgumentException("type must be 'PlayerEvent'");
        }
        this.type = type;
        this.action = action;
        this.event = event;
        this.playerUUID = playerUUID;
    }

    public String getType() {
        return type;
    }

    public Action getAction() {
        return action;
    }

    public Event getEvent() {
        return event;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    @Override
    public String toString() {
        return "PlayerEventMessage{" +
                "type='" + type + '\'' +
                ", action=" + action +
                ", event=" + event +
                ", playerUUID=" + playerUUID +
                '}';
    }
}
