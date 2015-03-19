package me.mrson.mceconplus.API.Files;

import me.mrson.mceconplus.McEconPlus;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dominic on 3/17/2015.
 */
public class Items {
    private static File folder;

    private static File itemsFile;

    private static FileConfiguration items;

    private static McEconPlus plugin;

    public Items(McEconPlus plugin){
        this.plugin = plugin;
        this.folder = plugin.getDataFolder();

        this.itemsFile = new File(plugin.getDataFolder(), "items.yml");

        this.items = YamlConfiguration.loadConfiguration(itemsFile);
    }

    public Items() {

    }

    public static void createFiles(){
        if(!itemsFile.exists()){
            plugin.logger().fine("Creating config.yml...");
            plugin.saveResource("items.yml", true);
            plugin.logger().fine("File config.yml created.");
        }
    }

    public static File getItemsFile(){
        return itemsFile;
    }

    public static FileConfiguration getItems(){
        return items;
    }

    public static void saveItemsFiles(){
        if(itemsFile == null || items == null){
            return;
        }
        try{
            items.save(itemsFile);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void reloadItemsFile(){
        items = YamlConfiguration.loadConfiguration(itemsFile);
    }

}
