package fr.hyu.niflheim.gui.classmenu;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnClassBlock implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            Player player = event.getPlayer();

            if(clickedBlock == null) return;

            if(clickedBlock.getType().equals(Material.ANVIL)
                    ||clickedBlock.getType().equals(Material.FLETCHING_TABLE)
                    ||clickedBlock.getType().equals(Material.ENCHANTING_TABLE)
                    ||clickedBlock.getType().equals(Material.CAULDRON)) {

                Location blockLocation = clickedBlock.getLocation();
                Location blockToCheck = new Location(blockLocation.getWorld(), blockLocation.getBlockX(), blockLocation.getBlockY() - 1, blockLocation.getBlockZ());

                if(blockToCheck == null) return;

                if (blockToCheck.getBlock().getType().equals(Material.LODESTONE)) {

                    if(clickedBlock.getType().equals(Material.ANVIL)) {}
                    if(clickedBlock.getType().equals(Material.FLETCHING_TABLE)) {}
                    if(clickedBlock.getType().equals(Material.ENCHANTING_TABLE)) {}
                    if(clickedBlock.getType().equals(Material.CAULDRON)) {}

                }

            }
        }
    }
}
