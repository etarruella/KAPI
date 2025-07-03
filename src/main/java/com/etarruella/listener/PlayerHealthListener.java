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

    // Listener del evento de daño nativo
    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            Player player = (Player) entity;
            double oldHealth = entity.getHealth();
            double damage = event.getDamage();
            double newHealth = Math.max(oldHealth - damage, 0);

            // Lanzar evento personalizado con cambio negativo
            PlayerHealthChangeEvent healthChangeEvent = new PlayerHealthChangeEvent(player, oldHealth, -damage, newHealth);
            Bukkit.getPluginManager().callEvent(healthChangeEvent);
        }
    }

    // Listener del evento de regeneración nativo
    @EventHandler
    public void onEntityRegainHealthEvent(EntityRegainHealthEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            Player player = (Player) entity;
            double oldHealth = entity.getHealth();
            double amountHealed = event.getAmount();
            double newHealth = Math.min(oldHealth + amountHealed, entity.getMaxHealth());

            // Lanzar evento personalizado con cambio positivo
            PlayerHealthChangeEvent healthChangeEvent = new PlayerHealthChangeEvent(player, oldHealth, amountHealed, newHealth);
            Bukkit.getPluginManager().callEvent(healthChangeEvent);
        }
    }

    // Listener para el evento personalizado (aquí podrías enviar a WebSocket o lo que quieras)
    @EventHandler
    public void onPlayerHealthChange(PlayerHealthChangeEvent event) {
        // Por ahora, solo enviar un mensaje de ejemplo
        Player player = event.getPlayer();
        double change = event.getHealthChange();
        String sign = change > 0 ? "+" : "";
        player.sendMessage("Vida " + sign + ": " + event.getNewHealth());

        // TODO: Aquí conectar con WebSocket para enviar la actualización
    }
}
