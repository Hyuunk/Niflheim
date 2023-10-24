package fr.hyu.niflheimPermissions.player;

import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class PlayerProfileManager implements Listener
{
    public static HashMap<Player, PlayerProfile> profiles;

    static {
        PlayerProfileManager.profiles = new HashMap<Player, PlayerProfile>();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerProfile getProfile = new PlayerProfile(player);
        PlayerProfileManager.profiles.put(player, getProfile);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerProfileManager.profiles.remove(player);
    }
}
