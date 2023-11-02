package fr.hyu.niflheimMMO.experience;

import fr.hyu.Main;
import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheimPermissions.player.PlayerProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Experiences {

    public static HashMap<Integer, Integer> levelNeededPerLevel =  new HashMap<>();;
    static final File file = new File(Main.INSTANCE.getDataFolder(), "NiflheimMMO/experiences.yml");
    static final FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static void initExperiences() {
        if (!file.exists()) {

            config.set("maxLevel", 50);
            int maxLevel = config.getInt("maxLevel");
            for (int i = 0; i <= maxLevel; i++) {

                config.set("level."+i, 0);
            }
            try {
                config.save(file);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        int maxLevel = config.getInt("maxLevel");
        for (int i = 0; i <= maxLevel; i++) {
            levelNeededPerLevel.put(i, config.getInt("level."+i));
       }
    }

    public static boolean reloadExperience(Player player) {
        PlayerProfile playerProfile = PlayerProfileManager.profiles.get(player);
        if (playerProfile.getExperiencesPoints() >= playerProfile.getExperienceNeeded()) {
            int newExperience = (playerProfile.getExperiencesPoints() - playerProfile.getExperienceNeeded());

            playerProfile.setStat(player, PlayerProfile.Stat.EXPERIENCEPOINTS, newExperience);
            playerProfile.addStat(player,PlayerProfile.Stat.LEVEL, 1);
            playerProfile.addStat(player, PlayerProfile.Stat.POINTSAVAILABLES, 3);

            playerProfile.setExperienceNeeded(levelNeededPerLevel.get(PlayerProfileManager.profiles.get(player).getLevel()));
            return true;
        }
        return false;
    }
}
