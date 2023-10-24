package fr.hyu.niflheim.gui;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import fr.hyu.niflheimPermissions.player.PlayerProfile;
import org.bukkit.inventory.Inventory;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;

public class MenuManager implements Listener
{
    public static void onMenuItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case PLAYER_HEAD: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPROFILE));
                break;
            }
            case HOPPER: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYADMINSHOP));
            }
            case COMPARATOR: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSETTINGS));
                break;
            }
            case COMMAND_BLOCK: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU_DEVMOD));
                break;
            }
        }
    }

    public static void onJobsItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case ARROW: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU));
                break;
            }
        }
    }

    public static void onProfileItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case ARROW: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU));
                break;
            }
        }
    }

    public static void onAdminShopItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {

            case ARROW: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU));
                break;
            }
        }
    }

    public static void onAdminShop_1Item(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case ARROW: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYADMINSHOP));
                break;
            }
            default: {
                onAdminShopItemAction(player, material, action);
                break;
            }
        }
    }

    public static void onSettingsItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case ARROW: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU));
                break;
            }
        }
    }

    public static void onMenuDevItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case PLAYER_HEAD: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPROFILE_DEVMOD));
            }
            case COMPARATOR: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSETTINGS_DEVMOD));
                break;
            }
            case GRASS: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU));
                break;
            }
        }
    }

    public static void onProfileDevItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case ARROW: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU_DEVMOD));
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

    private static void onAdminShopItemAction(final Player player, final Material material, final InventoryAction action) {
        switch (action) {
            case PICKUP_ALL: {
    //            Buying_SellingManager.toBuy(player, material);
                break;
            }
            case PICKUP_HALF: {
    //            Buying_SellingManager.toSell(player, material);
                break;
            }
        }
        GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYADMINSHOP_ORES_1));
    }

    public static void initializePlayerMenuItems(final Player player, final Inventory inventory) {
        inventory.setItem(4, GuiItem.createHeadItem(Material.PLAYER_HEAD, player, ChatColor.GRAY + "Profile: " + ChatColor.GREEN + player.getDisplayName(), String.valueOf(ChatColor.GOLD.toString()) + "\u26c1 Gold: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.GOLD), String.valueOf(ChatColor.LIGHT_PURPLE.toString()) + "\u2756 Karma: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.KARMA)));
        inventory.setItem(10, GuiItem.createGuiItem(new ItemStack(Material.HOPPER), ChatColor.DARK_GRAY + "AdminShop", ""));
        inventory.setItem(16, GuiItem.createGuiItem(new ItemStack(Material.MAP), ChatColor.YELLOW + "Map", ""));
        inventory.setItem(31, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
        inventory.setItem(32, GuiItem.createGuiItem(new ItemStack(Material.COMPARATOR), ChatColor.RED + "Param\u00e8tres", ""));
        if (player.isOp()) {
            inventory.setItem(30, GuiItem.createGuiItem(new ItemStack(Material.COMMAND_BLOCK), ChatColor.GOLD + "DevMod", ""));
        }
    }

    public static void initializeProfileMenuItems(final Player player, final Inventory inventory) {
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }

    public static void initializeAdminShopMenuItems(final Player player, final Inventory inventory) {
        inventory.setItem(4, GuiItem.createHeadItem(Material.PLAYER_HEAD, player, ChatColor.GRAY + "Profile: " + ChatColor.GREEN + player.getDisplayName(), String.valueOf(ChatColor.GOLD.toString()) + "\u26c1 Gold: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.GOLD), String.valueOf(ChatColor.LIGHT_PURPLE.toString()) + "\u2756 Karma: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.KARMA)));
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }

  /*  public static void initializeAdminShop_Ores_1MenuItems(final Player player, final Inventory inventory) {
        inventory.setItem(4, GuiItem.createHeadItem(Material.SKULL_ITEM, player, ChatColor.GRAY + "Profile: " + ChatColor.GREEN + player.getDisplayName(), String.valueOf(ChatColor.GOLD.toString()) + "\u26c1 Gold: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.GOLD), String.valueOf(ChatColor.LIGHT_PURPLE.toString()) + "\u2756 Karma: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.KARMA)));
        Material[] values;
        for (int length = (values = Material.values()).length, i = 0; i < length; ++i) {
            final Material material = values[i];
            if (AdminShopManager.itemProfiles.get(material).getCategories().equalsIgnoreCase("ores") && AdminShopManager.itemProfiles.get(material).getPage() == 1) {
                final ItemStack item = new ItemStack(material);
                item.setAmount(AdminShopManager.itemProfiles.get(material).getAmount());
                inventory.setItem(AdminShopManager.itemProfiles.get(material).getSlot(), GuiItem.createGuiItem(item, material.name(), "", ChatColor.GRAY + "Â» Clic gauche pour acheter " + AdminShopManager.itemProfiles.get(material).getAmount() + " de " + ChatColor.GRAY + material.name().toLowerCase() + ChatColor.GRAY + ".", new StringBuilder().append(ChatColor.LIGHT_PURPLE).append(AdminShopManager.itemProfiles.get(material).getAmount()).append("x").append(ChatColor.GRAY).append(" pour ").append(ChatColor.GOLD).append(AdminShopManager.itemProfiles.get(material).getPriceToBuy()).append(" \u26c1").append(ChatColor.GRAY).append(".").toString(), "", ChatColor.GRAY + "Â» Clic droit pour vendre " + AdminShopManager.itemProfiles.get(material).getAmount() + " de " + ChatColor.GRAY + material.name().toLowerCase() + ChatColor.GRAY + ".", new StringBuilder().append(ChatColor.LIGHT_PURPLE).append(AdminShopManager.itemProfiles.get(material).getAmount()).append("x").append(ChatColor.GRAY).append(" pour ").append(ChatColor.GOLD).append(AdminShopManager.itemProfiles.get(material).getPriceToSell()).append(" \u26c1").append(ChatColor.GRAY).append(".").toString()));
            }
        }
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }

    public static void initializeAdminShop_Natures_1MenuItems(final Player player, final Inventory inventory) {
        inventory.setItem(4, GuiItem.createHeadItem(Material.SKULL_ITEM, player, ChatColor.GRAY + "Profile: " + ChatColor.GREEN + player.getDisplayName(), String.valueOf(ChatColor.GOLD.toString()) + "\u26c1 Gold: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.GOLD), String.valueOf(ChatColor.LIGHT_PURPLE.toString()) + "\u2756 Karma: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.KARMA)));
        Material[] values;
        for (int length = (values = Material.values()).length, i = 0; i < length; ++i) {
            final Material material = values[i];
            if (AdminShopManager.itemProfiles.get(material).getCategories().equalsIgnoreCase("natures") && AdminShopManager.itemProfiles.get(material).getPage() == 1) {
                final ItemStack item = new ItemStack(material);
                item.setAmount(AdminShopManager.itemProfiles.get(material).getAmount());
                inventory.setItem(AdminShopManager.itemProfiles.get(material).getSlot(), GuiItem.createGuiItem(item, material.name(), "", ChatColor.GRAY + "Â» Clic gauche pour acheter " + AdminShopManager.itemProfiles.get(material).getAmount() + " de " + ChatColor.GRAY + material.name().toLowerCase() + ChatColor.GRAY + ".", new StringBuilder().append(ChatColor.LIGHT_PURPLE).append(AdminShopManager.itemProfiles.get(material).getAmount()).append("x").append(ChatColor.GRAY).append(" pour ").append(ChatColor.GOLD).append(AdminShopManager.itemProfiles.get(material).getPriceToBuy()).append(" \u26c1").append(ChatColor.GRAY).append(".").toString(), "", ChatColor.GRAY + "Â» Clic droit pour vendre " + AdminShopManager.itemProfiles.get(material).getAmount() + " de " + ChatColor.GRAY + material.name().toLowerCase() + ChatColor.GRAY + ".", new StringBuilder().append(ChatColor.LIGHT_PURPLE).append(AdminShopManager.itemProfiles.get(material).getAmount()).append("x").append(ChatColor.GRAY).append(" pour ").append(ChatColor.GOLD).append(AdminShopManager.itemProfiles.get(material).getPriceToSell()).append(" \u26c1").append(ChatColor.GRAY).append(".").toString()));
            }
        }
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }

    public static void initializeAdminShop_Nether_1MenuItems(final Player player, final Inventory inventory) {
        inventory.setItem(4, GuiItem.createHeadItem(Material.SKULL_ITEM, player, ChatColor.GRAY + "Profile: " + ChatColor.GREEN + player.getDisplayName(), String.valueOf(ChatColor.GOLD.toString()) + "\u26c1 Gold: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.GOLD), String.valueOf(ChatColor.LIGHT_PURPLE.toString()) + "\u2756 Karma: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.KARMA)));
        Material[] values;
        for (int length = (values = Material.values()).length, i = 0; i < length; ++i) {
            final Material material = values[i];
            if (AdminShopManager.itemProfiles.get(material).getCategories().equalsIgnoreCase("nether") && AdminShopManager.itemProfiles.get(material).getPage() == 1) {
                final ItemStack item = new ItemStack(material);
                item.setAmount(AdminShopManager.itemProfiles.get(material).getAmount());
                inventory.setItem(AdminShopManager.itemProfiles.get(material).getSlot(), GuiItem.createGuiItem(item, material.name(), "", ChatColor.GRAY + "Â» Clic gauche pour acheter " + AdminShopManager.itemProfiles.get(material).getAmount() + " de " + ChatColor.GRAY + material.name().toLowerCase() + ChatColor.GRAY + ".", new StringBuilder().append(ChatColor.LIGHT_PURPLE).append(AdminShopManager.itemProfiles.get(material).getAmount()).append("x").append(ChatColor.GRAY).append(" pour ").append(ChatColor.GOLD).append(AdminShopManager.itemProfiles.get(material).getPriceToBuy()).append(" \u26c1").append(ChatColor.GRAY).append(".").toString(), "", ChatColor.GRAY + "Â» Clic droit pour vendre " + AdminShopManager.itemProfiles.get(material).getAmount() + " de " + ChatColor.GRAY + material.name().toLowerCase() + ChatColor.GRAY + ".", new StringBuilder().append(ChatColor.LIGHT_PURPLE).append(AdminShopManager.itemProfiles.get(material).getAmount()).append("x").append(ChatColor.GRAY).append(" pour ").append(ChatColor.GOLD).append(AdminShopManager.itemProfiles.get(material).getPriceToSell()).append(" \u26c1").append(ChatColor.GRAY).append(".").toString()));
            }
        }
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }

    public static void initializeAdminShop_End_1MenuItems(final Player player, final Inventory inventory) {
        inventory.setItem(4, GuiItem.createHeadItem(Material.SKULL_ITEM, player, ChatColor.GRAY + "Profile: " + ChatColor.GREEN + player.getDisplayName(), String.valueOf(ChatColor.GOLD.toString()) + "\u26c1 Gold: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.GOLD), String.valueOf(ChatColor.LIGHT_PURPLE.toString()) + "\u2756 Karma: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.KARMA)));
        Material[] values;
        for (int length = (values = Material.values()).length, i = 0; i < length; ++i) {
            final Material material = values[i];
            if (AdminShopManager.itemProfiles.get(material).getCategories().equalsIgnoreCase("end") && AdminShopManager.itemProfiles.get(material).getPage() == 1) {
                final ItemStack item = new ItemStack(material);
                item.setAmount(AdminShopManager.itemProfiles.get(material).getAmount());
                inventory.setItem(AdminShopManager.itemProfiles.get(material).getSlot(), GuiItem.createGuiItem(item, material.name(), "", ChatColor.GRAY + "Â» Clic gauche pour acheter " + AdminShopManager.itemProfiles.get(material).getAmount() + " de " + ChatColor.GRAY + material.name().toLowerCase() + ChatColor.GRAY + ".", new StringBuilder().append(ChatColor.LIGHT_PURPLE).append(AdminShopManager.itemProfiles.get(material).getAmount()).append("x").append(ChatColor.GRAY).append(" pour ").append(ChatColor.GOLD).append(AdminShopManager.itemProfiles.get(material).getPriceToBuy()).append(" \u26c1").append(ChatColor.GRAY).append(".").toString(), "", ChatColor.GRAY + "Â» Clic droit pour vendre " + AdminShopManager.itemProfiles.get(material).getAmount() + " de " + ChatColor.GRAY + material.name().toLowerCase() + ChatColor.GRAY + ".", new StringBuilder().append(ChatColor.LIGHT_PURPLE).append(AdminShopManager.itemProfiles.get(material).getAmount()).append("x").append(ChatColor.GRAY).append(" pour ").append(ChatColor.GOLD).append(AdminShopManager.itemProfiles.get(material).getPriceToSell()).append(" \u26c1").append(ChatColor.GRAY).append(".").toString()));
            }
        }
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }
*/
    public static void initializeSettingsMenuItems(final Player player, final Inventory inventory) {
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }

    public static void initializePlayerMenuDevItems(final Player player, final Inventory inventory) {
        inventory.setItem(4, GuiItem.createHeadItem(Material.PLAYER_HEAD, player, ChatColor.GREEN + "Profile", String.valueOf(ChatColor.LIGHT_PURPLE.toString()) + "\u2756 Karma: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.KARMA), String.valueOf(ChatColor.GOLD.toString()) + "\u26c1 Gold: " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.GOLD)));
        inventory.setItem(10, GuiItem.createGuiItem(new ItemStack(Material.HOPPER), ChatColor.DARK_GRAY + "AdminShop", ""));
        inventory.setItem(16, GuiItem.createGuiItem(new ItemStack(Material.MAP), ChatColor.YELLOW + "Map", ""));
        inventory.setItem(31, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
        inventory.setItem(32, GuiItem.createGuiItem(new ItemStack(Material.COMPARATOR), ChatColor.RED + "Param\u00e8tres", ""));
        if (player.isOp()) {
            inventory.setItem(30, GuiItem.createGuiItem(new ItemStack(Material.GRASS), ChatColor.GREEN + "NormalMod", ""));
        }
    }

    public static void initializeProfileMenuDevItems(final Player player, final Inventory inventory) {
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }

    public static void initializeSettingsMenuDevItems(final Player player, final Inventory inventory) {
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }
}
