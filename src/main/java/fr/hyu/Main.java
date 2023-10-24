package fr.hyu;

import fr.hyu.niflheim.NiflheimCommands;
import fr.hyu.niflheim.chat.JoinLeaveEvents;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main INSTANCE;

    static {
        Main.INSTANCE = null;
    }

    public void onEnable() {
        Main.INSTANCE = this;

        this.getCommand("gma").setExecutor(new NiflheimCommands());
        this.getCommand("gmc").setExecutor(new NiflheimCommands());
        this.getCommand("gms").setExecutor(new NiflheimCommands());
        this.getCommand("gmsp").setExecutor(new NiflheimCommands());

        this.getServer().getPluginManager().registerEvents((Listener)new JoinLeaveEvents(),this);

    }

    public void onDisable() {
    }
}