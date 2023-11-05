package fr.hyu.niflheimMMO;

import fr.hyu.Main;
import fr.hyu.niflheimMMO.items.ItemProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MMOCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

        final String newLine = System.getProperty("line.separator");

        if (sender instanceof Player) {
            Player player = (Player) sender;

            switch (cmd.getLabel().toUpperCase()) {
                case "NIFLHEIMMMO":
                    switch (args[0].toUpperCase()) {
                        case "LOAD":
                            player.sendMessage("start?");
                            for (EntityType entityType : EntityType.values()) {

                                String monsterName = entityType.name();
                                final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Monsters/" + monsterName + ".yml");
                                final FileConfiguration config = YamlConfiguration.loadConfiguration(file);

                                config.set("MonsterName", monsterName);
                                config.set("ID", entityType.name());
                                config.set("Lore", "");
                                config.set("drops.levelRange.0 - 1.lootTables.ANVIL", 0.01);
                                config.set("drops.levelRange.0 - 1.lootTables.IRON", 0.01);
                                config.set("drops.levelRange.1 - 10.lootTables.ANVIL", 0.01);
                                config.set("drops.levelRange.10 - 20.lootTables.ANVIL", 0.01);
                                config.set("stats.vitalityModifier", 1);
                                config.set("stats.defenceModifier", 1);
                                config.set("stats.strengthModifier", 1);
                                config.set("stats.dexterityModifier", 1);
                                config.set("stats.agilityModifier", 1);
                                config.set("stats.intelligenceModifier", 1);
                                config.set("stats.faithModifier", 1);
                                config.set("stats.experienceModifier", 1);

                                try {
                                    player.sendMessage("saved.");
                                    config.save(file);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            break;
                        case "CREATEFILE":
                            ItemStack droppedItem = new ItemStack(Material.matchMaterial(args[1]), 1);
                            createFile(droppedItem, args[2]);
                            break;
                    }
                    break;
                case "XP":
                    int experiencePourcentage = ((PlayerProfileManager.profiles.get(player).getExperiencesPoints())*100)/(PlayerProfileManager.profiles.get(player).getExperienceNeeded());
                   player.sendMessage(ChatColor.GREEN + "Vous êtes niveau "+ PlayerProfileManager.profiles.get(player).getLevel() + "." + newLine + "Votre expérience est de : " + PlayerProfileManager.profiles.get(player).getExperiencesPoints() + "/" + PlayerProfileManager.profiles.get(player).getExperienceNeeded() + ChatColor.GRAY + " [" + ChatColor.GREEN + experiencePourcentage + " %" +ChatColor.GRAY + "]" + ChatColor.GREEN + ".");
                break;

            }
        }
        return false;
    }
    private void createFile(ItemStack itemStack, String id){

        File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/Items/" + id.toUpperCase() + ".yml");
        FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);

        config.set("Material", itemStack.getType().name());
        config.set("nameItem", "");
        config.set("Rarity", "");
        config.set("Lore", "");
        config.set("MonsterDrop", "");

        config.set("maxLevel", 2);
        config.set("minLevel", 1);

        config.set("stats.maxVitality", 2);
        config.set("stats.minVitality", 1);

        config.set("stats.maxDefence", 2);
        config.set("stats.minDefence", 1);

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
