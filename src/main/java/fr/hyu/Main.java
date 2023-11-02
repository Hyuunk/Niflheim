package fr.hyu;

import fr.hyu.niflheim.NiflheimCommands;
import fr.hyu.niflheim.chat.ChatEvent;
import fr.hyu.niflheim.chat.ChatManager;
import fr.hyu.niflheim.chat.JoinLeaveEvents;
import fr.hyu.niflheim.gui.GuiManager;
import fr.hyu.niflheim.gui.menu.MenuManager;
import fr.hyu.niflheim.gui.menu.classes.ClassesMenu;
import fr.hyu.niflheimEconomy.EconomyCommands;
import fr.hyu.niflheimMMO.MMOCommands;
import fr.hyu.niflheimMMO.OnEventMMO;
import fr.hyu.niflheimMMO.classes.PlayerClassesManager;
import fr.hyu.niflheimMMO.experience.ExperienceGetter;
import fr.hyu.niflheimMMO.experience.Experiences;
import fr.hyu.niflheimMMO.items.ItemsListeners;
import fr.hyu.niflheimMMO.mobs.MonstersListeners;
import fr.hyu.niflheimMMO.mobs.MonsterSpawning;
import fr.hyu.niflheimPermissions.PermissionsCommands;
import fr.hyu.niflheimPermissions.player.PlayerProfileManager;
import fr.hyu.niflheimPermissions.player.PlayerRankProfile;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.N;

public class Main extends JavaPlugin {

    public static Main INSTANCE;

    public void onEnable() {
        Main.INSTANCE = this;
        ChatEvent.EventStarter();
        PlayerRankProfile.initRank();
        Experiences.initExperiences();

        this.getCommand("gma").setExecutor(new NiflheimCommands());
        this.getCommand("gmc").setExecutor(new NiflheimCommands());
        this.getCommand("gms").setExecutor(new NiflheimCommands());
        this.getCommand("gmsp").setExecutor(new NiflheimCommands());
        this.getCommand("setspawn").setExecutor(new NiflheimCommands());
        this.getCommand("spawn").setExecutor(new NiflheimCommands());
        this.getCommand("permstest").setExecutor(new NiflheimCommands());
        this.getCommand("test").setExecutor(new NiflheimCommands());
        this.getCommand("chatcolor").setExecutor(new NiflheimCommands());
        this.getCommand("patchnote").setExecutor(new NiflheimCommands());

        this.getCommand("niflheimPerms").setExecutor(new PermissionsCommands());
        this.getCommand("niflheimMMO").setExecutor(new MMOCommands());
        this.getCommand("niflheimEconomy").setExecutor(new EconomyCommands());
        this.getCommand("xp").setExecutor(new MMOCommands());

        this.getServer().getPluginManager().registerEvents(new PlayerProfileManager(), this);
        this.getServer().getPluginManager().registerEvents(new MenuManager(),this);
        this.getServer().getPluginManager().registerEvents(new JoinLeaveEvents(),this);
        this.getServer().getPluginManager().registerEvents(new OnEventMMO(),this);
        this.getServer().getPluginManager().registerEvents(new ChatManager(), this);
        this.getServer().getPluginManager().registerEvents(new GuiManager(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerClassesManager(), this);
        this.getServer().getPluginManager().registerEvents(new ClassesMenu(), this);
        this.getServer().getPluginManager().registerEvents(new ExperienceGetter(), this);
        this.getServer().getPluginManager().registerEvents(new MonsterSpawning(), this);
        this.getServer().getPluginManager().registerEvents(new MonstersListeners(), this);
        this.getServer().getPluginManager().registerEvents(new ItemsListeners(), this);
    }

    public void onDisable() {
    }
}