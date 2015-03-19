package me.mrson.mceconplus.API.Files;

import me.mrson.mceconplus.McEconPlus;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dominic on 3/17/2015.
 */
public class Config {
    private static File folder;

    private static File configFile;

    private static FileConfiguration config;

    private static McEconPlus plugin;

    public Config(McEconPlus plugin){
        this.plugin = plugin;
        this.folder = plugin.getDataFolder();

        this.configFile = new File(plugin.getDataFolder(), "config.yml");

        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public Config() {

    }

    public static void createFiles(){
        if(!configFile.exists()){
            plugin.logger().fine("Creating config.yml...");
            plugin.saveResource("config.yml", true);
            plugin.logger().fine("File config.yml created.");
        }
    }

    public static File getConfigFile(){
        return configFile;
    }

    public static FileConfiguration getConfig(){
        return config;
    }

    public static void saveConfigFiles(){
        if(configFile == null || config == null){
            return;
        }
        try{
            config.save(configFile);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void reloadConfigFile(){
        config = YamlConfiguration.loadConfiguration(configFile);
    }

}
