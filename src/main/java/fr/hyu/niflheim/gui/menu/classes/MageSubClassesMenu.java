package fr.hyu.niflheim.gui.menu.classes;

import fr.hyu.Main;
import fr.hyu.niflheim.gui.GuiItem;
import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheimMMO.classes.PlayerClassesProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MageSubClassesMenu {

    public static void onMenuMageSubItem(final ItemStack itemStack, final Player player, final InventoryAction action, int slot) {
        final Material material = itemStack.getType();
        switch (material) {

            case ENCHANTED_BOOK:
            case SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE:
            case BLAZE_ROD:
                onActionItem(player, slot);
                break;

            case GREEN_TERRACOTTA:
                onClassChoice(player, slot);
                player.closeInventory();
                break;

            case ARROW:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.NONE);
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYCLASSES));
                break;

        }
    }

    public static void onMenuMageSubDevItem(final ItemStack itemStack, final Player player, final InventoryAction action, int slot) {
        final Material material = itemStack.getType();
        switch (material) {

            case ENCHANTED_BOOK:
            case SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE:
            case BLAZE_ROD:
                onActionDevItem(player, slot);
                break;

            case GREEN_TERRACOTTA:
                onClassChoice(player, slot);
                player.closeInventory();
                break;

            case ARROW:
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYCLASSES_DEVMOD));
                break;

        }
    }

    private static void onActionItem(final Player player, final int slot) {
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBMAGE).clear(slot);
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBMAGE).setItem(slot, GuiItem.createGuiItem(new ItemStack(Material.GREEN_TERRACOTTA), ChatColor.GREEN + "VALIDER!"));
        Bukkit.getServer().getScheduler().runTaskLater(Main.INSTANCE, () -> {
            GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBMAGE));
            return;
        }, 30);
    }
    private static void onActionDevItem(final Player player, final int slot) {
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBMAGE_DEVMOD).clear(slot);
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBMAGE_DEVMOD).setItem(slot, GuiItem.createGuiItem(new ItemStack(Material.GREEN_TERRACOTTA), ChatColor.GREEN + "VALIDER!"));
        Bukkit.getServer().getScheduler().runTaskLater(Main.INSTANCE, () -> {
            GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBMAGE_DEVMOD));
            return;
        }, 30);
    }
    private static void onClassChoice(Player player, int slot) {
        switch (slot) {
            case 11:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.ARCHMAGE);
                break;
            case 13:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.SORCERER);
                break;
            case 15:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.CLERIC);
                break;
        }
    }

    public static void initializeMageSubMenuItems(final Player player, final Inventory inventory) {

        inventory.setItem(11, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.ARCHMAGE.getMaterial()), PlayerClassesProfile.Classes.ARCHMAGE.getColor() + PlayerClassesProfile.Classes.ARCHMAGE.getDisplayName(), ""));
        inventory.setItem(13, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.SORCERER.getMaterial()), PlayerClassesProfile.Classes.SORCERER.getColor() + PlayerClassesProfile.Classes.SORCERER.getDisplayName(), ""));
        inventory.setItem(15, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.CLERIC.getMaterial()), PlayerClassesProfile.Classes.CLERIC.getColor() + PlayerClassesProfile.Classes.CLERIC.getDisplayName(), ""));

        inventory.setItem(27, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));

    }

    public static void initializeMageSubMenuDevItems(final Player player, final Inventory inventory) {

        inventory.setItem(11, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.ARCHMAGE.getMaterial()), PlayerClassesProfile.Classes.ARCHMAGE.getColor() + PlayerClassesProfile.Classes.ARCHMAGE.getDisplayName(), ""));
        inventory.setItem(13, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.SORCERER.getMaterial()), PlayerClassesProfile.Classes.SORCERER.getColor() + PlayerClassesProfile.Classes.SORCERER.getDisplayName(), ""));
        inventory.setItem(15, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.CLERIC.getMaterial()), PlayerClassesProfile.Classes.CLERIC.getColor() + PlayerClassesProfile.Classes.CLERIC.getDisplayName(), ""));

        inventory.setItem(27, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));

    }
}
