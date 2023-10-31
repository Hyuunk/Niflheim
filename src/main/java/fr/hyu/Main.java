package fr.hyu;

import fr.hyu.niflheim.NiflheimCommands;
import fr.hyu.niflheim.chat.ChatEvent;
import fr.hyu.niflheim.chat.ChatManager;
import fr.hyu.niflheim.chat.JoinLeaveEvents;
import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheim.gui.menu.MenuManager;
import fr.hyu.niflheim.gui.menu.classes.ClassesMenu;
import fr.hyu.niflheimMMO.OnJoinItem;
import fr.hyu.niflheimMMO.classes.PlayerClassesManager;
import fr.hyu.niflheimMMO.experience.ExperienceGetter;
import fr.hyu.niflheimMMO.mobs.MobsSpawning;
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
        this.getCommand("test").setExecutor(new NiflheimCommands());


        this.getServer().getPluginManager().registerEvents((Listener) new PlayerProfileManager(), this);
        this.getServer().getPluginManager().registerEvents((Listener) new MenuManager(),this);
        this.getServer().getPluginManager().registerEvents((Listener) new JoinLeaveEvents(),this);
        this.getServer().getPluginManager().registerEvents((Listener) new OnJoinItem(),this);
        this.getServer().getPluginManager().registerEvents((Listener) new ChatManager(), this);
        this.getServer().getPluginManager().registerEvents((Listener) new GuiManager(), this);
        this.getServer().getPluginManager().registerEvents((Listener) new PlayerClassesManager(), this);
        this.getServer().getPluginManager().registerEvents((Listener) new ClassesMenu(), this);
        this.getServer().getPluginManager().registerEvents((Listener) new ExperienceGetter(), this);
        this.getServer().getPluginManager().registerEvents((Listener) new MobsSpawning(), this);
    }

    public void onDisable() {
    }
}