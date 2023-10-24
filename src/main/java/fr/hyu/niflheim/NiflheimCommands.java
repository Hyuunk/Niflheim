package fr.hyu.niflheim;

import fr.hyu.Main;
import fr.hyu.Toolskit;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class NiflheimCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            switch (cmd.getLabel().toUpperCase()) {
                case "GMA": {
                    setGameMode(GameMode.ADVENTURE, args, player);
                    break;
                }
                case "GMC": {
                    setGameMode(GameMode.CREATIVE, args, player);
                    break;
                }
                case "GMS": {
                    setGameMode(GameMode.SURVIVAL, args, player);
                    break;
                }
                case "GMSP": {
                    setGameMode(GameMode.SPECTATOR, args, player);
                    break;
                }
                case "SPAWN": {
                    handleSpawnCommand(player);
                    break;
                }
                case "SETSPAWN": {
                    handleSetSpawnCommand(player);
                    break;
                }
            }
        }
        return false;
    }

    public void setGameMode(final GameMode gamemode, final String[] args, final Player player) {
        switch (Toolskit.checkArgs(player, Toolskit.CommandsType.PLAYER, args)) {
            case 0: {
                final Player targetPlayer = Bukkit.getPlayer(args[0]);
                targetPlayer.setGameMode(gamemode);
                targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0f, 1.0f);
                player.sendMessage(ChatColor.GREEN + "Tu viens de passer en mode " + gamemode.name().toLowerCase() + " " + ChatColor.GRAY + targetPlayer.getName() + ".");
                targetPlayer.sendMessage(ChatColor.GREEN + "Ton gamemode a été changé en " + gamemode.name().toLowerCase() + ChatColor.GRAY + ".");
                break;
            }
            case 1: {
                player.setGameMode(gamemode);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0f, 1.0f);
                player.sendMessage(ChatColor.GREEN + "Tu viens de passer en mode " + gamemode.name().toLowerCase() + ChatColor.GRAY + ".");
                break;
            }
            case -3: {
                player.sendMessage(ChatColor.RED + "Invalid Player. Try /gm(a,c,s,sp) [<targetPlayer>]." + ChatColor.GRAY + ".");
                break;
            }
        }
    }

    // Système de spawn
    private FileConfiguration spawnConfig = null;
    private File spawnFile = null;

    private void initSpawnConfig() {
        if (spawnFile == null) {
            spawnFile = new File(Main.INSTANCE.getDataFolder(), "Niflheim/spawn.yml");
        }
        spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);

        if (!spawnFile.exists()) {
            try {
                spawnFile.createNewFile();
            } catch (IOException e) {
                Main.INSTANCE.getLogger().warning("Impossible de créer le fichier de configuration spawn.yml.");
            }
        }
    }

    private void saveSpawnConfig() {
        if (spawnConfig == null || spawnFile == null) {
            return;
        }
        try {
            spawnConfig.save(spawnFile);
        } catch (IOException e) {
            Main.INSTANCE.getLogger().warning("Impossible de sauvegarder le fichier de configuration spawn.yml.");
        }
    }

    private void handleSetSpawnCommand(Player player) {
        Location spawnLocation = player.getLocation();

        spawnConfig.set("spawn.world", spawnLocation.getWorld().getName());
        spawnConfig.set("spawn.x", spawnLocation.getBlockX());
        spawnConfig.set("spawn.y", spawnLocation.getBlockY());
        spawnConfig.set("spawn.z", spawnLocation.getBlockZ());

        saveSpawnConfig();

        player.sendMessage(ChatColor.GREEN + "Le spawn a été défini à votre emplacement actuel.");
    }

    private void handleSpawnCommand(Player player) {
        if (spawnConfig.contains("spawn")) {
            World world = Bukkit.getWorld(spawnConfig.getString("spawn.world"));
            int x = spawnConfig.getInt("spawn.x");
            int y = spawnConfig.getInt("spawn.y");
            int z = spawnConfig.getInt("spawn.z");

            Location spawnLocation = new Location(world, x, y, z);
            player.teleport(spawnLocation);
            player.sendMessage(ChatColor.GREEN + "Vous avez été téléporté au spawn personnalisé.");
        } else {
            player.sendMessage(ChatColor.RED + "Le spawn personnalisé n'est pas défini.");
        }
    }
}
