package fr.hyu.niflheimPermissions.player;

import fr.hyu.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PlayerRankProfile {

    private static HashMap<Rank, List<String>> permissions;

    static {
        PlayerRankProfile.permissions = new HashMap<Rank, List<String>>();
    }

    public static void initRank() {
        final File filePermissions = new File(Main.INSTANCE.getDataFolder(), "NiflheimPerms/permissions/permissions.yml");
        final FileConfiguration configPermissions = (FileConfiguration) YamlConfiguration.loadConfiguration(filePermissions);
        if (!filePermissions.exists()) {
            configPermissions.set("rank.gerant.permissions", "niflheim.*");
            configPermissions.set("rank.responsable.permissions", "null");
            configPermissions.set("rank.developpeur.permissions", "null");
            configPermissions.set("rank.moderateur.permissions", "null");
            configPermissions.set("rank.builder.permissions", "null");
            configPermissions.set("rank.assistant.permissions", "null");
            configPermissions.set("rank.ami.permissions", "null");
            configPermissions.set("rank.default.permissions", "null");
            try {
                configPermissions.save(filePermissions);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        Rank[] values;
        for (int length = (values = Rank.values()).length, i = 0; i < length; ++i) {
            final Rank rank = values[i];
            PlayerRankProfile.permissions.put(rank, configPermissions.getStringList("rank." + rank.toString().toLowerCase() + ".permissions"));
        }
    }

    public static boolean hasPermission(final Player player, final String permission) {
        if (permission.indexOf("niflheim.") != -1) {
            return PlayerRankProfile.permissions.get(PlayerProfileManager.profiles.get(player).getRank()).contains("niflheim.*") || PlayerRankProfile.permissions.get(PlayerProfileManager.profiles.get(player).getRank()).contains(permission);
        }
        return PlayerRankProfile.permissions.get(PlayerProfileManager.profiles.get(player).getRank()).contains(permission);
    }

    public static List<String> getPermissions(final Player player) {
        return PlayerRankProfile.permissions.get(PlayerProfileManager.profiles.get(player).getRank());
    }

    public enum Rank
    {
        GERANT("GERANT", "Gérant", ChatColor.GOLD),
        RESPONSABLE("RESPONSABLE", "Responsable", ChatColor.RED),
        DEVELOPPEUR("DEVELOPPEUR", "Développeur", ChatColor.BLUE),
        MODERATEUR("MODERATEUR", "Modérateur", ChatColor.YELLOW),
        BUILDER("BUILDER", "Builder", ChatColor.LIGHT_PURPLE),
        ASSISTANT("ASSISTANT", "Assistant", ChatColor.GREEN),
        AMI("AMI", "Ami", ChatColor.DARK_GREEN),
        DEFAULT("DEFAULT", "Default", ChatColor.WHITE);

        private String name;
        private String displayName;
        private ChatColor color;

        private Rank(String name, String displayName, ChatColor color) {
            this.name = name;
            this.displayName = displayName;
            this.color = color;
        }
        public String getName() {
            return this.name;
        }
        public String getDisplayName(){
            return this.displayName;
        }

        public ChatColor getColor() { return this.color; }


    }
}

