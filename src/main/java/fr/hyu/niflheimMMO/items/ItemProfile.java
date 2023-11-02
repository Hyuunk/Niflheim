package fr.hyu.niflheimMMO.items;

import fr.hyu.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ItemProfile {

    private ItemMeta itemMeta;
    private ItemStack itemStack;
    private String id;
    private boolean isStated;
    private String name;
    private int level;
    private int vitality;
    private int strength;
    private int dexterity;
    private int agility;
    private int intelligence;
    private int faith;
    private HashMap<Integer, ItemMeta> runes;


    public ItemProfile(ItemStack itemStack) {

        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();

        if (itemStack.hasItemMeta()) {
            this.itemMeta = itemStack.getItemMeta();
            if (itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "ID"), PersistentDataType.STRING) == null) {
                if (itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "isStated"), PersistentDataType.BOOLEAN) == null) {
                    this.isStated = false;
                } else if (itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "isStated"), PersistentDataType.BOOLEAN)) {
                    this.isStated = true;
                } else {
                    this.isStated = false;
                }
                this.id = itemStack.getType().name();
            } else {
                this.id = itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "ID"), PersistentDataType.STRING);
            }
        } else {
            this.id = itemStack.getType().name();
            this.isStated = false;
        }

        createFile(itemStack, id);

        File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Items/" + id + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (!isStated) { statsApplicator(itemStack, itemMeta); }

        this.level = itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Level"), PersistentDataType.INTEGER);
        this.vitality = itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Vitality"), PersistentDataType.INTEGER);
        this.strength = itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Strength"), PersistentDataType.INTEGER);
        this.dexterity = itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Dexterity"), PersistentDataType.INTEGER);
        this.agility = itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Agility"), PersistentDataType.INTEGER);
        this.intelligence = itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Intelligence"), PersistentDataType.INTEGER);
        this.faith = itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.INSTANCE, "Faith"), PersistentDataType.INTEGER);

        if(!itemMeta.hasLore()) {
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(ChatColor.RED + "+" +  this.vitality + " \u2764 Vitalité ");
            lore.add(ChatColor.GREEN + "+" +  this.strength + " \u03a8 Force ");
            lore.add(ChatColor.YELLOW + "+" +  this.dexterity + " \u27b6 Dexterité ");
            lore.add(ChatColor.BLUE + "+" +  this.intelligence + " \u2745 Intelligence ");
            lore.add(ChatColor.DARK_PURPLE + "+" +  this.faith + " \u29fe Foi ");
            lore.add(ChatColor.WHITE + "+" +  this.agility + " \u2727 Agilité ");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
    }


    private void statsApplicator(ItemStack itemStack, ItemMeta itemMeta) {
        if (itemMeta == null) {
            return;
        }

        File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Items/" + id + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "isStated"), PersistentDataType.BOOLEAN, true);
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Level"), PersistentDataType.INTEGER, generateRandomValue(config, "minLevel", "maxLevel"));
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Vitality"), PersistentDataType.INTEGER, generateRandomValue(config, "stats.minVitality", "stats.maxVitality"));
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Strength"), PersistentDataType.INTEGER, generateRandomValue(config, "stats.minStrength", "stats.maxStrength"));
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Dexterity"), PersistentDataType.INTEGER, generateRandomValue(config, "stats.minDexterity", "stats.maxDexterity"));
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Agility"), PersistentDataType.INTEGER, generateRandomValue(config, "stats.minAgility", "stats.maxAgility"));
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Intelligence"), PersistentDataType.INTEGER, generateRandomValue(config, "stats.minIntelligence", "stats.maxIntelligence"));
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.INSTANCE, "Faith"), PersistentDataType.INTEGER, generateRandomValue(config, "stats.minFaith", "stats.maxFaith"));

        itemStack.setItemMeta(itemMeta);

    }

    private int generateRandomValue(FileConfiguration config, String minKey, String maxKey) {
        int min = config.getInt(minKey);
        int max = config.getInt(maxKey);
        return new Random().nextInt(max - min + 1) + min;
    }

        private void createFile(ItemStack itemStack, String id){

            File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Items/" + id.toUpperCase() + ".yml");
            FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);

            config.set("Material", itemStack.getType().name());
            config.set("Lore", "");
            config.set("MonsterDrop", "");

            config.set("maxLevel", 2);
            config.set("minLevel", 1);

            config.set("stats.maxVitality", 2);
            config.set("stats.minVitality", 1);

            config.set("stats.maxStrength", 2);
            config.set("stats.minStrength", 1);

            config.set("stats.maxDexterity", 2);
            config.set("stats.minDexterity", 1);

            config.set("stats.maxAgility", 2);
            config.set("stats.minAgility", 1);

            config.set("stats.maxIntelligence", 2);
            config.set("stats.minIntelligence", 1);

            config.set("stats.maxFaith", 2);
            config.set("stats.minFaith", 1);

            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

