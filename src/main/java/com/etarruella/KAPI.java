package com.etarruella;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class KAPI extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            double newLife = Math.max(entity.getHealth() - event.getDamage(), 0);
            entity.sendMessage("Vida -: " + newLife);
        }
    }

    @EventHandler
    public void onEntityRegainHealthEvent(EntityRegainHealthEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            double newLife = Math.min(entity.getHealth() + event.getAmount(), entity.getMaxHealth());
            entity.sendMessage("Vida +: " + newLife);
        }
    }

}