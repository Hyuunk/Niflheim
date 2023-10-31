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

public class SpiritualistSubClassesMenu {

    public static void onMenuSpiritualistSubItem(final ItemStack itemStack, final Player player, final InventoryAction action, int slot) {
        final Material material = itemStack.getType();
        switch (material) {

            case ECHO_SHARD:
            case TOTEM_OF_UNDYING:
            case BRUSH:
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

    public static void onMenuSpiritualistSubDevItem(final ItemStack itemStack, final Player player, final InventoryAction action, int slot) {
        final Material material = itemStack.getType();
        switch (material) {

            case ECHO_SHARD:
            case TOTEM_OF_UNDYING:
            case BRUSH:
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
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBSPIRITUALIST).clear(slot);
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBSPIRITUALIST).setItem(slot, GuiItem.createGuiItem(new ItemStack(Material.GREEN_TERRACOTTA), ChatColor.GREEN + "VALIDER!"));
        Bukkit.getServer().getScheduler().runTaskLater(Main.INSTANCE, () -> {
            GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBSPIRITUALIST));
            return;
        }, 30);
    }
    private static void onActionDevItem(final Player player, final int slot) {
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBSPIRITUALIST_DEVMOD).clear(slot);
        PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBSPIRITUALIST_DEVMOD).setItem(slot, GuiItem.createGuiItem(new ItemStack(Material.GREEN_TERRACOTTA), ChatColor.GREEN + "VALIDER!"));
        Bukkit.getServer().getScheduler().runTaskLater(Main.INSTANCE, () -> {
            GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSUBSPIRITUALIST_DEVMOD));
            return;
        }, 30);
    }

    private static void onClassChoice(Player player, int slot) {
        switch (slot) {
            case 11:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.PUGILIST);
                break;
            case 13:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.SUMMONER);
                break;
            case 15:
                PlayerProfileManager.profiles.get(player).setClasses(PlayerClassesProfile.Classes.SHAMAN);
                break;
        }
    }

    public static void initializeSpiritualistSubMenuItems(final Player player, final Inventory inventory) {

        inventory.setItem(11, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.PUGILIST.getMaterial()), PlayerClassesProfile.Classes.PUGILIST.getColor() + PlayerClassesProfile.Classes.PUGILIST.getDisplayName(), ""));
        inventory.setItem(13, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.SUMMONER.getMaterial()), PlayerClassesProfile.Classes.SUMMONER.getColor() + PlayerClassesProfile.Classes.SUMMONER.getDisplayName(), ""));
        inventory.setItem(15, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.SHAMAN.getMaterial()), PlayerClassesProfile.Classes.SHAMAN.getColor() + PlayerClassesProfile.Classes.SHAMAN.getDisplayName(), ""));

        inventory.setItem(27, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));

    }

    public static void initializeSpiritualistSubMenuDevItems(final Player player, final Inventory inventory) {

        inventory.setItem(11, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.PUGILIST.getMaterial()), PlayerClassesProfile.Classes.PUGILIST.getColor() + PlayerClassesProfile.Classes.PUGILIST.getDisplayName(), ""));
        inventory.setItem(13, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.SUMMONER.getMaterial()), PlayerClassesProfile.Classes.SUMMONER.getColor() + PlayerClassesProfile.Classes.SUMMONER.getDisplayName(), ""));
        inventory.setItem(15, GuiItem.createGuiItem(new ItemStack(PlayerClassesProfile.Classes.SHAMAN.getMaterial()), PlayerClassesProfile.Classes.SHAMAN.getColor() + PlayerClassesProfile.Classes.SHAMAN.getDisplayName(), ""));

        inventory.setItem(27, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));

    }
}
