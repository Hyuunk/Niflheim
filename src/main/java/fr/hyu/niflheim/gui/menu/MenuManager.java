package fr.hyu.niflheim.gui.menu;


import fr.hyu.Main;
import fr.hyu.niflheim.gui.GuiItem;
import fr.hyu.niflheim.gui.GuiManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import fr.hyu.niflheimPermissions.player.PlayerProfile;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;


import java.util.ArrayList;
import java.util.List;

public class MenuManager implements Listener
{
    @EventHandler
    public void onClickMenu(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.getItem() != null && event.getItem().getType() == Material.NETHER_STAR && event.getItem().getItemMeta().getDisplayName().equals(String.valueOf(ChatColor.GOLD.toString()) + ChatColor.BOLD + "MENU")) {
            try {
                final List<String> loresList = new ArrayList<String>();
                loresList.add("Cliquez sur l'étoile du nether pour ouvrir votre menu de joueur.");
                if (event.getItem().getItemMeta().getLore().equals(loresList)) {
                    GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU));
                    return;
                }
            }
            catch (NullPointerException ex) {}
        }
    }

    @EventHandler
    public void onInventoryInteractMenu(final InventoryClickEvent event) {
        if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.NETHER_STAR && event.getCurrentItem().getItemMeta().getDisplayName() != null && event.getCurrentItem().getItemMeta().getDisplayName().equals(String.valueOf(ChatColor.GOLD.toString()) + ChatColor.BOLD + "MENU")) {
            try {
                final List<String> loresList = new ArrayList<String>();
                loresList.add("Cliquez sur l'étoile du nether pour ouvrir votre menu de joueur.");
                if (event.getCurrentItem().getItemMeta().getLore().equals(loresList)) {
                    event.setCancelled(true);
                }
            }
            catch (NullPointerException ex) {}
        }
    }

    @EventHandler
    public void onPlayerDropMenu(final PlayerDropItemEvent event) {
        if (event.getItemDrop() != null && event.getItemDrop().getType() == EntityType.DROPPED_ITEM) {
            final Item dropped = event.getItemDrop();
            if (dropped.getItemStack().getType().equals((Object)Material.NETHER_STAR) && dropped.getItemStack().getItemMeta().getDisplayName().equals(String.valueOf(ChatColor.GOLD.toString()) + ChatColor.BOLD + "MENU")) {
                try {
                    final List<String> loresList = new ArrayList<String>();
                    loresList.add("Cliquez sur l'étoile du nether pour ouvrir votre menu de joueur.");
                    if (dropped.getItemStack().getItemMeta().getLore().equals(loresList)) {
                        event.setCancelled(true);
                    }
                }
                catch (NullPointerException ex) {}
            }
        }
    }
    public static void onMenuItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case PLAYER_HEAD: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPROFILE));
                break;
            }
            case BOOKSHELF: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSTATS));
                break;
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
            case BOOKSHELF: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSTATS_DEVMOD));
                break;
            }
            case GRASS: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU));
                break;
            }
        }
    }


    public static void initializePlayerMenuItems(final Player player, final Inventory inventory) {
        inventory.setItem(4, GuiItem.createHeadItem(Material.PLAYER_HEAD, player, ChatColor.GREEN + "Profil","", ChatColor.GRAY + "Rang " + PlayerProfileManager.profiles.get(player).getRank().getColor() + PlayerProfileManager.profiles.get(player).getRank().getDisplayName(), ChatColor.GRAY + "Classe " + PlayerProfileManager.profiles.get(player).getClasses().getColor() + PlayerProfileManager.profiles.get(player).getClasses().getDisplayName(),"" , ChatColor.GRAY + "Niveau " + PlayerProfile.getStat(player, PlayerProfile.Stat.LEVEL), String.valueOf(ChatColor.RED.toString()) + "\u2764 Vitalit\u00e9 " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.VITALITYNATIVE), String.valueOf(ChatColor.BLUE.toString()) + "\u2748 Defence " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.DEFENCENATIVE), String.valueOf(ChatColor.GREEN.toString()) + "\u03a8 Force " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.STRENGTHNATIVE), String.valueOf(ChatColor.YELLOW.toString()) + "\u27b6 Dexterit\u00e9 " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.DEXTIRITYNATIVE), String.valueOf(ChatColor.GOLD.toString()) + String.valueOf(ChatColor.BLUE.toString()) + "\u2745 Intelligence " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.INTELLIGENCENATIVE), String.valueOf(ChatColor.DARK_PURPLE.toString()) + "\u29fe Foi " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.FAITHNATIVE), String.valueOf(ChatColor.WHITE.toString()) + "\u2727 Agilit\u00e9 " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.AGILITYNATIVE), String.valueOf(ChatColor.LIGHT_PURPLE.toString()) + "\u2756 Karma " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.KARMA)));
         inventory.setItem(10, GuiItem.createGuiItem(new ItemStack(Material.BOOKSHELF), ChatColor.BLUE + "Statistiques", ""));
        inventory.setItem(16, GuiItem.createGuiItem(new ItemStack(Material.MAP), ChatColor.YELLOW + "Map", ""));
        inventory.setItem(31, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
        inventory.setItem(32, GuiItem.createGuiItem(new ItemStack(Material.COMPARATOR), ChatColor.RED + "Paramètres", ""));
        if (player.isOp()) {
            inventory.setItem(30, GuiItem.createGuiItem(new ItemStack(Material.COMMAND_BLOCK), ChatColor.GOLD + "DevMod", ""));
        }
    }

    ///////DEV//////

    public static void initializePlayerMenuDevItems(final Player player, final Inventory inventory) {
        inventory.setItem(4, GuiItem.createHeadItem(Material.PLAYER_HEAD, player, ChatColor.GREEN + "Profil", "", ChatColor.GRAY + "Rang " + PlayerProfileManager.profiles.get(player).getRank().getColor() + PlayerProfileManager.profiles.get(player).getRank().getDisplayName(), ChatColor.GRAY + "Classe " + PlayerProfileManager.profiles.get(player).getClasses().getColor() + PlayerProfileManager.profiles.get(player).getClasses().getDisplayName(),"" , ChatColor.GRAY + "Niveau " + PlayerProfile.getStat(player, PlayerProfile.Stat.LEVEL), String.valueOf(ChatColor.RED.toString()) + "\u2764 Vitalit\u00e9 " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.VITALITYNATIVE), String.valueOf(ChatColor.BLUE.toString()) + "\u2748 Defence " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.DEFENCENATIVE), String.valueOf(ChatColor.GREEN.toString()) + "\u03a8 Force " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.STRENGTHNATIVE), String.valueOf(ChatColor.YELLOW.toString()) + "\u27b6 Dexterit\u00e9 " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.DEXTIRITYNATIVE), String.valueOf(ChatColor.GOLD.toString()) + String.valueOf(ChatColor.BLUE.toString()) + "\u2745 Intelligence " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.INTELLIGENCENATIVE), String.valueOf(ChatColor.DARK_PURPLE.toString()) + "\u29fe Foi " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.FAITHNATIVE), String.valueOf(ChatColor.WHITE.toString()) + "\u2727 Agilit\u00e9 " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.AGILITYNATIVE), String.valueOf(ChatColor.LIGHT_PURPLE.toString()) + "\u2756 Karma " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.KARMA)));
        inventory.setItem(10, GuiItem.createGuiItem(new ItemStack(Material.BOOKSHELF), ChatColor.BLUE + "Statistiques", ""));
        inventory.setItem(16, GuiItem.createGuiItem(new ItemStack(Material.MAP), ChatColor.YELLOW + "Map", ""));
        inventory.setItem(31, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
        inventory.setItem(32, GuiItem.createGuiItem(new ItemStack(Material.COMPARATOR), ChatColor.RED + "Paramètres", ""));
        if (player.isOp()) {
            inventory.setItem(30, GuiItem.createGuiItem(new ItemStack(Material.GRASS), ChatColor.GREEN + "NormalMod", ""));
        }
    }
}
