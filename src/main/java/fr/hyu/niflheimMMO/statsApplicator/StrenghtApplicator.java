package fr.hyu.niflheimMMO.statsApplicator;

import fr.hyu.Main;
import fr.hyu.niflheimMMO.items.ItemProfile;
import fr.hyu.niflheimMMO.mobs.MonsterProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StrenghtApplicator {

    public static boolean damageOnMob(MonsterProfile monsterProfile, Player player) {

        PlayerProfile playerProfile = PlayerProfileManager.profiles.get(player);

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();

        ItemStack itemOnHead = player.getInventory().getHelmet();
        ItemStack itemOnChestplate = player.getInventory().getChestplate();
        ItemStack itemOnLeggings = player.getInventory().getLeggings();
        ItemStack itemOnBoots = player.getInventory().getBoots();

        int strengthPlayerNative = playerProfile.getStrengthNative();

        int vitalityMob = monsterProfile.getVitality();

        int attackDamage = strengthPlayerNative + strengthItem(itemInMainHand) + strengthItem(itemInOffHand) + strengthItem(itemOnHead) + strengthItem(itemOnChestplate) + strengthItem(itemOnLeggings) + strengthItem(itemOnBoots);
        attackDamage = DefenceApplicator.newAttackDamage(attackDamage, monsterProfile);
        if(DexterityApplicator.isCriticalPlayer(player)) {
            attackDamage = (int) (attackDamage * 1.5);
            if (attackDamage > 0) {player.sendMessage(ChatColor.GOLD + "-" + attackDamage + " ➶");} else if(attackDamage < 0) {player.sendMessage(ChatColor.GOLD + "+" + Math.abs(attackDamage) + " ➶");}

        } else {
            if (attackDamage > 0) {player.sendMessage(ChatColor.RED + "-" + attackDamage + " ❤");} else if(attackDamage < 0) {player.sendMessage(ChatColor.RED + "+" + Math.abs(attackDamage) + " ❤");}

        }

        int newVitalityMob = vitalityMob - attackDamage;

        if(newVitalityMob <= 0) return true;

        monsterProfile.setVitality(newVitalityMob);
            return false;


    }

    private static int strengthItem(ItemStack itemInUse) {

        if(itemInUse == null) return 0;

        if (itemInUse.hasItemMeta()) {
            if(itemInUse.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "isStated"), PersistentDataType.BOOLEAN) != null) {
                return itemInUse.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Strength"), PersistentDataType.INTEGER);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

}
