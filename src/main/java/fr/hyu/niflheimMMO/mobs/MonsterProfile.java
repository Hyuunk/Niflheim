package fr.hyu.niflheimMMO.mobs;

import fr.hyu.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static java.lang.Math.abs;

public class MonsterProfile {

    private Entity entity;
    private EntityType entityType;
    private UUID uuid;
    private String customName;
    private String id;
    private boolean isStated;
    private int entitySpawnDistance;
    private int level;
    private HashMap<String, Double> lootTable;
    private int vitality;
    private int defence;
    private int strength;
    private int dexterity;
    private int agility;
    private int intelligence;
    private int faith;
    private int experience;
    private int vitalityModifier;
    private int defenceModifier;
    private int strengthModifier;
    private int dexterityModifier;
    private int agilityModifier;
    private int intelligenceModifier;
    private int faithModifier;
    private int experienceModifier;

    public MonsterProfile(LivingEntity entity, Location entitySpawnLocation) {

        this.entity = entity;

            if (entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "ID"), PersistentDataType.STRING) == null) {
                if (entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "isStated"), PersistentDataType.BOOLEAN) == null) {
                    this.isStated = false;
                } else if (entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "isStated"), PersistentDataType.BOOLEAN)) {
                    this.isStated = true;
                } else {
                    this.isStated = false;
                }
                this.id = entity.getType().name();
            } else {
                this.id = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "ID"), PersistentDataType.STRING);
            }


        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Monsters/" + id +".yml");
        final FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);

        this.uuid = entity.getUniqueId();
        this.entityType = entity.getType();

        if (!isStated) {
            this.level = levelCalculator(entity, entitySpawnLocation);
            this.vitalityModifier = config.getInt( "stats.vitalityModifier");
            this.defenceModifier = config.getInt("stats.defenceModifier");
            this.strengthModifier = config.getInt( "stats.strengthModifier");
            this.dexterityModifier = config.getInt("stats.dexterityModifier");
            this.agilityModifier = config.getInt( "stats.agilityModifier");
            this.intelligenceModifier = config.getInt( "stats.intelligenceModifier");
            this.faithModifier = config.getInt( "stats.faithModifier");
            this.experienceModifier = config.getInt("stats.experienceModifier");
            this.customName = customNameNaturalSpawning(entity, level);
            entity.setCustomNameVisible(true);
            statsApplicator(entity, level, vitalityModifier, defenceModifier, strengthModifier, dexterityModifier, agilityModifier, intelligenceModifier, faithModifier, experienceModifier);

        }
            this.level = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Level"), PersistentDataType.INTEGER);
            this.vitality = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Vitality"), PersistentDataType.INTEGER);
            this.defence = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Defence"), PersistentDataType.INTEGER);
            this.strength = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Strength"), PersistentDataType.INTEGER);
            this.dexterity = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Dexterity"), PersistentDataType.INTEGER);
            this.agility = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Agility"), PersistentDataType.INTEGER);
            this.intelligence = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Intelligence"), PersistentDataType.INTEGER);
            this.faith = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Faith"), PersistentDataType.INTEGER);
            this.experience = entity.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Experience"), PersistentDataType.INTEGER);
            this.customName = entity.getCustomName();

        this.lootTable = getLootTableItems(level, config);

    }

    private String customNameNaturalSpawning(LivingEntity entity, int level) {

        String entityName = entity.getName();
        String customName = entityName.substring(0, 1) + entityName.substring(1).toLowerCase();
        entity.setCustomName(ChatColor.GRAY + customName + " [" + ChatColor.BLUE + level + ChatColor.GRAY + "]");
        return ChatColor.GRAY + entityType.name() + " [" + ChatColor.BLUE + level + ChatColor.GRAY + "]";
    }

    private int levelCalculator(LivingEntity entity, Location entitySpawnLocation) {

        int entitySpawnDistance = (abs(abs((int) entitySpawnLocation.getX()) + abs((int) entitySpawnLocation.getZ())));
        int monsterLevel = (int) Math.round((entitySpawnDistance) * Math.pow(10.0, -2.0));

        //mob in another world
        if (entity.getWorld().getName().equals("world_nether")) monsterLevel = monsterLevel + 10;
        if (entity.getWorld().getName().equals("world_the_end")) monsterLevel = monsterLevel + 25;

        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Level"), PersistentDataType.INTEGER, monsterLevel);

        return monsterLevel;

    }

    private void statsApplicator(LivingEntity entity, int monsterLevel, int vitalityModifier, int defenceModifier, int strengthModifier, int dexterityModifier, int agilityModifier, int intelligenceModifier, int faithModifier, int experienceModifier) {
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "isStated"), PersistentDataType.BOOLEAN, true);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Level"), PersistentDataType.INTEGER, monsterLevel);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Vitality"), PersistentDataType.INTEGER, monsterLevel * vitalityModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Defence"), PersistentDataType.INTEGER, monsterLevel * defenceModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Strength"), PersistentDataType.INTEGER, monsterLevel * strengthModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Dexterity"), PersistentDataType.INTEGER, monsterLevel * dexterityModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Agility"), PersistentDataType.INTEGER, monsterLevel * agilityModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Intelligence"), PersistentDataType.INTEGER, monsterLevel * intelligenceModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Faith"), PersistentDataType.INTEGER, monsterLevel * faithModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Experience"), PersistentDataType.INTEGER, monsterLevel * experienceModifier);

    }

    private String getLevelRange(int level, FileConfiguration config) {
        ConfigurationSection levelRangeSection = config.getConfigurationSection("drops.levelRange");

        for (String key : levelRangeSection.getKeys(false)) {
            String[] range = key.split(" - ");
            int minLevel = Integer.parseInt(range[0]);
            int maxLevel = Integer.parseInt(range[1]);

            if (level >= minLevel && level <= maxLevel) {
                return key;
            }
        }

        return "N/A"; // Retourne "N/A" si aucune plage n'a été trouvée
    }

    private HashMap<String, Double> getLootTableItems(int level, FileConfiguration config) {
        String levelRange = getLevelRange(level, config);
        HashMap<String, Double> lootTableItems = new HashMap<>();

        if (levelRange.equals("N/A")) {
            return lootTableItems; // Aucune plage de niveau trouvée, renvoie la HashMap vide
        }

        ConfigurationSection lootSection = config.getConfigurationSection("drops.levelRange." + levelRange + ".lootTables");

        if (lootSection != null) {
            for (String itemName : lootSection.getKeys(false)) {
                double dropChance = lootSection.getDouble(itemName);
                lootTableItems.put(itemName, dropChance);
            }
        }

        return lootTableItems;
    }

    public HashMap<String, Double> getLootTable() {
        return lootTable;
    }

    public void setLootTable(HashMap<String, Double> lootTable) {
        this.lootTable = lootTable;
    }

    public int getVitalityModifier() {
        return vitalityModifier;
    }

    public void setVitalityModifier(int vitalityModifier) {
        this.vitalityModifier = vitalityModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Monsters/" + id +".yml");
        final FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
        config.set("stats.vitalityModifier", vitalityModifier);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDefenceModifier() {
        return defenceModifier;
    }

    public void setDefenceModifier(int defenceModifier) {
        this.defenceModifier = defenceModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Monsters/" + id +".yml");
        final FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
        config.set("stats.defenceModifier", defenceModifier);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getStrengthModifier() {
        return strengthModifier;
    }

    public void setStrengthModifier(int strengthModifier) {
        this.strengthModifier = strengthModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Monsters/" + id +".yml");
        final FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
        config.set("stats.strengthModifier", strengthModifier);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDexterityModifier() {
        return dexterityModifier;
    }

    public void setDexterityModifier(int dexterityModifier) {
        this.dexterityModifier = dexterityModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Monsters/" + id +".yml");
        final FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
        config.set("stats.dexterityModifier", dexterityModifier);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAgilityModifier() {
        return agilityModifier;
    }

    public void setAgilityModifier(int agilityModifier) {
        this.agilityModifier = agilityModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Monsters/" + id +".yml");
        final FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
        config.set("stats.agilityModifier", agilityModifier);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIntelligenceModifier() {
        return intelligenceModifier;
    }

    public void setIntelligenceModifier(int intelligenceModifier) {
        this.intelligenceModifier = intelligenceModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Monsters/" + id +".yml");
        final FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
        config.set("stats.intelligenceModifier", intelligenceModifier);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getFaithModifier() {
        return faithModifier;
    }

    public void setFaithModifier(int faithModifier) {
        this.faithModifier = faithModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Monsters/" + id +".yml");
        final FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
        config.set("stats.faithModifier", faithModifier);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getExperienceModifier() {
        return experienceModifier;
    }

    public void setExperienceModifier(int experienceModifier) {
        this.experienceModifier = experienceModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Monsters/" + id +".yml");
        final FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
        config.set("stats.experienceModifier", experienceModifier);
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Vitality"), PersistentDataType.INTEGER, vitality);
        this.vitality = vitality;

    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Defence"), PersistentDataType.INTEGER, defence);
        this.defence = defence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Strength"), PersistentDataType.INTEGER, strength);
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Dexterity"), PersistentDataType.INTEGER, dexterity);
        this.dexterity = dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Agility"), PersistentDataType.INTEGER, agility);
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Intelligence"), PersistentDataType.INTEGER, intelligence);
        this.intelligence = intelligence;
    }

    public int getFaith() {
        return faith;
    }

    public void setFaith(int faith) {
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Faith"), PersistentDataType.INTEGER, faith);
        this.faith = faith;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Experience"), PersistentDataType.INTEGER, experience);
        this.experience = experience;
    }
}



