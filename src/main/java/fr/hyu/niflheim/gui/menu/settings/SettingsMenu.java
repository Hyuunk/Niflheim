package fr.hyu.niflheim.gui.menu.settings;

import fr.hyu.niflheim.gui.GuiItem;
import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SettingsMenu {


    public static void onSettingsItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case ARROW: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU));
                break;
            }
        }
    }

    public static void onSettingsDevItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case ARROW: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU_DEVMOD));
                break;
            }
        }
    }

    public static void initializeSettingsMenuItems(final Player player, final Inventory inventory) {
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }

    public static void initializeSettingsMenuDevItems(final Player player, final Inventory inventory) {
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }
}
