package fr.hyu.niflheim.gui;

import fr.hyu.niflheim.gui.menu.MenuManager;
import fr.hyu.niflheim.gui.menu.classes.*;
import fr.hyu.niflheim.gui.menu.profile.ProfileMenu;
import fr.hyu.niflheim.gui.menu.settings.SettingsMenu;
import fr.hyu.niflheim.gui.menu.stats.StatsMenu;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;


public class GuiManager implements Listener {
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        final ItemStack itemStack = event.getCurrentItem();
        final Player player = (Player) event.getWhoClicked();
        final InventoryAction action = event.getAction();
        if (PlayerProfileManager.profiles.get(player).getInventories().contains(event.getInventory())) {
            event.setCancelled(true);
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                return;
            }
            if (itemStack.getType() == Material.BARRIER) {
                closeInventory((HumanEntity) player);
            } else {
                switch (getInventoryType(event.getInventory(), player)) {
                    case INVENTORYPLAYERMENU:
                        MenuManager.onMenuItem(itemStack, player, action);
                        break;
                    case INVENTORYPLAYERMENU_DEVMOD:
                        MenuManager.onMenuDevItem(itemStack, player, action);
                        break;
                    case INVENTORYPROFILE:
                        ProfileMenu.onProfileItem(itemStack, player, action);
                        break;
                    case INVENTORYPROFILE_DEVMOD:
                        ProfileMenu.onProfileDevItem(itemStack, player, action);
                        break;
                    case INVENTORYSTATS:
                        StatsMenu.onMenuStatsItem(itemStack, player, action);
                        break;
                    case INVENTORYSTATS_DEVMOD:
                        StatsMenu.onMenuStatsDevItem(itemStack, player, action);
                        break;
                    case INVENTORYSETTINGS:
                        SettingsMenu.onSettingsItem(itemStack, player, action);
                        break;
                    case INVENTORYSETTINGS_DEVMOD:
                        SettingsMenu.onSettingsDevItem(itemStack, player, action);
                        break;
                    case INVENTORYCLASSES:
                        ClassesMenu.onClassesItem(itemStack, player, action);
                        break;
                    case INVENTORYCLASSES_DEVMOD:
                        ClassesMenu.onClassesDevItem(itemStack, player, action);
                        break;
                    case INVENTORYSUBARCHER:
                        ArcherSubClassesMenu.onMenuArcherSubItem(itemStack, player, action, event.getRawSlot());
                        break;
                    case INVENTORYSUBARCHER_DEVMOD:
                        ArcherSubClassesMenu.onMenuArcherSubDevItem(itemStack, player, action, event.getRawSlot());
                        break;
                    case INVENTORYSUBMAGE:
                        MageSubClassesMenu.onMenuMageSubItem(itemStack, player, action, event.getRawSlot());
                        break;
                    case INVENTORYSUBMAGE_DEVMOD:
                        MageSubClassesMenu.onMenuMageSubDevItem(itemStack, player, action, event.getRawSlot());
                        break;
                    case INVENTORYSUBSPIRITUALIST:
                        SpiritualistSubClassesMenu.onMenuSpiritualistSubItem(itemStack, player, action, event.getRawSlot());
                        break;
                    case INVENTORYSUBSPIRITUALIST_DEVMOD:
                        SpiritualistSubClassesMenu.onMenuSpiritualistSubDevItem(itemStack, player, action, event.getRawSlot());
                        break;
                    case INVENTORYSUBWARRIOR:
                        WarriorSubClassesMenu.onMenuWarriorSubItem(itemStack, player, action, event.getRawSlot());
                        break;
                    case INVENTORYSUBWARRIOR_DEVMOD:
                        WarriorSubClassesMenu.onMenuWarriorSubDevItem(itemStack, player, action, event.getRawSlot());
                        break;
                    default:
                        break;

                }
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (PlayerProfileManager.profiles.get(player).getInventories().contains(event.getInventory())) {
            event.setCancelled(true);
        }
    }

    public static void openInventory(final HumanEntity entity, final Inventory inventory) {
        entity.openInventory(inventory);
    }

    public static void closeInventory(final HumanEntity entity) {
        entity.closeInventory();
    }

    public static void toOpen(Player player, Inventory inventory) {
            switch (getInventoryType(inventory, player)) {
                case INVENTORYPLAYERMENU:
                    MenuManager.initializePlayerMenuItems(player, inventory);
                    break;
                case INVENTORYPLAYERMENU_DEVMOD:
                    MenuManager.initializePlayerMenuDevItems(player, inventory);
                    break;
                case INVENTORYPROFILE:
                    ProfileMenu.initializeProfileMenuItems(player, inventory);
                    break;
                case INVENTORYPROFILE_DEVMOD:
                    ProfileMenu.initializeProfileMenuDevItems(player, inventory);
                    break;
                case INVENTORYSTATS:
                    StatsMenu.initializeStatsMenuItems(player, inventory);
                    break;
                case INVENTORYSTATS_DEVMOD:
                    StatsMenu.initializeStatsMenuDevItems(player, inventory);
                    break;
                case INVENTORYSETTINGS:
                    SettingsMenu.initializeSettingsMenuItems(player, inventory);
                    break;
                case INVENTORYSETTINGS_DEVMOD:
                    SettingsMenu.initializeSettingsMenuDevItems(player, inventory);
                    break;
                case INVENTORYCLASSES:
                    ClassesMenu.initializeClassesMenuItems(player, inventory);
                    break;
                case INVENTORYCLASSES_DEVMOD:
                    ClassesMenu.initializeClassesMenuDevItems(player, inventory);
                    break;
                case INVENTORYSUBARCHER:
                    ArcherSubClassesMenu.initializeArcherSubMenuItems(player, inventory);
                    break;
                case INVENTORYSUBARCHER_DEVMOD:
                    ArcherSubClassesMenu.initializeArcherSubMenuDevItems(player, inventory);
                    break;
                case INVENTORYSUBMAGE:
                    MageSubClassesMenu.initializeMageSubMenuItems(player, inventory);
                    break;
                case INVENTORYSUBMAGE_DEVMOD:
                    MageSubClassesMenu.initializeMageSubMenuDevItems(player, inventory);
                    break;
                case INVENTORYSUBSPIRITUALIST:
                    SpiritualistSubClassesMenu.initializeSpiritualistSubMenuItems(player, inventory);
                    break;
                case INVENTORYSUBSPIRITUALIST_DEVMOD:
                    SpiritualistSubClassesMenu.initializeSpiritualistSubMenuDevItems(player, inventory);
                    break;
                case INVENTORYSUBWARRIOR:
                    WarriorSubClassesMenu.initializeWarriorSubMenuItems(player, inventory);
                    break;
                case INVENTORYSUBWARRIOR_DEVMOD:
                    WarriorSubClassesMenu.initializeWarriorSubMenuDevItems(player, inventory);
                    break;
                default: {
                    System.out.println("[OLYMPPLAYERS] (GuiManager.initialize) inventory initialize path not defined");
                    break;
                }
            }
            player.openInventory(inventory);
        }

    public static void toActualize( Player player,  Inventory inventory) {
            switch (getInventoryType(inventory, player)) {
                case INVENTORYPLAYERMENU:
                    MenuManager.initializePlayerMenuItems(player, inventory);
                    break;
                case INVENTORYPLAYERMENU_DEVMOD:
                    MenuManager.initializePlayerMenuDevItems(player, inventory);
                    break;
                case INVENTORYPROFILE:
                    ProfileMenu.initializeProfileMenuItems(player, inventory);
                    break;
                case INVENTORYPROFILE_DEVMOD:
                    ProfileMenu.initializeProfileMenuDevItems(player, inventory);
                    break;
                case INVENTORYSTATS:
                    StatsMenu.initializeStatsMenuItems(player, inventory);
                    break;
                case INVENTORYSTATS_DEVMOD:
                    StatsMenu.initializeStatsMenuDevItems(player, inventory);
                    break;
                case INVENTORYSETTINGS:
                    SettingsMenu.initializeSettingsMenuItems(player, inventory);
                    break;
                case INVENTORYSETTINGS_DEVMOD:
                    SettingsMenu.initializeSettingsMenuDevItems(player, inventory);
                    break;
                case INVENTORYCLASSES:
                    ClassesMenu.initializeClassesMenuItems(player, inventory);
                    break;
                case INVENTORYCLASSES_DEVMOD:
                    ClassesMenu.initializeClassesMenuDevItems(player, inventory);
                    break;
                case INVENTORYSUBARCHER:
                    ArcherSubClassesMenu.initializeArcherSubMenuItems(player, inventory);
                    break;
                case INVENTORYSUBARCHER_DEVMOD:
                    ArcherSubClassesMenu.initializeArcherSubMenuDevItems(player, inventory);
                    break;
                case INVENTORYSUBMAGE:
                    MageSubClassesMenu.initializeMageSubMenuItems(player, inventory);
                    break;
                case INVENTORYSUBMAGE_DEVMOD:
                    MageSubClassesMenu.initializeMageSubMenuDevItems(player, inventory);
                    break;
                case INVENTORYSUBSPIRITUALIST:
                    SpiritualistSubClassesMenu.initializeSpiritualistSubMenuItems(player, inventory);
                    break;
                case INVENTORYSUBSPIRITUALIST_DEVMOD:
                    SpiritualistSubClassesMenu.initializeSpiritualistSubMenuDevItems(player, inventory);
                    break;
                case INVENTORYSUBWARRIOR:
                    WarriorSubClassesMenu.initializeWarriorSubMenuItems(player, inventory);
                    break;
                case INVENTORYSUBWARRIOR_DEVMOD:
                    WarriorSubClassesMenu.initializeWarriorSubMenuDevItems(player, inventory);
                    break;
                default: {
                    System.out.println("[OLYMPPLAYERS] (GuiManager.initialize) inventory initialize path not defined");
                    break;
                }
            }
    }

    public static List<String> getListNameInventory(Player player) {
        List<String> inventoryList = new ArrayList<String>();
        for (int i = 0; i < PlayerProfileManager.profiles.get(player).getInventories().size(); ++i) {
            inventoryList.add(PlayerProfileManager.profiles.get(player).getInventories().get(i).getType().name());
        }
        return inventoryList;
    }

    public static InventoryTypeList getInventoryType(Inventory inventory, Player player) {

        int inventorySize = inventory.getSize();
        InventoryTypeList[] values;
        for (InventoryTypeList type : InventoryTypeList.values()) {
            if (PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(type) == inventory) {
                return type;
            }
        }
        return InventoryTypeList.INVENTORYNOT;
    }

    public enum InventoryTypeList {
        INVENTORYNOT("INVENTORYNOT", 9, "null", 0),
        INVENTORYPLAYERMENU("INVENTORYPLAYERMENU", 36, "Player Menu", 1),
        INVENTORYPLAYERMENU_DEVMOD("INVENTORYPLAYERMENU_DEVMOD", 36, "Player Menu " + ChatColor.GOLD + ChatColor.BOLD + "DEVMOD", 2),
        INVENTORYPROFILE("INVENTORYPROFILE", 54, "Profile", 3),
        INVENTORYPROFILE_DEVMOD("INVENTORYPROFILE_DEVMOD", 54, "Profile " + ChatColor.GOLD + ChatColor.BOLD + "DEVMOD", 4),
        INVENTORYSTATS("INVENTORYSTATS", 54, "Stats", 5),
        INVENTORYSTATS_DEVMOD("INVENTORYSTATS_DEVMOD", 54, "Stats " + ChatColor.GOLD + ChatColor.BOLD + "DEVMOD", 6),
        INVENTORYSETTINGS("INVENTORYSETTINGS", 54, "Settings", 7),
        INVENTORYSETTINGS_DEVMOD("INVENTORYSETTINGS_DEVMOD", 54, "Settings " + ChatColor.GOLD + ChatColor.BOLD + "DEVMOD", 8),
        INVENTORYCLASSES("INVENTORYCLASSES", 27, "Class choice", 9),
        INVENTORYCLASSES_DEVMOD("INVENTORYCLASSES_DEVMOD", 36, "Class choice " + ChatColor.GOLD + ChatColor.BOLD + "DEVMOD", 10),
        INVENTORYSUBARCHER("INVENTORYSUBARCHER", 36, "Archer subclass choice ", 11),
        INVENTORYSUBARCHER_DEVMOD("INVENTORYSUBARCHER_DEVMOD", 36, "Archer subclass choice " + ChatColor.GOLD + ChatColor.BOLD + "DEVMOD", 12),
        INVENTORYSUBMAGE("INVENTORYSUBMAGE", 36, "Mage subclass choice ", 13),
        INVENTORYSUBMAGE_DEVMOD("INVENTORYSUBMAGE_DEVMOD", 36, "Mage subclass choice " + ChatColor.GOLD + ChatColor.BOLD + "DEVMOD", 14),
        INVENTORYSUBSPIRITUALIST("INVENTORYSUBSPIRITUALIST", 36, "Spiritualist subclass choice ", 15),
        INVENTORYSUBSPIRITUALIST_DEVMOD("INVENTORYSUBSPIRITUALIST_DEVMOD", 36, "Spiritualist subclass choice " + ChatColor.GOLD + ChatColor.BOLD + "DEVMOD", 16),
        INVENTORYSUBWARRIOR("INVENTORYSUBWARRIOR", 36, "Warrior subclass choice ", 17),
        INVENTORYSUBWARRIOR_DEVMOD("INVENTORYSUBWARRIOR_DEVMOD", 36, "Warrior subclass choice " + ChatColor.GOLD + ChatColor.BOLD + "DEVMOD", 18);

        private int slotNumber;
        private String inventoryName;
        private int ordinal;

        private InventoryTypeList(String name, int slotNumber, String inventoryName, int ordinal) {
            this.slotNumber = slotNumber;
            this.inventoryName = inventoryName;
            this.ordinal = ordinal;
        }

        public Inventory createInventory(Player player) {
            return Bukkit.createInventory(player, this.slotNumber, this.inventoryName);
        }

        public int getSlotNumber() {
            return this.slotNumber;
        }

        public String getInventoryName() {
            return this.inventoryName;
        }

        public int getOrdinal() {
            return this.ordinal;
        }

        public static InventoryTypeList getOrdinalToInventoryType(int ordinal) {
            for (InventoryTypeList inv : InventoryTypeList.values()) {
                if (inv.getOrdinal() == ordinal) {
                    return inv;
                }
            }
            return null;
        }
    }
}

