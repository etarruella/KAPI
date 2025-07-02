package com.etarruella;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
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
            // newLife send to network server
        }
    }

}