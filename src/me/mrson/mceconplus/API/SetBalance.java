package me.mrson.mceconplus.API;

import me.mrson.mceconplus.API.Files.Players;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

/**
 * Created by Dominic on 3/18/2015.
 */
public class SetBalance {

    static boolean b;

    @SuppressWarnings("deprecation")
    public static boolean SetBalance(String s, double d){
        OfflinePlayer user = Bukkit.getServer().getOfflinePlayer(s);
        UUID uuid = user.getUniqueId();

        if(Players.getPlayers().contains(String.valueOf(uuid))){
            if(Players.getPlayers().contains(String.valueOf(uuid) + ".balance")){
                Players.getPlayers().set(String.valueOf(uuid) + ".balance", d);
                Players.savePlayersFile();
                Players.reloadPlayersFile();
                b = true;
            }else{
                b = false;
            }
        }else{
            b = false;
        }
        return b;
    }
}
