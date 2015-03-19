package me.mrson.mceconplus.Events;

import me.mrson.mceconplus.API.Files.Config;
import me.mrson.mceconplus.API.Files.Players;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

/**
 * Created by Dominic on 3/17/2015.
 */
public class PlayerJoinEvent implements Listener {

    @EventHandler()
    public void onPlayerJoins(org.bukkit.event.player.PlayerJoinEvent event){
        String user = event.getPlayer().getDisplayName();
        UUID uuid = event.getPlayer().getUniqueId();

        if(!Players.getPlayers().contains(String.valueOf(uuid))){
            Players.getPlayers().set(String.valueOf(uuid) + ".username", user);
            Players.getPlayers().set(String.valueOf(uuid) + ".balance", 100);
            Players.savePlayersFile();
            Players.reloadPlayersFile();

            Config.getConfig().set("Players", +1);
            Config.saveConfigFiles();
            Config.reloadConfigFile();
        }

        if(Players.getPlayers().getString(String.valueOf(uuid) + ".username") != user){
            Players.getPlayers().set(String.valueOf(uuid) + ".username", user);
            Players.savePlayersFile();
            Players.reloadPlayersFile();
        }
    }

}
