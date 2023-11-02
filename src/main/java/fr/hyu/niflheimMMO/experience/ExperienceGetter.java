package fr.hyu.niflheimMMO.experience;

import fr.hyu.Main;
import fr.hyu.niflheimPermissions.player.PlayerProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;

public class ExperienceGetter implements Listener {

    public static void setExperienceEarned(LivingEntity entity, Player player) {
        int experience = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Level"), PersistentDataType.INTEGER);
        PlayerProfile.addStat(player, PlayerProfile.Stat.EXPERIENCEPOINTS, experience);
        player.sendMessage(ChatColor.GRAY + "Vous avez tué " + entity.getName() + ". " + ChatColor.GREEN + "+" + experience + " XP");
        if(Experiences.reloadExperience(player)) {
            player.sendMessage(ChatColor.GREEN + "Vous etes passé au niveau : " + PlayerProfileManager.profiles.get(player).getLevel());
        }
    }

//METTRE LES COEFF DES EXPS EN FONCTIONS DES MOBS
}

