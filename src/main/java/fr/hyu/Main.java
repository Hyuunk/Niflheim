package fr.hyu;

import fr.hyu.niflheim.NiflheimCommands;
import fr.hyu.niflheim.chat.ChatEvent;
import fr.hyu.niflheim.chat.JoinLeaveEvents;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import fr.hyu.niflheimPermissions.player.PlayerRankProfile;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main INSTANCE;

    static {
        Main.INSTANCE = null;
    }

    public void onEnable() {
        Main.INSTANCE = this;
        ChatEvent.EventStarter();
        PlayerRankProfile.initRank();

        this.getCommand("gma").setExecutor(new NiflheimCommands());
        this.getCommand("gmc").setExecutor(new NiflheimCommands());
        this.getCommand("gms").setExecutor(new NiflheimCommands());
        this.getCommand("gmsp").setExecutor(new NiflheimCommands());
        this.getCommand("setspawn").setExecutor(new NiflheimCommands());
        this.getCommand("spawn").setExecutor(new NiflheimCommands());
        this.getCommand("permstest").setExecutor(new NiflheimCommands());

        this.getServer().getPluginManager().registerEvents((Listener) new PlayerProfileManager(), this);
        this.getServer().getPluginManager().registerEvents((Listener)new JoinLeaveEvents(),this);

    }

    public void onDisable() {
    }
}