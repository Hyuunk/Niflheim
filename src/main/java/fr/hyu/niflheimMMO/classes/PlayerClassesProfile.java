package fr.hyu.niflheimMMO.classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class PlayerClassesProfile {


    public enum Classes {

        NONE("NONE", "None", ChatColor.WHITE, Material.WHITE_TERRACOTTA),
        PENDING("PENDING", "Pending", ChatColor.RED, Material.BARRIER),
        // ARCHER
        SNIPER("SNIPER", "Sniper", ChatColor.GOLD, Material.BOW),
        CROSSBOWMAN("CROSSBOWMAN", "Arbalétrier", ChatColor.RED, Material.CROSSBOW),
        MAZEMASTER("MAZEMASTER", "Maître du labyrinthe", ChatColor.BLUE, Material.GOLDEN_HOE),

        // MAGE
        ARCHMAGE("ARCHMAGE", "Archmage", ChatColor.AQUA, Material.ENCHANTED_BOOK),
        SORCERER("SORCERER", "Sorcier", ChatColor.LIGHT_PURPLE, Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE),
        CLERIC("CLERIC", "Clergé", ChatColor.GREEN, Material.BLAZE_ROD),

        // GUERRIER
        BERSERKER("BERSERKER", "Berserker", ChatColor.DARK_RED, Material.GOLDEN_AXE),
        PALADIN("PALADIN", "Paladin", ChatColor.WHITE, Material.GOLDEN_SHOVEL),
        ASSASSIN("ASSASSIN", "Assassin", ChatColor.DARK_GREEN, Material.FISHING_ROD),


        // SPIRITUALISTE
        PUGILIST("PUGILIST", "Pugiliste", ChatColor.DARK_AQUA, Material.ECHO_SHARD),
        SUMMONER("SUMMONER", "Invocateur", ChatColor.GRAY, Material.TOTEM_OF_UNDYING),
        SHAMAN("SHAMAN", "Shaman", ChatColor.YELLOW, Material.BRUSH);

        private String name;
        private String displayName;
        private ChatColor color;
        private Material material;

        private Classes(String name, String displayName, ChatColor color, Material material) {
            this.name = name;
            this.displayName = displayName;
            this.color = color;
            this.material = material;
        }
        public String getName() {
            return this.name;
        }
        public String getDisplayName(){
            return this.displayName;
        }
        public ChatColor getColor() { return this.color; }
        public Material getMaterial() { return this.material; }


    }
}
