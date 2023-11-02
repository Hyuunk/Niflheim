package fr.hyu.niflheim.chat;

import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import fr.hyu.niflheimPermissions.player.PlayerRankProfile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import fr.hyu.niflheimPermissions.player.PlayerRankProfile.Rank;

public class ChatManager implements Listener {

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event) {
        this.toFormat(event.getPlayer(), event.getMessage(), event);
    }

    public void toFormat(Player player, final String message, final AsyncPlayerChatEvent event) {
        Rank rank = PlayerProfileManager.profiles.get(player).getRank();
        switch (rank) {

            case GERANT:
                event.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD + "Gérant" + ChatColor.GRAY + "] " + player.getName() + ": " + colourise(message));
                break;
            case RESPONSABLE:
                event.setFormat(ChatColor.GRAY + "[" + ChatColor.RED + "Responsable" + ChatColor.GRAY + "] " + player.getName() + ": " + colourise(message));
                break;
            case DEVELOPPEUR:
                event.setFormat(ChatColor.GRAY + "[" + ChatColor.BLUE + "Développeur" + ChatColor.GRAY + "] " + player.getName() + ": " + colourise(message));
                break;
            case MODERATEUR:
                event.setFormat(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Modérateur" + ChatColor.GRAY + "] " + player.getName() + ": " + colourise(message));
                break;
            case BUILDER:
                event.setFormat(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "Builder" + ChatColor.GRAY + "] " + player.getName() + ": " + colourise(message));
                break;
            case ASSISTANT:
                event.setFormat(ChatColor.GRAY + "[" + ChatColor.GREEN + "Assistant" + ChatColor.GRAY + "] " + player.getName() + ": " + colourise(message));
                break;
            case AMI:
                event.setFormat(ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + "Ami" + ChatColor.GRAY + "] " + player.getName() + ": " + colourise(message));
                break;
            case DEFAULT:
                event.setFormat(player.getName() + ": " + message);
                break;
        }
    }

    private static String colourise(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public enum MessageType
    {
        CLASSIC("CLASSIC", ChatColor.GRAY + "[" + ChatColor.GOLD + "Niflheim" + ChatColor.GRAY + "]" + ChatColor.WHITE + " > " + ChatColor.GRAY),
        RIGHT("RIGHT", ChatColor.GRAY + "[" + ChatColor.GREEN + "Niflheim" + ChatColor.GRAY + "]" + ChatColor.WHITE + " > " + ChatColor.GRAY),
        ERROR("ERROR", ChatColor.GRAY + "[" + ChatColor.RED + "Niflheim" + ChatColor.GRAY + "]" + ChatColor.WHITE + " > " + ChatColor.GRAY),
        UNKNOWCOMMAND("UNKNOWCOMMAND", "Unknown command. Type \"/help\" for help.");

        private String type;

        private MessageType(final String name, final String type) {
            this.type = type;
        }

        public String getMessage() {
            return this.type;
        }
    }

}
