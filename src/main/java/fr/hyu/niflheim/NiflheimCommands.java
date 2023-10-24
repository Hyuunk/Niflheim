package fr.hyu.niflheim;

import fr.hyu.Toolskit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

}

