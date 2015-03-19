package me.mrson.mceconplus;

import me.mrson.mceconplus.API.Files.Config;
import me.mrson.mceconplus.API.Files.Items;
import me.mrson.mceconplus.API.Files.Players;
import me.mrson.mceconplus.Commands.Balance;
import me.mrson.mceconplus.Commands.Economy;
import me.mrson.mceconplus.Commands.Pay;
import me.mrson.mceconplus.Events.PlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created by Dominic on 3/17/2015.
 */
public class McEconPlus extends JavaPlugin{
    private static Plugin plugin;

    public void onEnable(){
        plugin = this;
        logger().fine("McEconPlus is loading...");
        logger().fine("McEconPlus enabled.");

        new Config(this);
        Config.createFiles();

        new Players(this);
        Players.createFiles();

        new Items(this);
        Items.createFiles();

        registerEvent(this, new PlayerJoinEvent());

        getCommand("balance").setExecutor(new Balance(this));
        getCommand("bal").setExecutor(new Balance(this));
        getCommand("money").setExecutor(new Balance(this));
        getCommand("economy").setExecutor(new Economy(this));
        getCommand("pay").setExecutor(new Pay(this));
    }

    public void onDisable(){
        plugin = null;
    }

    public Plugin getPlugin(){
        return plugin;
    }

    public Logger logger(){
        return getLogger();
    }

    public static void registerEvent(Plugin plugin, Listener... listeners){
        for(Listener listener : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}
