package fr.hyu.niflheimMMO;

import fr.hyu.Main;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class MMOCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            switch (cmd.getLabel().toUpperCase()) {
                case "NIFLHEIMMMO":
                    switch (args[0].toUpperCase()) {
                        case "LOAD":
                            player.sendMessage("start?");
                            final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/monsters.yml");
                            final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                            for (EntityType entityType : EntityType.values()) {
                                String monsterName = entityType.name();
                                config.set(monsterName + ".vitalityModifier", 1);
                                config.set(monsterName + ".strengthModifier", 1);
                                config.set(monsterName + ".dextirityModifier", 1);
                                config.set(monsterName + ".agilityModifier", 1);
                                config.set(monsterName + ".intelligenceModifier", 1);
                                config.set(monsterName + ".faithModifier", 1);

                            }
                            try {
                                player.sendMessage("saved.");
                                config.save(file);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
                case "XP":
                    int experiencePourcentage = ((PlayerProfileManager.profiles.get(player).getExperiencesPoints())*100)/(PlayerProfileManager.profiles.get(player).getExperienceNeeded());
                   player.sendMessage(ChatColor.GREEN + "Votre expérience est de : " + PlayerProfileManager.profiles.get(player).getExperiencesPoints() + "/" + PlayerProfileManager.profiles.get(player).getExperienceNeeded() + ChatColor.GRAY + " [" + ChatColor.GREEN + experiencePourcentage + " %" +ChatColor.GRAY + "]" + ChatColor.GREEN + ".");
                break;

            }
        }
        return false;
    }
}
