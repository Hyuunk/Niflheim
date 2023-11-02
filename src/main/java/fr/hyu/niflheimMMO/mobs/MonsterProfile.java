package fr.hyu.niflheimMMO.mobs;

import fr.hyu.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static java.lang.Math.abs;

public class MonsterProfile {

    private EntityType entityType;
    private UUID uuid;
    private String customName;
    private int entitySpawnDistance;
    private int level;

    private int vitalityModifier;
    private int strengthModifier;
    private int dextirityModifier;
    private int agilityModifier;
    private int intelligenceModifier;
    private int faithModifier;

    public MonsterProfile(LivingEntity entity, Location entitySpawnLocation) {


        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/monsters.yml");
        final FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
        this.uuid = entity.getUniqueId();
        this.entityType = entity.getType();
        this.level = levelCalculator(entity, entitySpawnLocation);
        this.vitalityModifier = config.getInt(entityType.name() + ".vitalityModifier");
        this.strengthModifier = config.getInt(entityType.name() + ".strengthModifier");
        this.dextirityModifier = config.getInt(entityType.name() + ".dextirityModifier");
        this.agilityModifier = config.getInt(entityType.name() + ".agilityModifier");
        this.intelligenceModifier = config.getInt(entityType.name() + ".intelligenceModifier");
        this.faithModifier = config.getInt(entityType.name() + ".faithModifier");
        statsApplicator(entity, level, vitalityModifier, strengthModifier, dextirityModifier, agilityModifier, intelligenceModifier, faithModifier);
        this.customName = customNameNaturalSpawning(entity, level);
        entity.setCustomNameVisible(true);

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEntitySpawnDistance() {
        return entitySpawnDistance;
    }

    public void setEntitySpawnDistance(int entitySpawnDistance) {
        this.entitySpawnDistance = entitySpawnDistance;
    }

    public int getVitalityModifier() {
        return vitalityModifier;
    }

    public void setVitalityModifier(int vitalityModifier, String monsterName) {
        this.vitalityModifier = vitalityModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/monsters.yml");
        final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(monsterName + ".vitalityModifier", vitalityModifier);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getStrengthModifier() {
        return strengthModifier;
    }

    public void setStrengthModifier(int strengthModifier, String monsterName) {
        this.strengthModifier = strengthModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/monsters.yml");
        final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(monsterName + ".strengthModifier", vitalityModifier);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDextirityModifier() {
        return dextirityModifier;
    }

    public void setDextirityModifier(int dextirityModifier, String monsterName) {
        this.dextirityModifier = dextirityModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/monsters.yml");
        final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(monsterName + ".dextirityModifier", vitalityModifier);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAgilityModifier() {
        return agilityModifier;
    }

    public void setAgilityModifier(int agilityModifier, String monsterName) {
        this.agilityModifier = agilityModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/monsters.yml");
        final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(monsterName + ".agilityModifier", vitalityModifier);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIntelligenceModifier() {
        return intelligenceModifier;
    }

    public void setIntelligenceModifier(int intelligenceModifier, String monsterName) {
        this.intelligenceModifier = intelligenceModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/monsters.yml");
        final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(monsterName + ".intelligenceModifier", vitalityModifier);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getFaithModifier() {
        return faithModifier;
    }

    public void setFaithModifier(int faithModifier, String monsterName) {
        this.faithModifier = faithModifier;
        final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/monsters.yml");
        final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(monsterName + ".faithModifier", vitalityModifier);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void statsApplicator(LivingEntity entity, int monsterLevel, int vitalityModifier, int strengthModifier, int dextirityModifier, int agilityModifier, int intelligenceModifier, int faithModifier) {
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Vitality"), PersistentDataType.INTEGER, monsterLevel * vitalityModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Strength"), PersistentDataType.INTEGER, monsterLevel * strengthModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Dextirity"), PersistentDataType.INTEGER, monsterLevel * dextirityModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Agility"), PersistentDataType.INTEGER, monsterLevel * agilityModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Intelligence"), PersistentDataType.INTEGER, monsterLevel * intelligenceModifier);
        entity.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Faith"), PersistentDataType.INTEGER, monsterLevel * faithModifier);
    }
}



