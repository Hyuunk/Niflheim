package fr.hyu.niflheim.gui;

import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import java.util.List;
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

public class GuiManager implements Listener
{
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        final ItemStack itemStack = event.getCurrentItem();
        final Player player = (Player)event.getWhoClicked();
        final InventoryAction action = event.getAction();
        if (PlayerProfileManager.profiles.get(player).getInventories().contains(event.getInventory())) {
            event.setCancelled(true);
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                return;
            }
            if (itemStack.getType() == Material.BARRIER) {
                closeInventory((HumanEntity)player);
            }
            else {
                switch (getInventoryType(event.getInventory())) {
                    case INVENTORYPLAYERMENU: {
                        MenuManager.onMenuItem(itemStack, player, action);
                        break;
                    }
                    case INVENTORYPLAYERMENU_DEVMOD: {
                        MenuManager.onMenuDevItem(itemStack, player, action);
                        break;
                    }
                    case INVENTORYPROFILE: {
                        MenuManager.onProfileItem(itemStack, player, action);
                        break;
                    }
                    case INVENTORYPROFILE_DEVMOD: {
                        MenuManager.onProfileDevItem(itemStack, player, action);
                        break;
                    }
                    case INVENTORYADMINSHOP: {
                        MenuManager.onAdminShopItem(itemStack, player, action);
                        break;
                    }
                    case INVENTORYADMINSHOP_ORES_1: {
                        MenuManager.onAdminShop_1Item(itemStack, player, action);
                        break;
                    }
                    case INVENTORYADMINSHOP_NATURES_1: {
                        MenuManager.onAdminShop_1Item(itemStack, player, action);
                        break;
                    }
                    case INVENTORYADMINSHOP_NETHER_1: {
                        MenuManager.onAdminShop_1Item(itemStack, player, action);
                        break;
                    }
                    case INVENTORYADMINSHOP_END_1: {
                        MenuManager.onAdminShop_1Item(itemStack, player, action);
                        break;
                    }
                    case INVENTORYSETTINGS: {
                        MenuManager.onSettingsItem(itemStack, player, action);
                        break;
                    }
                    case INVENTORYSETTINGS_DEVMOD: {
                        MenuManager.onSettingsDevItem(itemStack, player, action);
                        break;
                    }
                    default: {
                        System.out.println("[OLYMPPLAYERS] (GuiManager.onInventoryClick) undefined button path");
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent event) {
        final Player player = (Player)event.getWhoClicked();
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

    public static void toOpen(final Player player, final Inventory inventory) {
        if (isInventoryType(inventory)) {
            switch (getInventoryType(inventory)) {
                case INVENTORYPLAYERMENU: {
                    MenuManager.initializePlayerMenuItems(player, inventory);
                    break;
                }
                case INVENTORYPLAYERMENU_DEVMOD: {
                    MenuManager.initializePlayerMenuDevItems(player, inventory);
                    break;
                }
                case INVENTORYPROFILE: {
                    MenuManager.initializeProfileMenuItems(player, inventory);
                    break;
                }
                case INVENTORYPROFILE_DEVMOD: {
                    MenuManager.initializeProfileMenuDevItems(player, inventory);
                    break;
                }
                case INVENTORYADMINSHOP: {
                    MenuManager.initializeAdminShopMenuItems(player, inventory);
                    break;
                }
 /*               case INVENTORYADMINSHOP_ORES_1: {
                    MenuManager.initializeAdminShop_Ores_1MenuItems(player, inventory);
                    break;
                }
                case INVENTORYADMINSHOP_NATURES_1: {
                    MenuManager.initializeAdminShop_Natures_1MenuItems(player, inventory);
                    break;
                }
                case INVENTORYADMINSHOP_NETHER_1: {
                    MenuManager.initializeAdminShop_Nether_1MenuItems(player, inventory);
                    break;
                }
                case INVENTORYADMINSHOP_END_1: {
                    MenuManager.initializeAdminShop_End_1MenuItems(player, inventory);
                    break;
                }

  */
                case INVENTORYSETTINGS: {
                    MenuManager.initializeSettingsMenuItems(player, inventory);
                    break;
                }
                case INVENTORYSETTINGS_DEVMOD: {
                    MenuManager.initializeSettingsMenuDevItems(player, inventory);
                    break;
                }
                default: {
                    System.out.println("[OLYMPPLAYERS] (GuiManager.initialize) inventory initialize path not defined");
                    break;
                }
            }
            player.openInventory(inventory);
        }
        else {
            System.out.println("[OLYMPPLAYERS] (GuiManager.initialize) inventoryType not recognized");
        }
    }

    public static void toActualize(final Player player, final Inventory inventory) {
        if (isInventoryType(inventory)) {
            switch (getInventoryType(inventory)) {
                case INVENTORYPLAYERMENU: {
                    MenuManager.initializePlayerMenuItems(player, inventory);
                    break;
                }
                case INVENTORYPLAYERMENU_DEVMOD: {
                    MenuManager.initializePlayerMenuDevItems(player, inventory);
                    break;
                }
                case INVENTORYPROFILE: {
                    MenuManager.initializeProfileMenuItems(player, inventory);
                    break;
                }
                case INVENTORYPROFILE_DEVMOD: {
                    MenuManager.initializeProfileMenuDevItems(player, inventory);
                    break;
                }
                case INVENTORYADMINSHOP: {
                    MenuManager.initializeAdminShopMenuItems(player, inventory);
                    break;
                }
                /*
                case INVENTORYADMINSHOP_ORES_1: {
                    MenuManager.initializeAdminShop_Ores_1MenuItems(player, inventory);
                    break;
                }
                case INVENTORYADMINSHOP_NATURES_1: {
                    MenuManager.initializeAdminShop_Natures_1MenuItems(player, inventory);
                    break;
                }
                case INVENTORYADMINSHOP_NETHER_1: {
                    MenuManager.initializeAdminShop_Nether_1MenuItems(player, inventory);
                    break;
                }
                case INVENTORYADMINSHOP_END_1: {
                    MenuManager.initializeAdminShop_End_1MenuItems(player, inventory);
                    break;
                }

                 */
                case INVENTORYSETTINGS: {
                    MenuManager.initializeSettingsMenuItems(player, inventory);
                    break;
                }
                case INVENTORYSETTINGS_DEVMOD: {
                    MenuManager.initializeSettingsMenuDevItems(player, inventory);
                    break;
                }
                default: {
                    System.out.println("[OLYMPPLAYERS] (GuiManager.initialize) inventory initialize path not defined");
                    break;
                }
            }
        }
        else {
            System.out.println("[OLYMPPLAYERS] (GuiManager.initialize) inventoryType not recognized");
        }
    }

    public static List<String> getListNameInventory(Player player) {
        List<String> inventoryList = new ArrayList<String>();
        for (int i = 0; i < PlayerProfileManager.profiles.get(player).getInventories().size(); ++i) {
            inventoryList.add(PlayerProfileManager.profiles.get(player).getInventories().get(i).getType().name());
        }
        return inventoryList;
    }

    public static boolean isInventoryType(Inventory inventory) {
        String inventoryName = inventory.getType().name();
        int inventorySize = inventory.getSize();
        InventoryTypeList[] values;
        for (int length = (values = InventoryTypeList.values()).length, i = 0; i < length; ++i) {
            InventoryTypeList inv = values[i];
            if (inv.getSlotNumber() == inventorySize && inv.getInventoryName() == inventoryName) {
                return true;
            }
        }
        return false;
    }

    public static InventoryTypeList getInventoryType(final Inventory inventory) {
        final String inventoryName = inventory.getType().name();
        final int inventorySize = inventory.getSize();
        InventoryTypeList[] values;
        for (int length = (values = InventoryTypeList.values()).length, i = 0; i < length; ++i) {
            final InventoryTypeList inv = values[i];
            if (inv.getSlotNumber() == inventorySize && inv.getInventoryName() == inventoryName) {
                return inv;
            }
        }
        return InventoryTypeList.INVENTORYNOT;
    }

    public enum InventoryTypeList
    {
        INVENTORYNOT("INVENTORYNOT", 9, "null"),
        INVENTORYPLAYERMENU("INVENTORYPLAYERMENU", 36, "Player Menu"),
        INVENTORYPLAYERMENU_DEVMOD("INVENTORYPLAYERMENU_DEVMOD", 36, "Player Menu (devmod)"),
        INVENTORYPROFILE("INVENTORYPROFILE", 54, "Profile"),
        INVENTORYPROFILE_DEVMOD("INVENTORYPROFILE_DEVMOD", 54, "Profile (devmod)"),
        INVENTORYADMINSHOP("INVENTORYADMINSHOP", 54, "AdminShop"),
        INVENTORYADMINSHOP_DEVMOD("INVENTORYADMINSHOP_DEVMOD", 54, "AdminShop (devmod)"),
        INVENTORYADMINSHOP_ORES_1("INVENTORYADMINSHOP_ORES_1", 54, "AdminShop Ores"),
        INVENTORYADMINSHOP_NATURES_1("INVENTORYADMINSHOP_NATURES_1", 54, "AdminShop Natures"),
        INVENTORYADMINSHOP_NETHER_1("INVENTORYADMINSHOP_NETHER_1",54, "AdminShop Nether"),
        INVENTORYADMINSHOP_END_1("INVENTORYADMINSHOP_END_1", 54, "AdminShop End"),
        INVENTORYSETTINGS("INVENTORYSETTINGS", 54, "Settings"),
        INVENTORYSETTINGS_DEVMOD("INVENTORYSETTINGS_DEVMOD", 54, "Settings (devmod)");

        private int slotNumber;
        private String inventoryName;

        private InventoryTypeList(final String name, final int slotNumber, final String inventoryName) {
            this.slotNumber = slotNumber;
            this.inventoryName = inventoryName;
        }

        public Inventory createInventory(final Player player) {
            return Bukkit.createInventory(player, this.slotNumber, this.inventoryName);
        }

        public int getSlotNumber() {
            return this.slotNumber;
        }

        public String getInventoryName() {
            return this.inventoryName;
        }
    }
}
