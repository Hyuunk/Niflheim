package fr.hyu.niflheimPermissions;

import fr.hyu.Toolskit;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import fr.hyu.niflheimPermissions.player.PlayerRankProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionsCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            switch (cmd.getLabel().toUpperCase()) {

                case "NIFLHEIMPERMS": {
                    if(!PlayerRankProfile.hasPermission(player, "niflheim.niflheimperms")) { player.sendMessage(ChatColor.RED + "Tu n'as pas la permission !"); return false; }
                    switch (args[0].toUpperCase()) {
                        case "SET":
                            if(!PlayerRankProfile.hasPermission(player, "niflheim.niflheimperms.set")) { player.sendMessage(ChatColor.RED + "Tu n'as pas la permission !"); return false; }
                            switch (Toolskit.checkArgs(player, Toolskit.CommandsType.NOUN_NOUN_PLAYER, args)) {
                                case NORMALLY:
                                    PlayerProfileManager.profiles.get(Bukkit.getPlayer(args[2])).setRank(PlayerRankProfile.Rank.valueOf(args[1].toUpperCase()));
                                    break;
                                case PLAYER_NONEXISTANT:
                                    player.sendMessage("ERREUR");
                                    break;
                                case FIRST_ARGUMENT_ERROR:
                                    player.sendMessage("ERREUR");
                                    break;
                                case SECOND_ARGUMENT_ERROR:
                                    player.sendMessage("ERREUR");
                                    break;
                                case THIRD_ARGUMENT_ERROR:
                                    player.sendMessage("ERREUR");
                                    break;
                                case THIRD_ARGUMENT_ONSELF:
                                    PlayerProfileManager.profiles.get(player).setRank(PlayerRankProfile.Rank.valueOf(args[1].toUpperCase()));
                                    break;
                            }

                            break;
                        case "OP":
                            if(!PlayerRankProfile.hasPermission(player, "niflheim.niflheimperms.op")) { player.sendMessage(ChatColor.RED + "Tu n'as pas la permission !"); return false; }
                            if(player.isOp()) {player.setOp(false); break;} else { player.setOp(true); break; }
                    }
                break;
                }



            }
        } return false;
    }
}
