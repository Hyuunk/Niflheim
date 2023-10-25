package fr.hyu.niflheimPermissions.player;

import java.io.IOException;
import java.util.ArrayList;

import fr.hyu.Main;
import fr.hyu.niflheim.gui.GuiManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.inventory.Inventory;
import java.util.List;
import java.util.UUID;
import fr.hyu.niflheimPermissions.player.PlayerRankProfile.Rank;

public class PlayerProfile
{
    private String name;
    private UUID uuid;

    private PlayerRankProfile.Rank rank;

    private List<Inventory> inventoryArrayList;
    private HashMap<GuiManager.InventoryTypeList, Inventory> inventoryHashMapTypeListInv;
    private double gold;
    private double karma;
    private int warp;

    public PlayerProfile(final Player player) {
        this.name = player.getDisplayName();
        this.uuid = player.getUniqueId();
        this.initFile();
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + this.name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        this.rank = PlayerRankProfile.Rank.valueOf(config.getString("rank"));
        this.inventoryArrayList = this.initInventories(player);
        this.inventoryHashMapTypeListInv = this.initHashMap();
        this.gold = config.getDouble("gold");
        this.karma = config.getDouble("karmaPoint");
        this.warp = config.getInt("warp");
    }

    public HashMap<GuiManager.InventoryTypeList, Inventory> initHashMap() {
        final HashMap<GuiManager.InventoryTypeList, Inventory> hashMapInv = new HashMap<GuiManager.InventoryTypeList, Inventory>();
        for (int i = 0; i < this.inventoryArrayList.size(); ++i) {
            final Inventory inv = this.inventoryArrayList.get(i);
            hashMapInv.put(GuiManager.getInventoryType(inv), inv);
        }
        return hashMapInv;
    }

    private List<Inventory> initInventories(final Player player) {
        final List<Inventory> inventoryArrayList = new ArrayList<Inventory>();
        GuiManager.InventoryTypeList[] values;
        for (int length = (values = GuiManager.InventoryTypeList.values()).length, i = 0; i < length; ++i) {
            final GuiManager.InventoryTypeList inventory = values[i];
            inventoryArrayList.add(inventory.createInventory(player));
        }
        return inventoryArrayList;
    }

    private void initFile() {
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + this.name + ".yml");
        if (!file.exists()) {
            final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
            config.set("rank", "DEFAULT");
            config.set("gold", 0);
            config.set("karmaPoint", 0);
            config.set("warp", 0);
            try {
                config.save(file);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("rank", rank.getName());
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Inventory> getInventories() {
        return this.inventoryArrayList;
    }

    public void setInventories(final Inventory inventory) {
        final List<Inventory> inventoryArrayList = this.inventoryArrayList;
        inventoryArrayList.add(inventory);
        this.inventoryArrayList = inventoryArrayList;
    }

    public HashMap<GuiManager.InventoryTypeList, Inventory> getHashMapInventoryTypeToInventory() {
        return this.inventoryHashMapTypeListInv;
    }

    public double getGold() {
        return this.gold;
    }

    public void setGold(final double gold) {
        this.gold = gold;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("gold", gold);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getKarma() {
        return this.karma;
    }

    public void setKarma(double karma) {
        this.karma = karma;
        File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("karmaPoint", (Object)karma);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWarp() {
        return this.warp;
    }

    public void setWarp(int warp) {
        this.warp = warp;
        File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("warp", (Object)warp);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double getStat(Player player, Stat stat) {
        final String upperCase;
        switch (upperCase = stat.getName().toUpperCase()) {
            case "GOLD": {
                return PlayerProfileManager.profiles.get(player).getGold();
            }
            case "WARP": {
                return PlayerProfileManager.profiles.get(player).getWarp();
            }
            case "KARMA": {
                return PlayerProfileManager.profiles.get(player).getKarma();
            }
            default:
                break;
        }
        return 0.0;
    }

    public static void setStat(final Player player, final Stat stat, final double toSet) {
        final String upperCase;
        switch (upperCase = stat.getName().toUpperCase()) {
            case "GOLD": {
                PlayerProfileManager.profiles.get(player).setGold(toSet);
                return;
            }
            case "WARP": {
                PlayerProfileManager.profiles.get(player).setWarp((int)toSet);
                return;
            }
            case "KARMA": {
                PlayerProfileManager.profiles.get(player).setKarma(toSet);
                return;
            }
            default:
                break;
        }
    }

    public static void addStat(Player player, Stat stat, double toAdd) {
        final String upperCase;
        switch (upperCase = stat.getName().toUpperCase()) {
            case "GOLD": {
                PlayerProfileManager.profiles.get(player).setGold(PlayerProfileManager.profiles.get(player).getGold() + toAdd);
                return;
            }
            case "WARP": {
                PlayerProfileManager.profiles.get(player).setWarp((int)(PlayerProfileManager.profiles.get(player).getWarp() + toAdd));
                return;
            }
            case "KARMA": {
                PlayerProfileManager.profiles.get(player).setKarma(PlayerProfileManager.profiles.get(player).getKarma() + toAdd);
                return;
            }
            default:
                break;
        }
        System.out.println("[OLYMPPERMS] setStat not defined");
    }

    public static boolean isStat(String statToVerify) {
        Stat[] values;
        for (int length = (values = Stat.values()).length, i = 0; i < length; ++i) {
            Stat stat = values[i];
            if (stat.toString().equalsIgnoreCase(statToVerify)) {
                return true;
            }
        }
        return false;
    }

    public enum Stat
    {
        GOLD("GOLD", 0, "gold"),
        KARMA("KARMA", 1, "karma"),
        WARP("WARP", 2, "warp");

        private String name;

        private Stat(String name2, int ordinal, String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
