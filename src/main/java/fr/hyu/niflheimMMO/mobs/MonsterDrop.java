package fr.hyu.niflheimMMO.mobs;

import fr.hyu.Main;
import fr.hyu.niflheimMMO.items.ItemProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MonsterDrop {

    public static void DropLoot(Entity entity) {

        MonsterProfile monsterProfile = new MonsterProfile((LivingEntity) entity, entity.getLocation());

        HashMap<String, Double> lootTables = monsterProfile.getLootTable();

        for (Map.Entry<String, Double> entry : lootTables.entrySet()) {
            String id = entry.getKey();     // Récupérer la clé
            Double dropChance = entry.getValue(); // Récupérer la valeur associée à la clé

            Random random = new Random();
            double randomNumber = random.nextDouble();
            if (randomNumber <= dropChance) {
                if (Material.matchMaterial(id) == null) {
                    ItemStack droppedItem = new ItemStack(Material.DECORATED_POT, 1);
                    ItemMeta itemMeta = droppedItem.getItemMeta();
                    itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "ID"), PersistentDataType.STRING, id);
                    itemMeta.setDisplayName(ChatColor.WHITE + "Loot bag");
                    droppedItem.setItemMeta(itemMeta);
                    Item itemEntity = entity.getLocation().getWorld().dropItem(entity.getLocation(), droppedItem);
                    Bukkit.getPlayer("Hyuu_").sendMessage("dropped id   " + id + " " + randomNumber + " " + dropChance);

                } else {
                    ItemStack droppedItem = new ItemStack(Material.matchMaterial(id), 1);
                    ItemProfile item = new ItemProfile(droppedItem);
                    Item itemEntity = entity.getLocation().getWorld().dropItem(entity.getLocation(), droppedItem);
                    Bukkit.getPlayer("Hyuu_").sendMessage("dropped  " + id + " " + randomNumber + " " + dropChance);
                }

            } else {
                Bukkit.getPlayer("Hyuu_").sendMessage("notdropped  " + id + " " + randomNumber + " " + dropChance);

            }

        }
    }

}
