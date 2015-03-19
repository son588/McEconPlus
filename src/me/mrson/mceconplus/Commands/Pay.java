package me.mrson.mceconplus.Commands;

import me.mrson.mceconplus.API.GetBalance;
import me.mrson.mceconplus.API.SetBalance;
import me.mrson.mceconplus.McEconPlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Dominic on 3/18/2015.
 */
public class Pay implements CommandExecutor{
    McEconPlus mcEconPlus;
    //test

    public Pay(McEconPlus mcEconPlus) {
        this.mcEconPlus = mcEconPlus;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args){

        if(cmd.getName().equalsIgnoreCase("pay") && sender.hasPermission("mcep.pay")){
            if(sender instanceof Player){
                if(args.length >= 1){
                    if(args.length == 2){
                        try{
                            Player target = Bukkit.getServer().getPlayer(args[0]);
                            double value = Math.round(Double.parseDouble(args[1]) * 100.0) / 100.0;
                            boolean givebalance = SetBalance.SetBalance(args[0], GetBalance.getBalance(args[0]) + value);
                            boolean removebalance = SetBalance.SetBalance(sender.getName(), GetBalance.getBalance(sender.getName()) - value);
                            double remainingbalance = GetBalance.getBalance(sender.getName()) - value;

                            try{
                                if(target.isOnline()){
                                    if(givebalance == true && removebalance == true){
                                        if(target.getName() == sender.getName()){
                                            sender.sendMessage(ChatColor.RED + "You cannot pay yourself!");
                                        }else{
                                            sender.sendMessage(ChatColor.GREEN + "You have given '" + args[0] + "' $" + value);
                                            sender.sendMessage(ChatColor.GREEN + "Your balance is now $" + remainingbalance);

                                            target.sendMessage(ChatColor.GREEN + "User '" + sender.getName() + "' has given you $" + value);
                                        }
                                    }else{
                                        sender.sendMessage(ChatColor.RED + "Player '" + args[0] + "' does not exist!");
                                    }
                                }else{
                                    sender.sendMessage(ChatColor.RED + "User '" + args[0] + "' has to be online and/or exist!");
                                }
                            }catch(NullPointerException ex){
                                sender.sendMessage(ChatColor.RED + "User '" + args[0] + "' has to be online and/or exist!");
                            }
                        }catch (NumberFormatException ex){
                            sender.sendMessage(ChatColor.RED + "Value has to be a number!");
                        }
                    }else{
                        sender.sendMessage(ChatColor.GREEN + "Usage: /pay {user} {value}");
                    }
                }else{
                    sender.sendMessage(ChatColor.GREEN + "Usage: /pay {user} {value}");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "You have to be a player to execute this command!");
            }

        }

        return false;
    }
}
