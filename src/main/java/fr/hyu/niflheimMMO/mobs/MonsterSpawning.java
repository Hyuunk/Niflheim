package fr.hyu.niflheimMMO.mobs;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MonsterSpawning implements Listener {

    @EventHandler
    public void onMonsterSpawn(CreatureSpawnEvent event) {

        EntityType entityType = event.getEntityType();
        LivingEntity livingEntity = event.getEntity();
        if (entityType == EntityType.MAGMA_CUBE || entityType == EntityType.SLIME || entityType == EntityType.BEE) return;
        MonsterProfile getMonsterProfile = new MonsterProfile(livingEntity, livingEntity.getLocation());
    }
}
