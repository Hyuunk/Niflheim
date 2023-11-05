package fr.hyu.niflheimMMO.items;

import fr.hyu.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.util.List;

public class ItemsListeners implements Listener {

    @EventHandler
    public void onItemOnHand(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        if (itemStack.getType().equals(Material.AIR) || itemStack.getType().equals(Material.DECORATED_POT)) return;
        List<String> customItems = ItemProfile.initListCustomItem();
        if(customItems.contains(itemStack.getType().name())) {
            ItemProfile itemProfile = new ItemProfile(itemStack);
        } else if (itemStack.hasItemMeta()) {
            String itemID = itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "ID"), PersistentDataType.STRING);
            if (itemID != null && customItems.contains(itemID)) {
                ItemProfile itemProfile = new ItemProfile(itemStack);
            }
        }

    }

    @EventHandler
    public void onLootBagOpening(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();

        if (itemStack == null || !itemStack.getType().equals(Material.DECORATED_POT) || !itemStack.hasItemMeta()) return;

        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            String itemID = itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "ID"), PersistentDataType.STRING);
            List<String> customItems = ItemProfile.initListCustomItem();
            if(itemID == null || !customItems.contains(itemID)) return;
            itemStack.setAmount(0);
            File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Items/" + itemID + ".yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            ItemStack itemStackLootBag = new ItemStack(Material.matchMaterial(config.getString("Material")));
            ItemMeta itemMeta = itemStackLootBag.getItemMeta();
            itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "ID"), PersistentDataType.STRING, itemID);
            itemStackLootBag.setItemMeta(itemMeta);
            ItemProfile itemProfile = new ItemProfile(itemStackLootBag);
            event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().getHeldItemSlot(), itemStackLootBag);
         }

    }

}
