package me.mrson.mceconplus.Commands;

import me.mrson.mceconplus.API.Files.Config;
import me.mrson.mceconplus.API.Files.Items;
import me.mrson.mceconplus.API.Files.Players;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created by Dominic on 3/19/2015.
 */
public class Sell implements CommandExecutor{

    @SuppressWarnings("depricated")
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args){
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if(cmd.getName().equalsIgnoreCase("sell") && sender.hasPermission("mcep.sell")){
            boolean enabled = Config.getConfig().getBoolean("shops.isEnabled");

            if(enabled == true){
                if(args.length >= 1){
                    if(args[0].equalsIgnoreCase("hand")){
                        if(!(args[1].isEmpty())){
                            try{
                                if(player.getItemInHand().getType() == Material.AIR){
                                    sender.sendMessage(ChatColor.RED + "You cannot sell air!");
                                }

                                int amount = Integer.parseInt(args[1]);
                                int ItemID = player.getItemInHand().getTypeId();
                                byte ItemDataValue = player.getItemInHand().getData().getData();
                                String Item = String.valueOf(ItemID) + ":" + String.valueOf(ItemDataValue);

                                if(Items.getItems().contains(Item)){
                                    if(Items.getItems().contains(Item + ".sell")){
                                        double value = Items.getItems().getDouble(Item + ".sell");
                                        double PlayersBalance = Players.getPlayers().getDouble(String.valueOf(uuid) + ".balance") + value;

                                        Players.getPlayers().set(String.valueOf(uuid) + ".balance", value);
                                        Players.savePlayersFile();
                                        Players.reloadPlayersFile();

                                        ItemStack itemStack = new ItemStack(ItemID, amount, ItemDataValue);
                                        player.getInventory().removeItem(itemStack);
                                        player.updateInventory();

                                        sender.sendMessage(ChatColor.GREEN + "You have sold " + ChatColor.AQUA + amount + " " + Material.getMaterial(ItemID).toString() + ChatColor.GREEN + " for $" + value + " each");
                                    }else{
                                        sender.sendMessage(ChatColor.RED + "Item is not for sale!");
                                    }
                                }else{
                                    sender.sendMessage(ChatColor.RED + "Item is not for sale!");
                                }

                            }catch(NumberFormatException ex){
                                sender.sendMessage(ChatColor.RED + "Amount has to be a number!");
                            }
                        }else{
                            if(player.getItemInHand().getType() == Material.AIR){
                                sender.sendMessage(ChatColor.RED + "You cannot sell air!");
                            }

                            int ItemID = player.getItemInHand().getTypeId();
                            byte ItemDataValue = player.getItemInHand().getData().getData();
                            String Item = String.valueOf(ItemID) + ":" + String.valueOf(ItemDataValue);

                            if(Items.getItems().contains(Item)){
                                if(Items.getItems().contains(Item + ".sell")){
                                    double value = Items.getItems().getDouble(Item + ".sell");
                                    double PlayersBalance = Players.getPlayers().getDouble(String.valueOf(uuid) + ".balance") + value;

                                    Players.getPlayers().set(String.valueOf(uuid) + ".balance", value);
                                    Players.savePlayersFile();
                                    Players.reloadPlayersFile();

                                    ItemStack itemStack = new ItemStack(ItemID, 1, ItemDataValue);
                                    player.getInventory().removeItem(itemStack);
                                    player.updateInventory();

                                    sender.sendMessage(ChatColor.GREEN + "You have sold " + ChatColor.AQUA + "1 " + Material.getMaterial(ItemID).toString() + ChatColor.GREEN + " for $" + value);
                                }else{
                                    sender.sendMessage(ChatColor.RED + "Item is not for sale!");
                                }
                            }else{
                                sender.sendMessage(ChatColor.RED + "Item is not for sale!");
                            }
                        }
                    }else{
                        sender.sendMessage(ChatColor.GREEN + "Usage: /sell hand [amount]");
                    }
                }else{
                    sender.sendMessage(ChatColor.GREEN + "Usage: /sell hand [amount]");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "Shops are currently disabled. Contact the owner to enable it!");
            }
        }

        return false;
    }
}
