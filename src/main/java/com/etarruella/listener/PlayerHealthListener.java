package com.etarruella.listener;

import com.etarruella.event.PlayerHealthChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class PlayerHealthListener implements Listener {

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            Player player = (Player) entity;
            double oldHealth = entity.getHealth();
            double damage = event.getDamage();
            double newHealth = Math.max(oldHealth - damage, 0);

            PlayerHealthChangeEvent healthChangeEvent = new PlayerHealthChangeEvent(player, oldHealth, -damage, newHealth);
            Bukkit.getPluginManager().callEvent(healthChangeEvent);
        }
    }

    @EventHandler
    public void onEntityRegainHealthEvent(EntityRegainHealthEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            Player player = (Player) entity;
            double oldHealth = entity.getHealth();
            double amountHealed = event.getAmount();
            double newHealth = Math.min(oldHealth + amountHealed, entity.getMaxHealth());

            PlayerHealthChangeEvent healthChangeEvent = new PlayerHealthChangeEvent(player, oldHealth, amountHealed, newHealth);
            Bukkit.getPluginManager().callEvent(healthChangeEvent);
        }
    }

    @EventHandler
    public void onPlayerHealthChange(PlayerHealthChangeEvent event) {
        Player player = event.getPlayer();
        double change = event.getHealthChange();
        String sign = change > 0 ? "+" : "";
        player.sendMessage("Vida " + sign + ": " + event.getNewHealth());

        // TODO: Implementar lógica de envío por WebSocket
    }
}
