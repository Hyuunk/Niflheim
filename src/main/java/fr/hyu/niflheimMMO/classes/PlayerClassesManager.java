package fr.hyu.niflheimMMO.classes;

import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerClassesManager implements Listener {

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (PlayerProfileManager.profiles.get(player).getClasses() == PlayerClassesProfile.Classes.NONE) {
            if (!player.isOp()) {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYCLASSES));
            } else {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYCLASSES_DEVMOD));
            }
        }
    }
}
