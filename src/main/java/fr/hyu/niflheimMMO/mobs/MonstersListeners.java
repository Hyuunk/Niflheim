package fr.hyu.niflheimMMO.mobs;

import fr.hyu.niflheimMMO.experience.ExperienceGetter;
import fr.hyu.niflheimMMO.statsApplicator.AgilityApplicator;
import fr.hyu.niflheimMMO.statsApplicator.StrenghtApplicator;
import fr.hyu.niflheimPermissions.player.PlayerRankProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class MonstersListeners implements Listener {

    @EventHandler
    public void onMonsterHit(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (PlayerRankProfile.hasPermission(player, "niflheim.devlog")) {
                event.setDamage(0); //Cancel des dégats du jeu pour appliquer les dégats du plugin
                if (entity instanceof LivingEntity) {

                    LivingEntity livingEntity = (LivingEntity) entity;
                    MonsterProfile monsterProfile = new MonsterProfile(livingEntity, entity.getLocation());
                    if(AgilityApplicator.isDodgeMob( monsterProfile)){ player.sendMessage(ChatColor.GRAY + "Esquivé !"); event.setCancelled(true);return;}
                    if(StrenghtApplicator.damageOnMob(monsterProfile, player)) { ExperienceGetter.setExperienceEarned(livingEntity, player); livingEntity.setHealth(0.0); MonsterDrop.DropLoot(entity);}
                }
            }
        }

    }

    @EventHandler
    public void onProjectileHit(EntityDamageEvent event) {
/*
        if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (PlayerRankProfile.hasPermission(Bukkit.getPlayer("Hyuu_"), "niflheim.devlog")) {
            event.setDamage(0);
        }
        }
        */

    }

    @EventHandler
    public void onMonsterDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        EntityType entityType = entity.getType();

        if(entity.getLastDamageCause() == null) return;

        if (entity.getKiller() instanceof Player) { //double kill avec les stats de on normal faut retirer ça
            ExperienceGetter.setExperienceEarned((LivingEntity) entity, entity.getKiller());
        }
        if (entity.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.MAGIC)) {
            if (entity.getKiller() instanceof Player) {
                Player player = entity.getKiller();
                ExperienceGetter.setExperienceEarned(entity, player);
            }
        }
    }

//METTRE LES MSG DE MORTS JSP QUOI DAUTRES
}
