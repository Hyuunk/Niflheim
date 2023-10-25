package fr.hyu.niflheim.chat;

import fr.hyu.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.w3c.dom.events.Event;

public class ChatEvent implements Listener {
    public static void EventStarter() {
        // You stuff with your file
        new BukkitRunnable() { // A BukkitRunnable who will execute every second (20 ticks)

           @Override
            public void run() { // The method who will be executed every second
            Main.INSTANCE.getServer().broadcastMessage(ChatColor.GREEN + "EVENT (pas fait miskin)");
            }
        }.runTaskTimerAsynchronously(Main.INSTANCE, 0L, 24000L);
    }
}
