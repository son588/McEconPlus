package me.mrson.mceconplus.Commands;

import me.mrson.mceconplus.API.GetBalance;
import me.mrson.mceconplus.McEconPlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Dominic on 3/17/2015.
 */
public class Balance implements CommandExecutor {
    McEconPlus mcEconPlus;

    public Balance(McEconPlus mcEconPlus) {
        this.mcEconPlus = mcEconPlus;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("balance") || cmd.getName().equalsIgnoreCase("bal") || cmd.getName().equalsIgnoreCase("money") && sender.hasPermission("mcep.balance")){
            if(args.length >= 1){
                if(args.length == 1){
                    if(sender.hasPermission("mcep.economy.balance")){
                        double balance = GetBalance.getBalance(args[0]);
                        if(!(Double.isNaN(balance))){
                            sender.sendMessage(ChatColor.GREEN + "'" + args[0] + "' balance is " + balance);
                        }else if(Double.isNaN(balance)){
                            sender.sendMessage(ChatColor.RED + "Player '" + args[0] + "' does not exist!");
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED + "You do not have permission to this command!");
                    }
                }else{
                    sender.sendMessage(ChatColor.RED + "Invalid argument. Usage: /balance (Username)");
                }
            }else{
                double balance = GetBalance.getBalance(sender.getName().toString());
                if(Double.isNaN(balance)){
                    sender.sendMessage(ChatColor.RED + "Error. Balance in invalid. Contact the owner if this doesn't seem right.");
                }else{
                    sender.sendMessage(ChatColor.GREEN + "Your balance is " + balance);
                }
            }
        }
        return false;
    }
}
