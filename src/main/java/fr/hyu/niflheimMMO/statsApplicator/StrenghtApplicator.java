package fr.hyu.niflheimMMO.statsApplicator;

import fr.hyu.Main;
import fr.hyu.niflheimPermissions.player.PlayerProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class StrenghtApplicator {

    public static boolean damageOnMob(Entity entity, Player player, ItemStack itemInUse) {

        PlayerProfile playerProfile = PlayerProfileManager.profiles.get(player);

        int strengthPlayerNative = playerProfile.getStrengthNative();
        //int strengthPlayer = strengthPlayerNative + itemInUse.getItemMeta()
        int dextirityPlayer = playerProfile.getDexterityNative();
        int intelligencePlayer = playerProfile.getIntelligenceNative();
        int faithPlayer = playerProfile.getFaithNative();

        int vitalityMob = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Vitality"), PersistentDataType.INTEGER);
        int agilityMob = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Agility"), PersistentDataType.INTEGER);

        int newVitalityMob = vitalityMob - strengthPlayerNative;
        player.sendMessage(String.valueOf(newVitalityMob));

        if(newVitalityMob <= 0) {
            return true;
        }
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Vitality"), PersistentDataType.INTEGER, newVitalityMob);
            return false;


    }
}
