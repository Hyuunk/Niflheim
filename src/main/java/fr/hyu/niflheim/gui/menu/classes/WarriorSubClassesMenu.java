package fr.hyu.niflheim.gui.menu.classes;

import fr.hyu.Main;
import fr.hyu.niflheim.gui.GuiItem;
import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheimMMO.classes.PlayerClassesProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class WarriorSubClassesMenu {

    public static void onMenuWarriorSubItem(final ItemStack itemStack, final Player player, final InventoryAction action, int slot) {
        final Material material = itemStack.getType();
        switch (material) {

            case GOLDEN_AXE:
            case GOLDEN_SHOVEL:
            case FISHING_ROD:
                onActionItem(player, slot);
                break;

            case GREEN_TERRACOTTA:
                onClassChoice(player, slot);
                player.closeInventory();
                break;

            case ARROW:
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYCLASSES));
                break;

        }
    }
    public static void onMenuWarriorSubDevItem(final ItemStack itemStack, final Player player, final InventoryAction action, int slot) {
        final Material material = itemStack.getType();
        switch (material) {

            case GOLDEN_AXE:
            case GOLDEN_SHOVEL:
            case FISHING_ROD:
                onActionDevItem(player, slot);
                break;

            case GREEN_TERRACOTTA:
                onClassChoice(player, slot);
                player.closeInventory();
                break;

            case ARROW:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.NONE);
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYCLASSES_DEVMOD));
                break;

        }
    }

    private static void onActionItem(final Player player, final int slot) {
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBWARRIOR).clear(slot);
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBWARRIOR).setItem(slot, GuiItem.createGuiItem(new ItemStack(Material.GREEN_TERRACOTTA), ChatColor.GREEN + "VALIDER!"));
        Bukkit.getServer().getScheduler().runTaskLater(Main.INSTANCE, () -> {
            GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBWARRIOR));
            return;
        }, 30);
    }
    private static void onActionDevItem(final Player player, final int slot) {
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBWARRIOR_DEVMOD).clear(slot);
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBWARRIOR_DEVMOD).setItem(slot, GuiItem.createGuiItem(new ItemStack(Material.GREEN_TERRACOTTA), ChatColor.GREEN + "VALIDER!"));
        Bukkit.getServer().getScheduler().runTaskLater(Main.INSTANCE, () -> {
            GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBWARRIOR_DEVMOD));
            return;
        }, 30);
    }
    private static void onClassChoice(Player player, int slot) {
        switch (slot) {
            case 11:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.BERSERKER);
                break;
            case 13:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.PALADIN);
                break;
            case 15:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.ASSASSIN);
                break;
        }
    }

    public static void initializeWarriorSubMenuItems(final Player player, final Inventory inventory) {

        inventory.setItem(11, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.BERSERKER.getMaterial()), PlayerClassesProfile.Classes.BERSERKER.getColor() + PlayerClassesProfile.Classes.BERSERKER.getDisplayName(), ""));
        inventory.setItem(13, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.PALADIN.getMaterial()), PlayerClassesProfile.Classes.PALADIN.getColor() + PlayerClassesProfile.Classes.PALADIN.getDisplayName(), ""));
        inventory.setItem(15, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.ASSASSIN.getMaterial()), PlayerClassesProfile.Classes.ASSASSIN.getColor() + PlayerClassesProfile.Classes.ASSASSIN.getDisplayName(), ""));

        inventory.setItem(27, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));

    }

    public static void initializeWarriorSubMenuDevItems(final Player player, final Inventory inventory) {

        inventory.setItem(11, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.BERSERKER.getMaterial()), PlayerClassesProfile.Classes.BERSERKER.getColor() + PlayerClassesProfile.Classes.BERSERKER.getDisplayName(), ""));
        inventory.setItem(13, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.PALADIN.getMaterial()), PlayerClassesProfile.Classes.PALADIN.getColor() + PlayerClassesProfile.Classes.PALADIN.getDisplayName(), ""));
        inventory.setItem(15, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.ASSASSIN.getMaterial()), PlayerClassesProfile.Classes.ASSASSIN.getColor() + PlayerClassesProfile.Classes.ASSASSIN.getDisplayName(), ""));

        inventory.setItem(27, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));

    }
}
