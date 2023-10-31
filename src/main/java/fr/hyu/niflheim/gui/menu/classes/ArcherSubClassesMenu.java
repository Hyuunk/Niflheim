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
import fr.hyu.niflheimMMO.classes.PlayerClassesProfile.Classes;

public class ArcherSubClassesMenu {

    public static void onMenuArcherSubItem(final ItemStack itemStack, final Player player, final InventoryAction action, int slot) {
        final Material material = itemStack.getType();
        switch (material) {

            case BOW:
            case GOLDEN_HOE:
            case CROSSBOW:
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

    public static void onMenuArcherSubDevItem(final ItemStack itemStack, final Player player, final InventoryAction action, int slot) {
        final Material material = itemStack.getType();
        switch (material) {

            case BOW:
            case GOLDEN_HOE:
            case CROSSBOW:
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
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBARCHER).clear(slot);
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBARCHER).setItem(slot, GuiItem.createGuiItem(new ItemStack(Material.GREEN_TERRACOTTA), ChatColor.GREEN + "VALIDER!"));
        Bukkit.getServer().getScheduler().runTaskLater(Main.INSTANCE, () -> {
            GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBARCHER));
            return;
        }, 30);
    }
    private static void onActionDevItem(final Player player, final int slot) {
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBARCHER_DEVMOD).clear(slot);
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBARCHER_DEVMOD).setItem(slot, GuiItem.createGuiItem(new ItemStack(Material.GREEN_TERRACOTTA), ChatColor.GREEN + "VALIDER!"));
        Bukkit.getServer().getScheduler().runTaskLater(Main.INSTANCE, () -> {
            GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBARCHER_DEVMOD));
            return;
        }, 30);
    }

    private static void onClassChoice(Player player, int slot) {
        switch (slot) {
            case 11:
                PlayerProfileManager.profiles.get(player).setClasses(Classes.SNIPER);
                break;
            case 13:
                PlayerProfileManager.profiles.get(player).setClasses(Classes.CROSSBOWMAN);
                break;
            case 15:
                PlayerProfileManager.profiles.get(player).setClasses(Classes.MAZEMASTER);
                break;
        }
    }

    public static void initializeArcherSubMenuItems(final Player player, final Inventory inventory) {

        inventory.setItem(11, GuiItem.createGuiItem(new ItemStack(Classes.SNIPER.getMaterial()),Classes.SNIPER.getColor() + Classes.SNIPER.getDisplayName(), ""));
        inventory.setItem(13, GuiItem.createGuiItem(new ItemStack(Classes.CROSSBOWMAN.getMaterial()), Classes.CROSSBOWMAN.getColor() + Classes.CROSSBOWMAN.getDisplayName(), ""));
        inventory.setItem(15, GuiItem.createGuiItem(new ItemStack(Classes.MAZEMASTER.getMaterial()), Classes.MAZEMASTER.getColor() + Classes.MAZEMASTER.getDisplayName(), ""));

        inventory.setItem(27, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));



    }

    public static void initializeArcherSubMenuDevItems(final Player player, final Inventory inventory) {

        inventory.setItem(11, GuiItem.createGuiItem(new ItemStack(Classes.SNIPER.getMaterial()),Classes.SNIPER.getColor() + Classes.SNIPER.getDisplayName(), ""));
        inventory.setItem(13, GuiItem.createGuiItem(new ItemStack(Classes.CROSSBOWMAN.getMaterial()), Classes.CROSSBOWMAN.getColor() + Classes.CROSSBOWMAN.getDisplayName(), ""));
        inventory.setItem(15, GuiItem.createGuiItem(new ItemStack(Classes.MAZEMASTER.getMaterial()), Classes.MAZEMASTER.getColor() + Classes.MAZEMASTER.getDisplayName(), ""));

        inventory.setItem(27, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));

    }
}
