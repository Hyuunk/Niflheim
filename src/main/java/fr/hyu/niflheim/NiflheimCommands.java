package fr.hyu.niflheim;

import fr.hyu.Main;
import fr.hyu.Toolskit;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
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
                    //if (!Player.hasPermission) break;
                    setGameMode(GameMode.ADVENTURE, args, player); break;
                }
                case "GMC": {
                    //if (!Player.hasPermission) break;
                    setGameMode(GameMode.CREATIVE, args, player); break;
                }
                case "GMS": {
                    //if (!Player.hasPermission) break;
                    setGameMode(GameMode.SURVIVAL, args, player); break;
                }
                case "GMSP": {
                    //if (!Player.hasPermission) break;
                    setGameMode(GameMode.SPECTATOR, args, player); break;
                }
                case "SPAWN": {
                    handleSpawnCommand(player);
                    break;
                }
                case "SETSPAWN": {
                    if (!player.isOp()) break;
                    handleSetSpawnCommand(player);
                    break;
                }
                case "PERMSTEST" : {
                    player.sendMessage(PlayerProfileManager.profiles.get(sender).getRank().toString());
                    break;
                }

            }
        }
        return false;
    }

    public void setGameMode(final GameMode gamemode, final String[] args, final Player player) {
        if (!player.isOp()) return;
        switch (Toolskit.checkArgs(player, Toolskit.CommandsType.PLAYER, args)) {
            case 0: {
                final Player targetPlayer = Bukkit.getPlayer(args[0]);
                targetPlayer.setGameMode(gamemode);
                targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0f, 1.0f);
                player.sendMessage(String.valueOf("ChatManager.MessageType.OLYMPCLASSIC.getMessage())" + "Tu viens de passer en mode " + ChatColor.GREEN + gamemode.name().toLowerCase() + " " + ChatColor.GRAY + targetPlayer.getName() + "."));
                targetPlayer.sendMessage(String.valueOf("ChatManager.MessageType.OLYMPCLASSIC.getMessage())" + "Ton gamemode a \u00e9t\u00e9 chang\u00e9 en " + ChatColor.GREEN + gamemode.name().toLowerCase() + ChatColor.GRAY + "."));
                break;
            }
            case 1: {
                player.setGameMode(gamemode);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0f, 1.0f);
                player.sendMessage(String.valueOf("ChatManager.MessageType.OLYMPCLASSIC.getMessage())" + "Tu viens de passer en mode " + ChatColor.GREEN + gamemode.name().toLowerCase() + ChatColor.GRAY + "."));
                break;
            }
            case -3: {
                player.sendMessage(String.valueOf("ChatManager.MessageType.OLYMPERROR.getMessage())" + "Invalid Player. Try /gm(a,c,s,sp)" + ChatColor.RED + " [<targetPlayer>]" + ChatColor.GRAY + "."));
                break;
            }
        }
    }
    // Système de spawn

    private void handleSetSpawnCommand(Player player) {
        Location spawnLocation = player.getLocation();
        File file = new File(Main.INSTANCE.getDataFolder(), "spawn.yml");
        FileConfiguration spawnConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(file);

        spawnConfig.set("spawn.x", spawnLocation.getBlockX());
        spawnConfig.set("spawn.y", spawnLocation.getBlockY());
        spawnConfig.set("spawn.z", spawnLocation.getBlockZ());
        spawnConfig.set("spawn.yaw", spawnLocation.getYaw());
        spawnConfig.set("spawn.pitch", spawnLocation.getPitch());
        try {
            spawnConfig.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        player.sendMessage(ChatColor.GREEN + "Le spawn a été défini à votre emplacement actuel.");
    }

    private void handleSpawnCommand(Player player) {
        File file = new File(Main.INSTANCE.getDataFolder(), "spawn.yml");
        FileConfiguration spawnConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        double x = spawnConfig.getDouble("spawn.x");
        double y = spawnConfig.getDouble("spawn.y");
        double z = spawnConfig.getDouble("spawn.z");
        double yaw = spawnConfig.getDouble("spawn.yaw");
        double pitch = spawnConfig.getDouble("spawn.pitch");

        Location spawnLocation = new Location(Bukkit.getWorld("world"), x, y, z, (float) yaw, (float) pitch);
        player.teleport(spawnLocation);
        player.sendMessage(ChatColor.GREEN + "Vous avez été télporté au spawn");

    }
}