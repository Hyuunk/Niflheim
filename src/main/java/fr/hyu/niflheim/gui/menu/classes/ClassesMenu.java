package fr.hyu.niflheim.gui.menu.classes;

import fr.hyu.Main;
import fr.hyu.niflheim.gui.GuiItem;
import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheimMMO.classes.PlayerClassesProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ClassesMenu implements Listener {
    @EventHandler
    public void onCloseClassesMenu(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        try {
        if (PlayerProfileManager.profiles.get(player).getInventories().contains(inventory)
            && PlayerProfileManager.profiles.get(player).getClasses().equals(PlayerClassesProfile.Classes.NONE)
            && !PlayerProfileManager.profiles.get(player).getClasses().equals(PlayerClassesProfile.Classes.PENDING)) {

            if (inventory == PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYCLASSES)) {

                Bukkit.getScheduler().runTaskLater(Main.INSTANCE, () -> {
                    try {
                        GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYCLASSES));
                    } catch (NullPointerException ex) {
                    }
                    ;
                }, 40);
            }
        }
        } catch (NullPointerException ex) {}
    }

    public static void onClassesItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {

            case GREEN_GLAZED_TERRACOTTA:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.PENDING);
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBARCHER));
                break;

            case LIGHT_BLUE_GLAZED_TERRACOTTA:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.PENDING);
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBMAGE));
                break;

            case RED_GLAZED_TERRACOTTA:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.PENDING);
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBWARRIOR));
                break;

            case YELLOW_GLAZED_TERRACOTTA:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.PENDING);
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBSPIRITUALIST));
                break;
        }
    }

    public static void onClassesDevItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {

            case WHITE_TERRACOTTA:
                player.closeInventory();
                break;

            case GREEN_GLAZED_TERRACOTTA:
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBARCHER_DEVMOD));
                break;

            case LIGHT_BLUE_GLAZED_TERRACOTTA:
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBMAGE_DEVMOD));
                break;

            case RED_GLAZED_TERRACOTTA:
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBWARRIOR_DEVMOD));
                break;

            case YELLOW_GLAZED_TERRACOTTA:
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBSPIRITUALIST_DEVMOD));
                break;
        }
    }

    public static void initializeClassesMenuItems(final Player player, final Inventory inventory) {

        inventory.setItem(10, GuiItem.createGuiItem(new ItemStack(Material.GREEN_GLAZED_TERRACOTTA), ChatColor.GREEN + "Archer", ""));
        inventory.setItem(12, GuiItem.createGuiItem(new ItemStack(Material.LIGHT_BLUE_GLAZED_TERRACOTTA), ChatColor.BLUE + "Mage", ""));
        inventory.setItem(14, GuiItem.createGuiItem(new ItemStack(Material.RED_GLAZED_TERRACOTTA), ChatColor.RED + "Guerrier", ""));
        inventory.setItem(16, GuiItem.createGuiItem(new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA), ChatColor.YELLOW + "Spiritualiste", ""));


    }

    public static void initializeClassesMenuDevItems(final Player player, final Inventory inventory) {

        inventory.setItem(10, GuiItem.createGuiItem(new ItemStack(Material.GREEN_GLAZED_TERRACOTTA), ChatColor.GREEN + "Archer", ""));
        inventory.setItem(12, GuiItem.createGuiItem(new ItemStack(Material.LIGHT_BLUE_GLAZED_TERRACOTTA), ChatColor.BLUE + "Mage", ""));
        inventory.setItem(14, GuiItem.createGuiItem(new ItemStack(Material.RED_GLAZED_TERRACOTTA), ChatColor.RED + "Guerrier", ""));
        inventory.setItem(16, GuiItem.createGuiItem(new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA), ChatColor.YELLOW + "Spiritualiste", ""));

        inventory.setItem(31, GuiItem.createGuiItem(new ItemStack(Material.WHITE_TERRACOTTA), ChatColor.WHITE + "SKIP", ""));

    }
}
