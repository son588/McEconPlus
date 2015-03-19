package me.mrson.mceconplus.Commands;

import me.mrson.mceconplus.API.GetBalance;
import me.mrson.mceconplus.API.SetBalance;
import me.mrson.mceconplus.McEconPlus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Dominic on 3/18/2015.
 */
public class Economy implements CommandExecutor{
    McEconPlus mcEconPlus;

    public Economy(McEconPlus mcEconPlus) {
        this.mcEconPlus = mcEconPlus;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args){

        if(cmd.getName().equalsIgnoreCase("economy") && sender.hasPermission("mcep.economy")){
            if(args.length >= 1){
                if(args.length == 2){
                    if(args[1].equalsIgnoreCase("balance") && sender.hasPermission("mcep.economy.balance")){
                        double balance = GetBalance.getBalance(args[0]);
                        if(!(Double.isNaN(balance))){
                            sender.sendMessage(ChatColor.GREEN + "'" + args[0] + "' balance is " + balance);
                        }else if(Double.isNaN(balance)){
                            sender.sendMessage(ChatColor.RED + "Player '" + args[0] + "' does not exist!");
                        }
                    }else{
                        sender.sendMessage(ChatColor.GREEN + "Usage: /economy {user} {balance|setbalance|givebalance} [value]");
                    }
                }else if(args.length == 3){
                    if(args[1].equalsIgnoreCase("setbalance") && sender.hasPermission("mcep.economy.setbalance")){
                        try{
                            double value = Math.round(Double.parseDouble(args[2]) * 100.0) / 100.0;
                            boolean setbalance = SetBalance.SetBalance(args[0], value);

                            if(setbalance == true){
                                sender.sendMessage(ChatColor.GREEN + "You have set '" + args[0] + "' balance to " + value);
                            }else{
                                sender.sendMessage(ChatColor.RED + "Player '" + args[0] + "' does not exist!");
                            }
                        }catch(NumberFormatException ex){
                            sender.sendMessage(ChatColor.RED + "Value has to be a number!");
                        }
                    }else if(args[1].equalsIgnoreCase("givebalance") && sender.hasPermission("mcep.admin.givebalance")){
                        try{
                            double value = Math.round(Double.parseDouble(args[2]) * 100.0) / 100.0;
                            boolean setbalance = SetBalance.SetBalance(args[0], GetBalance.getBalance(args[0]) + value);

                            if(setbalance == true){
                                sender.sendMessage(ChatColor.GREEN + "You have gave '" + args[0] + "' $" + value);
                            }else{
                                sender.sendMessage(ChatColor.RED + "Player '" + args[0] + "' does not exist!");
                            }
                        }catch(NumberFormatException ex){
                            sender.sendMessage(ChatColor.RED + "Value has to be a number!");
                        }
                    }else{
                        sender.sendMessage(ChatColor.GREEN + "Usage: /economy {user} {balance|setbalance|givebalance} [value]");
                    }
                }else{
                    sender.sendMessage(ChatColor.GREEN + "Usage: /economy {user} {balance|setbalance|givebalance} [value]");
                }
            }else{
                sender.sendMessage(ChatColor.GREEN + "Usage: /economy {user} {balance|setbalance|givebalance} [value]");
            }
        }

        return false;
    }
}
