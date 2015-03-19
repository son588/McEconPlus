package me.mrson.mceconplus.API.Files;

import me.mrson.mceconplus.McEconPlus;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dominic on 3/17/2015.
 */
public class Players {
    private static File folder;

    private static File playersFile;

    private static FileConfiguration players;

    private static McEconPlus plugin;

    public Players(McEconPlus plugin){
        this.plugin = plugin;
        this.folder = plugin.getDataFolder();

        this.playersFile = new File(plugin.getDataFolder(), "players.yml");

        this.players = YamlConfiguration.loadConfiguration(playersFile);
    }

    public Players() {

    }

    public static void createFiles(){
        if(!playersFile.exists()){
            plugin.logger().fine("Creating players.yml...");
            plugin.saveResource("players.yml", true);
            plugin.logger().fine("File players.yml created.");
        }
    }

    public static File getPlayersFile(){
        return playersFile;
    }

    public static FileConfiguration getPlayers(){
        return players;
    }

    public static void savePlayersFile() {
        if (playersFile == null || players == null) {
            return;
        }
        try {
            players.save(playersFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void reloadPlayersFile(){
        players = YamlConfiguration.loadConfiguration(playersFile);
    }
}
