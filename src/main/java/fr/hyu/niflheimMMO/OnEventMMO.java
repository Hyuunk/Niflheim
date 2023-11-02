package fr.hyu.niflheimMMO;

import fr.hyu.niflheimPermissions.player.PlayerProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static fr.hyu.niflheimMMO.experience.Experiences.levelNeededPerLevel;

public class OnEventMMO implements Listener {
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        final ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "MENU");
        final List<String> loresList = new ArrayList<String>();
        loresList.add("Cliquez sur l'étoile du nether pour ouvrir votre menu de joueur.");
        meta.setLore(loresList);
        item.setItemMeta(meta);
        player.getInventory().setItem(17, item);

        //INITIALISATION DE LXP NECESSAIRE
        PlayerProfileManager.profiles.get(player).setExperienceNeeded(levelNeededPerLevel.get(PlayerProfileManager.profiles.get(player).getLevel()));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();


        final ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "MENU");
        final List<String> loresList = new ArrayList<String>();
        loresList.add("Cliquez sur l'étoile du nether pour ouvrir votre menu de joueur.");
        meta.setLore(loresList);
        item.setItemMeta(meta);
        player.getInventory().setItem(17, item);
    }
}
