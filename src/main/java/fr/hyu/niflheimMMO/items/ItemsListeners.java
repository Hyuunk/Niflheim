package fr.hyu.niflheimMMO.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class ItemsListeners implements Listener {

    @EventHandler
    public void onItemOnHand(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        event.getPlayer().getItemInUse();
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        if(!(Bukkit.getPlayer("Hyuu_") == player)) return;
        if (itemStack.getType().equals(Material.AIR)) return;

        ItemProfile itemProfile = new ItemProfile(itemStack);
    }

}
