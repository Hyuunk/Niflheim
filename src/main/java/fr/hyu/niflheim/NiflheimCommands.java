package fr.hyu.niflheim;

import fr.hyu.Main;
import fr.hyu.Toolskit;
import fr.hyu.niflheim.chat.ChatManager;
import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheimMMO.classes.PlayerClassesProfile;
import fr.hyu.niflheimMMO.experience.Experiences;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import fr.hyu.niflheimPermissions.player.PlayerRankProfile;
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

        final String newLine = System.getProperty("line.separator");

        if (sender instanceof Player) {
            Player player = (Player) sender;

            switch (cmd.getLabel().toUpperCase()) {
                case "GMA":
                    setGameMode(GameMode.ADVENTURE, args, player);
                    break;
                case "GMC":
                    setGameMode(GameMode.CREATIVE, args, player);
                    break;
                case "GMS":
                    setGameMode(GameMode.SURVIVAL, args, player);
                    break;
                case "GMSP":
                    setGameMode(GameMode.SPECTATOR, args, player);
                    break;

                case "CHATCOLOR":
                    if(!PlayerRankProfile.hasPermission(player, "niflheim.chatcolor")) { player.sendMessage(ChatColor.RED + "Tu n'as pas la permission !"); break;}
                    player.sendMessage(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "----------" + ChatColor.RED + ChatColor.BOLD + " C" + ChatColor.GOLD + ChatColor.BOLD + "h" + ChatColor.YELLOW + ChatColor.BOLD + "a" + ChatColor.GREEN + ChatColor.BOLD + "t" + ChatColor.DARK_GREEN + ChatColor.BOLD + "C" + ChatColor.DARK_AQUA + ChatColor.BOLD + "o" + ChatColor.BLUE + ChatColor.BOLD + "l" + ChatColor.DARK_BLUE + ChatColor.BOLD + "o" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "r " + ChatColor.GRAY + ChatColor.STRIKETHROUGH + "----------" + newLine + ChatColor.RESET + "&4: " + ChatColor.DARK_RED + " dark_red " + ChatColor.RESET + "    &c: " + ChatColor.RED + " red " + newLine + ChatColor.RESET + "&6: " + ChatColor.GOLD + " gold " + ChatColor.RESET + "          &e: " + ChatColor.YELLOW + " yellow " + newLine + ChatColor.RESET + "&2: " + ChatColor.DARK_GREEN + " dark_green " + ChatColor.RESET + " &a: " + ChatColor.GREEN + " green " + newLine + ChatColor.RESET + "&3: " + ChatColor.DARK_AQUA + " dark_aqua " + ChatColor.RESET + "  &b: " + ChatColor.AQUA + " aqua " + newLine + ChatColor.RESET + "&1: " + ChatColor.DARK_BLUE + " dark_blue " + ChatColor.RESET + "   &9: " + ChatColor.BLUE + " blue " + newLine + ChatColor.RESET + "&5: " + ChatColor.DARK_PURPLE + " dark_purple " + ChatColor.RESET + "&d: " + ChatColor.LIGHT_PURPLE + " light_purple " + newLine + ChatColor.RESET + "&7: " + ChatColor.GRAY + " gray " + ChatColor.RESET + "          &f: " + ChatColor.WHITE + " white " + newLine + ChatColor.RESET + "&0: " + ChatColor.BLACK + " black " + ChatColor.RESET + "         &8: " + ChatColor.DARK_GRAY + " dark_gray " + newLine + ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "-------------------------------" + newLine + ChatColor.RESET + "&k: " + ChatColor.MAGIC + " magic " + ChatColor.RESET + "  &l: " + ChatColor.BOLD + " bold " + ChatColor.RESET + "  &m: " + ChatColor.STRIKETHROUGH + " strikethrough " + newLine + ChatColor.RESET + "&n: " + ChatColor.UNDERLINE + " underline " + ChatColor.RESET + "  &o: " + ChatColor.ITALIC + " italic " + ChatColor.RESET + "  &r: " + ChatColor.RESET + " reset ");
                    break;

                case "PATCHNOTE": {
                    File file = new File(Main.INSTANCE.getDataFolder(), "Niflheim/patchnote.yml");
                    FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
                    String message = config.getString("this.patchnote");
                    player.sendMessage(ChatColor.GREEN + "Patchnote " + config.getString("this.version") + ":" + newLine + ChatColor.GRAY + message);
                    break;
                }

                    ///SPAWN
                case "SPAWN":
                    handleSpawnCommand(player);
                    break;
                case "SETSPAWN":
                    if(!PlayerRankProfile.hasPermission(player, "niflheim.setspawn")) { player.sendMessage(ChatColor.RED + "Tu n'as pas la permission !"); break;}
                    handleSetSpawnCommand(player);
                    break;



                case "TEST": {
                    if(!PlayerRankProfile.hasPermission(player, "niflheim.test")) { player.sendMessage(ChatColor.RED + "Tu n'as pas la permission !"); break;}
                    player.sendMessage();
                    break;
                }
            }
        }
        return false;
    }


    public void setGameMode(final GameMode gamemode, final String[] args, final Player player) {
        if(!PlayerRankProfile.hasPermission(player, "niflheim.gamemode")) { player.sendMessage(ChatColor.RED + "Tu n'as pas la permission !"); return;}
        switch (Toolskit.checkArgs(player, Toolskit.CommandsType.PLAYER, args)) {
            case NORMALLY: {
                final Player targetPlayer = Bukkit.getPlayer(args[0]);
                targetPlayer.setGameMode(gamemode);
                targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0f, 1.0f);
                player.sendMessage(ChatManager.MessageType.CLASSIC.getMessage() + "Tu viens de passer en mode " + ChatColor.GREEN + gamemode.name().toLowerCase() + " " + ChatColor.GRAY + targetPlayer.getName() + ".");
                targetPlayer.sendMessage(ChatManager.MessageType.CLASSIC.getMessage() + "Ton gamemode a été changé en " + ChatColor.GREEN + gamemode.name().toLowerCase() + ChatColor.GRAY + ".");
                break;
            }
            case FIRST_ARGUMENT_ONSELF: {
                player.setGameMode(gamemode);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0f, 1.0f);
                player.sendMessage(ChatManager.MessageType.CLASSIC.getMessage() + "Tu viens de passer en mode " + ChatColor.GREEN + gamemode.name().toLowerCase() + ChatColor.GRAY + ".");
                break;
            }
            case PLAYER_NONEXISTANT: {
                player.sendMessage(ChatManager.MessageType.ERROR.getMessage() + "Invalid Player. Try /gm(a,c,s,sp)" + ChatColor.RED + " [<targetPlayer>]" + ChatColor.GRAY + ".");
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
            player.sendMessage(ChatColor.GREEN + "Le spawn a été défini à votre emplacement actuel.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
        player.sendMessage(ChatColor.GREEN + "Vous avez été téléporté au spawn");

    }
}