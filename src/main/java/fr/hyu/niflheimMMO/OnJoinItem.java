package fr.hyu.niflheimMMO;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class OnJoinItem implements Listener {
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        final ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "MENU");
        final List<String> loresList = new ArrayList<String>();
        loresList.add("Cliquez sur l'étoile du nether pour ouvrir votre menu de joueur.");
        meta.setLore((List)loresList);
        item.setItemMeta(meta);
        player.getInventory().setItem(8, item);
    }
}
