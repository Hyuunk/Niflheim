package fr.hyu.niflheimMMO.statsApplicator;

import fr.hyu.Main;
import fr.hyu.niflheimMMO.mobs.MonsterProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.text.DecimalFormat;
import java.util.Random;

public class DexterityApplicator {
    public static boolean isCriticalPlayer(Player player) {

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();

        ItemStack itemOnHead = player.getInventory().getHelmet();
        ItemStack itemOnChestplate = player.getInventory().getChestplate();
        ItemStack itemOnLeggings = player.getInventory().getLeggings();
        ItemStack itemOnBoots = player.getInventory().getBoots();


        int dexterityNative = (int) PlayerProfile.getStat(player, PlayerProfile.Stat.DEXTIRITYNATIVE);
            int dexterity = dexterityNative + dexterityItem(itemInMainHand) + dexterityItem(itemInOffHand) + dexterityItem(itemOnHead) + dexterityItem(itemOnChestplate) + dexterityItem(itemOnLeggings) + dexterityItem(itemOnBoots);


            Random rand = new Random();

            // Génère un nombre aléatoire entre 0 et 1 pour déterminer si l'attaque réussit
            double randomValue = rand.nextDouble();

            // Si la probabilité d'esquive est supérieure au nombre aléatoire, l'attaque échoue
            if (randomValue > dexterityCalculator(dexterity)) {
                return false;
            } else {
                return true;

        }

    }

    private static int dexterityItem(ItemStack itemInUse) {

        if(itemInUse == null) return 0;

        if (itemInUse.hasItemMeta()) {
            if(itemInUse.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "isStated"), PersistentDataType.BOOLEAN) != null) {
                return itemInUse.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Dexterity"), PersistentDataType.INTEGER);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static double dexterityCalculator(int dexterity) {

        if(dexterity < 0 ) return 0;
        // Créez un objet DecimalFormat pour spécifier le format
        DecimalFormat decimalFormat = new DecimalFormat("#.####");

        double dexterityCalculated = 0.001 + 0.0749 * Math.log(dexterity + 1);

        // Utilisez DecimalFormat pour formater le nombre double
        String formattedDouble = decimalFormat.format(dexterityCalculated);

        // Convertissez la chaîne formatée en double si nécessaire
        return Double.parseDouble(formattedDouble);
    }
}
