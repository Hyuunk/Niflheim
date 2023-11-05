package fr.hyu.niflheim.gui.menu.stats;

import fr.hyu.Main;
import fr.hyu.niflheim.gui.GuiItem;
import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheimPermissions.player.PlayerProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

import static fr.hyu.niflheimMMO.statsApplicator.AgilityApplicator.agilityCalculator;
import static fr.hyu.niflheimMMO.statsApplicator.DefenceApplicator.defenceCalculator;
import static fr.hyu.niflheimMMO.statsApplicator.DexterityApplicator.dexterityCalculator;

public class StatsMenu {

    public static void onMenuStatsItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case RED_TERRACOTTA:
                onActionItem(player, PlayerProfile.Stat.VITALITYNATIVE, action, 19);
                break;
            case GREEN_TERRACOTTA:
                onActionItem(player, PlayerProfile.Stat.STRENGTHNATIVE, action, 21);
                break;
            case YELLOW_TERRACOTTA:
                onActionItem(player, PlayerProfile.Stat.DEXTIRITYNATIVE, action, 23);
                break;
            case WHITE_TERRACOTTA:
                onActionItem(player, PlayerProfile.Stat.AGILITYNATIVE, action, 25);
                break;
            case BLUE_TERRACOTTA:
                onActionItem(player, PlayerProfile.Stat.INTELLIGENCENATIVE, action, 30);
                break;
            case PURPLE_TERRACOTTA:
                onActionItem(player, PlayerProfile.Stat.FAITHNATIVE, action, 32);
                break;
            case ARROW: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU));
                break;
            }
        }
    }


    public static void onMenuStatsDevItem(final ItemStack itemStack, final Player player, final InventoryAction action) {
        final Material material = itemStack.getType();
        switch (material) {
            case RED_TERRACOTTA:
                onActionDevItem(player, PlayerProfile.Stat.VITALITYNATIVE, action);
                break;
            case GREEN_TERRACOTTA:
                onActionDevItem(player, PlayerProfile.Stat.STRENGTHNATIVE, action);
                break;
            case YELLOW_TERRACOTTA:
                onActionDevItem(player, PlayerProfile.Stat.DEXTIRITYNATIVE, action);
                break;
            case WHITE_TERRACOTTA:
                onActionDevItem(player, PlayerProfile.Stat.AGILITYNATIVE, action);
                break;
            case BLUE_TERRACOTTA:
                onActionDevItem(player, PlayerProfile.Stat.INTELLIGENCENATIVE, action);
                break;
            case PURPLE_TERRACOTTA:
                onActionDevItem(player, PlayerProfile.Stat.FAITHNATIVE, action);
                break;
            case BOOKSHELF:
                onActionDevItem(player, PlayerProfile.Stat.POINTSAVAILABLES, action);
                break;
            case ARROW: {
                GuiManager.toOpen(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYPLAYERMENU_DEVMOD));
                break;
            }
        }
    }


    /////ACTION ON STATS/////

    private static void onActionItem(final Player player, final PlayerProfile.Stat stat, final InventoryAction action, final int slot) {
        switch (action) {
            case PICKUP_ALL: {
                if (PlayerProfile.getStat(player, PlayerProfile.Stat.POINTSAVAILABLES) > 0) {
                    PlayerProfile.addStat(player, stat, 1);
                    PlayerProfile.addStat(player, PlayerProfile.Stat.POINTSAVAILABLES, -1);
                    break;
                }
                final Inventory inventory = PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSTATS);
                Bukkit.getServer().getScheduler().runTaskLater(Main.INSTANCE, () -> {
                    GuiManager.toActualize(player, inventory);}, 40L);
                break;
            }
            case PICKUP_HALF: {
                if (PlayerProfile.getStat(player, PlayerProfile.Stat.POINTSAVAILABLES) > 4) {
                    PlayerProfile.addStat(player, stat, 5);
                    PlayerProfile.addStat(player, PlayerProfile.Stat.POINTSAVAILABLES, -5);
                    break;
                }
                PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSTATS).clear(slot);
                PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSTATS).setItem(slot, GuiItem.createGuiItem(new ItemStack(Material.RED_TERRACOTTA), ChatColor.RED + "Points insuffisants !", new String[0]));
                Bukkit.getServer().getScheduler().runTaskLater(Main.INSTANCE, () -> {
                    GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSTATS));
                    return;
                }, 40L);
                break;
            }
        }
        GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSTATS));
    }

    private static void onActionDevItem(final Player player, final PlayerProfile.Stat stat, final InventoryAction action) {
        switch (action) {
            case PICKUP_ALL: {
                PlayerProfile.addStat(player, stat, 1);
                break;
            }
            case PICKUP_HALF: {
                PlayerProfile.addStat(player, stat, 5);
                break;
            }
            case DROP_ONE_SLOT: {
                PlayerProfile.addStat(player, stat, -1);
                break;
            }
            case DROP_ALL_SLOT: {
                PlayerProfile.addStat(player, stat, -5);
                break;
            }
        }
        GuiManager.toActualize(player, PlayerProfileManager.profiles.get(player).getHashMapInventoryTypeToInventory().get(GuiManager.InventoryTypeList.INVENTORYSTATS_DEVMOD));
    }

    public static void initializeStatsMenuItems(final Player player, final Inventory inventory) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        inventory.setItem(19, GuiItem.createGuiItem(new ItemStack(Material.RED_TERRACOTTA, 1), ChatColor.RED + "\u2764 Vitalité " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.VITALITYNATIVE), ChatColor.GRAY + "La vitalité vous permet d'augmenter votre vie maximale et votre défense.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points", "", ""));
        inventory.setItem(21, GuiItem.createGuiItem(new ItemStack(Material.GREEN_TERRACOTTA, 1), ChatColor.GREEN + "\u03a8 Force " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.STRENGTHNATIVE), ChatColor.GRAY + "La force vous permet d'augmenter vos dégats physiques et vos dégats physiques critiques.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points"));
        inventory.setItem(23, GuiItem.createGuiItem(new ItemStack(Material.YELLOW_TERRACOTTA, 1), ChatColor.YELLOW + "\u27b6 Dexterité " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.DEXTIRITYNATIVE), ChatColor.GRAY + "La dexterité vous permet d'augmenter vos chances de coup critique physique.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points", "", ChatColor.BLUE.toString() + decimalFormat.format(dexterityCalculator((int)PlayerProfile.getStat(player, PlayerProfile.Stat.DEXTIRITYNATIVE)) * 100) + "% >> " + decimalFormat.format(dexterityCalculator((int) (PlayerProfile.getStat(player, PlayerProfile.Stat.DEXTIRITYNATIVE) + 1)) * 100) + "%"));
        inventory.setItem(25, GuiItem.createGuiItem(new ItemStack(Material.WHITE_TERRACOTTA, 1), ChatColor.WHITE + "\u2727 Agilité " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.AGILITYNATIVE), ChatColor.GRAY + "L'agilité vous permet d'augmenter vos chances d'esquives et votre vitesse de déplacement.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points", "", ChatColor.BLUE.toString() + decimalFormat.format(agilityCalculator((int)PlayerProfile.getStat(player, PlayerProfile.Stat.AGILITYNATIVE)) * 100) + "% >> " + decimalFormat.format(agilityCalculator((int) (PlayerProfile.getStat(player, PlayerProfile.Stat.AGILITYNATIVE) + 1)) * 100) + "%"));
        inventory.setItem(30, GuiItem.createGuiItem(new ItemStack(Material.BLUE_TERRACOTTA, 1), ChatColor.BLUE + "\u2745 Intelligence " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.INTELLIGENCENATIVE), ChatColor.GRAY + "L'intelligence vous permet d'augmenter vos dégats magiques et vos dégats magiques critiques.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points"));
        inventory.setItem(32, GuiItem.createGuiItem(new ItemStack(Material.PURPLE_TERRACOTTA, 1), ChatColor.DARK_PURPLE + "\u29fe Foi " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.FAITHNATIVE), ChatColor.GRAY + "La foi vous permet d'augmenter vos chances de coup critique magiques.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points"));

        if (PlayerProfileManager.profiles.get(player).getPointsAvailables() == 0) {
            inventory.setItem(4, GuiItem.createGuiItem(new ItemStack(Material.BOOK), ChatColor.GOLD + "Point disponible " + ChatColor.GRAY + PlayerProfileManager.profiles.get(player).getPointsAvailables(), ""));
        }
        else if (PlayerProfileManager.profiles.get(player).getPointsAvailables() == 1) {
            inventory.setItem(4, GuiItem.createGuiItem(new ItemStack(Material.ENCHANTED_BOOK), ChatColor.GOLD + "Point disponible " + ChatColor.GRAY + PlayerProfileManager.profiles.get(player).getPointsAvailables(), ""));
        }
        else {
            inventory.setItem(4, GuiItem.createGuiItem(new ItemStack(Material.ENCHANTED_BOOK), ChatColor.GOLD + "Points disponibles " + ChatColor.GRAY + PlayerProfileManager.profiles.get(player).getPointsAvailables(), ""));
        }
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }

    public static void initializeStatsMenuDevItems(final Player player, final Inventory inventory) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        inventory.setItem(19, GuiItem.createGuiItem(new ItemStack(Material.RED_TERRACOTTA, 1), ChatColor.RED + "\u2764 Vitalité " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.VITALITYNATIVE), ChatColor.GRAY + "La vitalit\u00e9 vous permet d'augmenter votre vie maximale et votre d\u00e9fense.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points", "", ChatColor.RED + "Jeter - Retirer 1 point", ChatColor.RED + "Ctrl + Jeter - Retirer 5 points"));
        inventory.setItem(21, GuiItem.createGuiItem(new ItemStack(Material.GREEN_TERRACOTTA, 1), ChatColor.GREEN + "\u03a8 Force " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.STRENGTHNATIVE), ChatColor.GRAY + "La force vous permet d'augmenter vos d\u00e9gats physiques et vos d\u00e9gats physiques critiques.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points", "", ChatColor.RED + "Jeter - Retirer 1 point", ChatColor.RED + "Ctrl + Jeter - Retirer 5 points"));
        inventory.setItem(23, GuiItem.createGuiItem(new ItemStack(Material.YELLOW_TERRACOTTA, 1), ChatColor.YELLOW + "\u27b6 Dexterité " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.DEXTIRITYNATIVE), ChatColor.GRAY + "La dexterit\u00e9 vous permet d'augmenter vos chances de coup critique physique.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points", "", ChatColor.RED + "Jeter - Retirer 1 point", ChatColor.RED + "Ctrl + Jeter - Retirer 5 points", "", ChatColor.BLUE.toString() + decimalFormat.format(dexterityCalculator((int)PlayerProfile.getStat(player, PlayerProfile.Stat.DEXTIRITYNATIVE)) * 100) + "% >> " + decimalFormat.format(dexterityCalculator((int) (PlayerProfile.getStat(player, PlayerProfile.Stat.DEXTIRITYNATIVE) + 1)) * 100) + "%"));
        inventory.setItem(25, GuiItem.createGuiItem(new ItemStack(Material.WHITE_TERRACOTTA, 1), ChatColor.WHITE + "\u2727 Agilité " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.AGILITYNATIVE), ChatColor.GRAY + "L'agilit\u00e9 vous permet d'augmenter vos chances d'esquives et votre vitesse de d\u00e9placement.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points", "", ChatColor.RED + "Jeter - Retirer 1 point", ChatColor.RED + "Ctrl + Jeter - Retirer 5 points", "", ChatColor.BLUE.toString() + decimalFormat.format(agilityCalculator((int)PlayerProfile.getStat(player, PlayerProfile.Stat.AGILITYNATIVE)) * 100) + "% >> " + decimalFormat.format(agilityCalculator((int) (PlayerProfile.getStat(player, PlayerProfile.Stat.AGILITYNATIVE) + 1)) * 100) + "%"));
        inventory.setItem(30, GuiItem.createGuiItem(new ItemStack(Material.BLUE_TERRACOTTA, 1), ChatColor.BLUE + "\u2745 Intelligence " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.INTELLIGENCENATIVE), ChatColor.GRAY + "L'intelligence vous permet d'augmenter vos d\u00e9gats magiques et vos d\u00e9gats magiques critiques.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points", "", ChatColor.RED + "Jeter - Retirer 1 point", ChatColor.RED + "Ctrl + Jeter - Retirer 5 points"));
        inventory.setItem(32, GuiItem.createGuiItem(new ItemStack(Material.PURPLE_TERRACOTTA, 1), ChatColor.DARK_PURPLE + "\u29fe Foi " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.FAITHNATIVE), ChatColor.GRAY + "La foi vous permet d'augmenter vos chances de coup critique magiques.", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points", "", ChatColor.RED + "Jeter - Retirer 1 point", ChatColor.RED + "Ctrl + Jeter - Retirer 5 points"));
        inventory.setItem(8, GuiItem.createGuiItem(new ItemStack(Material.BOOKSHELF, 1), ChatColor.DARK_PURPLE + "Point " + ChatColor.GRAY + PlayerProfile.getStat(player, PlayerProfile.Stat.POINTSAVAILABLES), ChatColor.GRAY + "Donne des points de statistiques", "", ChatColor.GREEN + "Clic gauche + Ajouter 1 point", ChatColor.GREEN + "Clic droit + Ajouter 5 points", "", ChatColor.RED + "Jeter - Retirer 1 point", ChatColor.RED + "Ctrl + Jeter - Retirer 5 points"));


        if (PlayerProfileManager.profiles.get(player).getPointsAvailables() == 0) {
            inventory.setItem(4, GuiItem.createGuiItem(new ItemStack(Material.BOOK), ChatColor.GOLD + "Point disponible " + ChatColor.GRAY + PlayerProfileManager.profiles.get(player).getPointsAvailables(), ""));
        }
        else if (PlayerProfileManager.profiles.get(player).getPointsAvailables() == 1) {
            inventory.setItem(4, GuiItem.createGuiItem(new ItemStack(Material.ENCHANTED_BOOK), ChatColor.GOLD + "Point disponible " + ChatColor.GRAY + PlayerProfileManager.profiles.get(player).getPointsAvailables(), ""));
        }
        else {
            inventory.setItem(4, GuiItem.createGuiItem(new ItemStack(Material.ENCHANTED_BOOK), ChatColor.GOLD + "Points disponibles " + ChatColor.GRAY + PlayerProfileManager.profiles.get(player).getPointsAvailables(), ""));
        }
        inventory.setItem(45, GuiItem.createGuiItem(new ItemStack(Material.ARROW), ChatColor.YELLOW + "Retour", ""));
        inventory.setItem(49, GuiItem.createGuiItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Quitter", ""));
    }
}
