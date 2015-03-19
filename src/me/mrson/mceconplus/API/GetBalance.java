package me.mrson.mceconplus.API;

import me.mrson.mceconplus.API.Files.Players;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

/**
 * Created by Dominic on 3/17/2015.
 */
public class GetBalance {

    static double balance;

    @SuppressWarnings("deprecation")
    public static double getBalance(String s){
        OfflinePlayer user = Bukkit.getServer().getOfflinePlayer(s);
        UUID uuid = user.getUniqueId();

        if(Players.getPlayers().contains(String.valueOf(uuid))){
            if(Players.getPlayers().contains(String.valueOf(uuid) + ".balance")){
                balance = Players.getPlayers().getDouble(String.valueOf(uuid) + ".balance");
            }else{
                balance = Double.NaN;
            }
        }else{
            balance = Double.NaN;
        }
        return balance;
    }
}