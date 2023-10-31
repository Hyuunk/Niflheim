package fr.hyu.niflheimPermissions.player;

import java.io.IOException;
import java.util.ArrayList;

import fr.hyu.Main;
import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheimMMO.classes.PlayerClassesProfile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.inventory.Inventory;
import java.util.List;
import java.util.UUID;
import fr.hyu.niflheimPermissions.player.PlayerRankProfile.Rank;
import fr.hyu.niflheimMMO.classes.PlayerClassesProfile.Classes;
import fr.hyu.niflheim.gui.GuiManager.InventoryTypeList;

public class PlayerProfile
{
    private String name;
    private UUID uuid;
    private PlayerRankProfile.Rank rank;

    private PlayerClassesProfile.Classes classes;
    private int level;
    private int experiencesPoints;
    private int pointsAvailables;
    private double gold;
    private double karma;
    private int vitalityNative;
    private int defenceNative;
    private int strengthNative;
    private int dexterityNative;
    private int enduranceOnLeave;
    private int enduranceCapacityNative;
    private int intelligenceNative;
    private int faithNative;
    private int manaOnLeave;
    private int manaCapacityNative;
    private int agilityNative;
    private List<Inventory> inventoryArrayList;
    private HashMap<GuiManager.InventoryTypeList, Inventory> inventoryHashMapTypeListInv;
    private int warp;

    public PlayerProfile(Player player) {
        this.name = player.getDisplayName();
        this.uuid = player.getUniqueId();
        this.initFile();
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + this.name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        this.rank = PlayerRankProfile.Rank.valueOf(config.getString("rank"));
        this.classes = PlayerClassesProfile.Classes.valueOf(config.getString("class"));
        this.inventoryArrayList = this.initInventories(player);
        this.inventoryHashMapTypeListInv = this.initHashMap();
        this.level = config.getInt("level.level");
        this.experiencesPoints = config.getInt("level.experiencsPoints");
        this.pointsAvailables = config.getInt("level.pointsAvailables");
        this.gold = config.getInt("gold");
        this.karma = config.getInt("karmaPoint");
        this.warp = config.getInt("warps");
        this.vitalityNative = config.getInt("stats.vitalityNative");
        this.defenceNative = config.getInt("stats.defenceNative");
        this.strengthNative = config.getInt("stats.strengthNative");
        this.dexterityNative = config.getInt("stats.dexterityNative");
        this.enduranceOnLeave = config.getInt("stats.enduranceOnLeave");
        this.enduranceCapacityNative = config.getInt("stats.enduranceNative");
        this.intelligenceNative = config.getInt("stats.intelligenceNative");
        this.faithNative = config.getInt("stats.faithNative");
        this.manaOnLeave = config.getInt("stats.manaOnLeave");
        this.manaCapacityNative = config.getInt("stats.manaCapacityNative");
        this.agilityNative = config.getInt("stats.agilityNative");
    }

    public HashMap<InventoryTypeList, Inventory> initHashMap() {
        final HashMap<InventoryTypeList, Inventory> hashMapInv = new HashMap<GuiManager.InventoryTypeList, Inventory>();
        int i = -1;
        for (InventoryTypeList type : InventoryTypeList.values()) {
             i++;
            Inventory inv = this.inventoryArrayList.get(i);
            InventoryTypeList inventoryType = GuiManager.InventoryTypeList.getOrdinalToInventoryType(i);
           hashMapInv.put(inventoryType, inv);
        }
        return hashMapInv;
    }

    private List<Inventory> initInventories(Player player) {
        final List<Inventory> inventoryArrayList = new ArrayList<Inventory>();
        int i = -1;
        for (InventoryTypeList type : InventoryTypeList.values()) {
            inventoryArrayList.add(type.createInventory(player));
            i++;
        }
        return inventoryArrayList;
    }

    private void initFile() {
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + this.name + ".yml");
        if (!file.exists()) {
            final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
            config.set("rank", "DEFAULT");
            config.set("class", "NONE");

            config.set("gold", 0);
            config.set("karmaPoint", 0);
            config.set("warps", 0);

            config.set("level.level", (Object)0);
            config.set("level.experiencesPoints", (Object)0);
            config.set("level.pointsAvailables", (Object)0);
            config.set("stats.vitalityNative", (Object)0);
            config.set("stats.defenceNative", (Object)0);
            config.set("stats.strengthNative", (Object)0);
            config.set("stats.dexterityNative", (Object)0);
            config.set("stats.enduranceOnLeave", (Object)100);
            config.set("stats.enduranceNative", (Object)100);
            config.set("stats.intelligenceNative", (Object)0);
            config.set("stats.faithNative", (Object)0);
            config.set("stats.manaOnLeave", (Object)100);
            config.set("stats.manaCapacityNative", (Object)100);
            config.set("stats.agilityNative", (Object)0);
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

    public PlayerClassesProfile.Classes getClasses() {
        return this.classes;
    }

    public void setClasses(PlayerClassesProfile.Classes classes) {
        this.classes = classes;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("class", classes.getName());
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
        List<Inventory> inventoryArrayList = this.inventoryArrayList;
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

    public int getLevel() {
        return this.level;
    }

    public void setLevel(final int level) {
        this.level = level;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("level.level", (Object)level);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getExperiencesPoints() {
        return this.experiencesPoints;
    }

    public void setExperiencesPoints(final int experiencesPoints) {
        this.experiencesPoints = experiencesPoints;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("level.experiencesPoints", (Object)experiencesPoints);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPointsAvailables() {
        return this.pointsAvailables;
    }

    public void setPointsAvailables(final int pointsAvailables) {
        this.pointsAvailables = pointsAvailables;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("level.pointsAvailables", (Object)pointsAvailables);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getVitalityNative() {
        return this.vitalityNative;
    }

    public void setVitalityNative(final int vitalityNative) {
        this.vitalityNative = vitalityNative;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("stats.vitalityNative", (Object)vitalityNative);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDefenceNative() {
        return this.defenceNative;
    }

    public void setDefenceNative(final int defenceNative) {
        this.defenceNative = defenceNative;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("stats.defenceNative", (Object)defenceNative);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getStrengthNative() {
        return this.strengthNative;
    }

    public void setStrengthNative(final int strengthNative) {
        this.strengthNative = strengthNative;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("stats.strengthNative", (Object)strengthNative);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDexterityNative() {
        return this.dexterityNative;
    }

    public void setDexterityNative(final int dexterityNative) {
        this.dexterityNative = dexterityNative;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("stats.dexterityNative", (Object)dexterityNative);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getEnduranceOnLeave() {
        return this.enduranceOnLeave;
    }

    public void setEnduranceOnLeave(final int enduranceOnLeave) {
        this.enduranceOnLeave = enduranceOnLeave;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("stats.enduranceOnLeave", (Object)enduranceOnLeave);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getEnduranceNative() {
        return this.enduranceCapacityNative;
    }

    public void setEnduranceNative(final int enduranceNative) {
        this.enduranceCapacityNative = enduranceNative;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("stats.enduranceNative", (Object)enduranceNative);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIntelligenceNative() {
        return this.intelligenceNative;
    }

    public void setIntelligenceNative(final int intelligenceNative) {
        this.intelligenceNative = intelligenceNative;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("stats.intelligenceNative", (Object)intelligenceNative);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getFaithNative() {
        return this.faithNative;
    }

    public void setFaithNative(final int faithNative) {
        this.faithNative = faithNative;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("stats.faithNative", (Object)faithNative);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getManaOnLeave() {
        return this.manaOnLeave;
    }

    public void setManaOnLeave(final int mana) {
        this.manaOnLeave = mana;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("stats.manaOnLeave", (Object)this.manaOnLeave);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getManaCapacityNative() {
        return this.manaCapacityNative;
    }

    public void setManaCapacityNative(final int manaCapacityNative) {
        this.manaCapacityNative = manaCapacityNative;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("stats.manaCapacityNative", (Object)manaCapacityNative);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAgilityNative() {
        return this.agilityNative;
    }

    public void setAgilityNative(final int agilityNative) {
        this.agilityNative = agilityNative;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/players/" + name + ".yml");
        final FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        config.set("stats.agilityNative", (Object)agilityNative);
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
            case "FAITHNATIVE": {
                return PlayerProfileManager.profiles.get(player).getFaithNative();
            }
            case "ENDURANCENATIVE": {
                return PlayerProfileManager.profiles.get(player).getEnduranceNative();
            }
            case "POINTSAVAILABLES": {
                return PlayerProfileManager.profiles.get(player).getPointsAvailables();
            }
            case "AGILITYNATIVE": {
                return PlayerProfileManager.profiles.get(player).getAgilityNative();
            }
            case "GOLD": {
                return PlayerProfileManager.profiles.get(player).getGold();
            }
            case "WARP": {
                return PlayerProfileManager.profiles.get(player).getWarp();
            }
            case "KARMA": {
                return PlayerProfileManager.profiles.get(player).getKarma();
            }
            case "LEVEL": {
                return PlayerProfileManager.profiles.get(player).getLevel();
            }
            case "VITALITYNATIVE": {
                return PlayerProfileManager.profiles.get(player).getVitalityNative();
            }
            case "STRENGTHNATIVE": {
                return PlayerProfileManager.profiles.get(player).getStrengthNative();
            }
            case "DEFENCENATIVE": {
                return PlayerProfileManager.profiles.get(player).getDefenceNative();
            }
            case "INTELLIGENCENATIVE": {
                return PlayerProfileManager.profiles.get(player).getIntelligenceNative();
            }
            case "MANAONLEAVE": {
                return PlayerProfileManager.profiles.get(player).getManaOnLeave();
            }
            case "DEXTIRITYNATIVE": {
                return PlayerProfileManager.profiles.get(player).getDexterityNative();
            }
            case "MANACAPACITYNATIVE": {
                return PlayerProfileManager.profiles.get(player).getManaCapacityNative();
            }
            case "EXPERIENCEPOINTS": {
                return PlayerProfileManager.profiles.get(player).getExperiencesPoints();
            }
            default:
                break;
        }
        return 0.0;
    }

    public static void setStat(final Player player, final Stat stat, final double toSet) {
        final String upperCase;
        switch (upperCase = stat.getName().toUpperCase()) {
            case "FAITHNATIVE": {
                PlayerProfileManager.profiles.get(player).setFaithNative((int)toSet);
                return;
            }
            case "ENDURANCENATIVE": {
                PlayerProfileManager.profiles.get(player).setEnduranceNative((int)toSet);
                return;
            }
            case "POINTSAVAILABLES": {
                PlayerProfileManager.profiles.get(player).setPointsAvailables((int)toSet);
                return;
            }
            case "AGILITYNATIVE": {
                PlayerProfileManager.profiles.get(player).setAgilityNative((int)toSet);
                return;
            }
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
            case "LEVEL": {
                PlayerProfileManager.profiles.get(player).setLevel((int)toSet);
                return;
            }
            case "VITALITYNATIVE": {
                PlayerProfileManager.profiles.get(player).setVitalityNative((int)toSet);
                return;
            }
            case "STRENGTHNATIVE": {
                PlayerProfileManager.profiles.get(player).setStrengthNative((int)toSet);
                return;
            }
            case "DEFENCENATIVE": {
                PlayerProfileManager.profiles.get(player).setDefenceNative((int)toSet);
                return;
            }
            case "INTELLIGENCENATIVE": {
                PlayerProfileManager.profiles.get(player).setIntelligenceNative((int)toSet);
                return;
            }
            case "MANAONLEAVE": {
                PlayerProfileManager.profiles.get(player).setManaOnLeave((int)toSet);
                return;
            }
            case "DEXTIRITYNATIVE": {
                PlayerProfileManager.profiles.get(player).setDexterityNative((int)toSet);
                return;
            }
            case "MANACAPACITYNATIVE": {
                PlayerProfileManager.profiles.get(player).setManaCapacityNative((int)toSet);
                return;
            }
            case "EXPERIENCEPOINTS": {
                PlayerProfileManager.profiles.get(player).setExperiencesPoints((int)toSet);
                return;
            }
            default:
                break;
        }
    }

    public static void addStat(Player player, Stat stat, double toAdd) {
        final String upperCase;
        switch (upperCase = stat.getName().toUpperCase()) {
            case "FAITHNATIVE": {
                PlayerProfileManager.profiles.get(player).setFaithNative((int)(PlayerProfileManager.profiles.get(player).getFaithNative() + toAdd));
                return;
            }
            case "ENDURANCENATIVE": {
                PlayerProfileManager.profiles.get(player).setEnduranceNative((int)(PlayerProfileManager.profiles.get(player).getEnduranceNative() + toAdd));
                return;
            }
            case "POINTSAVAILABLES": {
                PlayerProfileManager.profiles.get(player).setPointsAvailables((int)(PlayerProfileManager.profiles.get(player).getPointsAvailables() + toAdd));
                return;
            }
            case "AGILITYNATIVE": {
                PlayerProfileManager.profiles.get(player).setAgilityNative((int)(PlayerProfileManager.profiles.get(player).getAgilityNative() + toAdd));
                return;
            }
            case "GOLD": {
                PlayerProfileManager.profiles.get(player).setGold(PlayerProfileManager.profiles.get(player).getGold() + toAdd);
                return;
            }
            case "WARP": {
                PlayerProfileManager.profiles.get(player).setWarp((int)(PlayerProfileManager.profiles.get(player).getWarp() + toAdd));
            }
            case "KARMA": {
                PlayerProfileManager.profiles.get(player).setKarma((int)(PlayerProfileManager.profiles.get(player).getKarma() + toAdd));
                return;
            }
            case "LEVEL": {
                PlayerProfileManager.profiles.get(player).setLevel((int)(PlayerProfileManager.profiles.get(player).getLevel() + toAdd));
                return;
            }
            case "VITALITYNATIVE": {
                PlayerProfileManager.profiles.get(player).setVitalityNative((int)(PlayerProfileManager.profiles.get(player).getVitalityNative() + toAdd));
                return;
            }
            case "STRENGTHNATIVE": {
                PlayerProfileManager.profiles.get(player).setStrengthNative((int)(PlayerProfileManager.profiles.get(player).getStrengthNative() + toAdd));
                return;
            }
            case "DEFENCENATIVE": {
                PlayerProfileManager.profiles.get(player).setDefenceNative((int)(PlayerProfileManager.profiles.get(player).getDefenceNative() + toAdd));
                return;
            }
            case "INTELLIGENCENATIVE": {
                PlayerProfileManager.profiles.get(player).setIntelligenceNative((int)(PlayerProfileManager.profiles.get(player).getIntelligenceNative() + toAdd));
                return;
            }
            case "MANAONLEAVE": {
                PlayerProfileManager.profiles.get(player).setManaOnLeave((int)(PlayerProfileManager.profiles.get(player).getManaOnLeave() + toAdd));
                return;
            }
            case "DEXTIRITYNATIVE": {
                PlayerProfileManager.profiles.get(player).setDexterityNative((int)(PlayerProfileManager.profiles.get(player).getDexterityNative() + toAdd));
                return;
            }
            case "MANACAPACITYNATIVE": {
                PlayerProfileManager.profiles.get(player).setManaCapacityNative((int)(PlayerProfileManager.profiles.get(player).getManaCapacityNative() + toAdd));
                return;
            }
            case "EXPERIENCEPOINTS": {
                PlayerProfileManager.profiles.get(player).setExperiencesPoints((int)(PlayerProfileManager.profiles.get(player).getExperiencesPoints() + toAdd));
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
        GOLD("GOLD", "gold"),
        KARMA("KARMA", "karma"),
        WARP("WARP", "warp"),

        LEVEL("LEVEL", "level"),
        EXPERIENCEPOINTS("EXPERIENCEPOINTS", "experiencePoints"),
        POINTSAVAILABLES("POINTSAVAILABLES", "pointsAvailables"),
        VITALITYNATIVE("VITALITYNATIVE", "vitalityNative"),
        DEFENCENATIVE("DEFENCENATIVE", "defenceNative"),
        STRENGTHNATIVE("STRENGTHNATIVE", "strengthNative"),
        DEXTIRITYNATIVE("DEXTIRITYNATIVE", "dextirityNative"),
        ENDURENCENATIVE("ENDURENCENATIVE", "enduranceNative"),
        INTELLIGENCENATIVE("INTELLIGENCENATIVE", "intelligenceNative"),
        FAITHNATIVE("FAITHNATIVE",  "faithNative"),
        MANAONLEAVE("MANAONLEAVE", "manaOnLeave"),
        MANACAPACITYNATIVE("MANACAPACITYNATIVE", "manaCapacityNative"),
        AGILITYNATIVE("AGILITYNATIVE", "agilityNative");

        private String name;

        private Stat(String name2, String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
