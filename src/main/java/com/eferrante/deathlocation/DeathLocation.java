package com.eferrante.deathlocation;
import com.eferrante.deathlocation.Commands.MainCommand;
import com.eferrante.deathlocation.Listeners.DeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathLocation extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("DeathLocation Plugin Successfully Enabled");
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getCommand("deathlocation").setExecutor(new MainCommand(this));
    }

    @Override
    public void onDisable() {
        System.out.println("DeathLocation Plugin Successfully Disabled");
    }
}

