package fr.hyu.niflheim.gui.menu.jobs;

import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

public class JobsMenu {


    public static void onJobsItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case ARROW: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU));
                break;
            }
        }
    }

}
