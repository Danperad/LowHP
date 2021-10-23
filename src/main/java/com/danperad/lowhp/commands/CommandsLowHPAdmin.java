package com.danperad.lowhp.commands;

import com.danperad.lowhp.LowHP;
import com.danperad.lowhpapi.LowHPAPI;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public class CommandsLowHPAdmin implements CommandExecutor {
    boolean hardLife = LowHP.getConf().getBoolean("hardLife");
    boolean after = LowHP.getConf().getBoolean("lifeAfterDeath");
    double hp = LowHP.getConf().getDouble("hp");
    double hpafter = LowHP.getConf().getDouble("hpAfter");

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("lowhpadm") && sender.hasPermission("lowhp.admin")) {
            switch (args[0]) {
                case "setlifes":
                    if (args.length == 3) {
                        try {
                            Player target = sender.getServer().getPlayer(args[1]);
                            assert target != null;
                            int lifes = Integer.parseInt(args[2]) - target.getStatistic(Statistic.DEATHS);
                            target.setStatistic(Statistic.DEATHS, Integer.parseInt(args[2]));
                            // if (LowHP.getPluginSetName()) LowHP.SetName(target);
                            target.getPlayer().sendMessage(ChatColor.GRAY + "Administrators added " + lifes + " lives to you");
                            if (Integer.parseInt(args[2]) > 0 && hardLife) target.setMaxHealth(hp);
                            else if (Integer.parseInt(args[2]) <= 0 && hardLife && after) {
                                target.setMaxHealth(hpafter);
                                target.setHealth(hpafter);
                            } else {
                                target.setMaxHealth(hp);
                                target.setHealth(hp);
                            }
                            sender.sendMessage("Complete!");
                            return true;
                        } catch (NullPointerException e) {
                            sender.sendMessage("Персонажа не существует!");
                            return true;
                        }
                    } else {
                        sender.sendMessage("Неверный формат команды");
                    }
                    break;
                case "setadv":
                    if (args.length == 3) {
                        if (!LowHPAPI.getAPI().hasPlayer(args[1])) {
                            sender.sendMessage("Player not found!");
                            return true;
                        } else {
                            LowHPAPI.getAPI().replacePlayer(args[1], Integer.valueOf(args[2]));
                        }
                        sender.sendMessage("Complete!");
                    } else {
                        sender.sendMessage("Неверный формат команды");
                    }
                    break;
                default:
                    sender.sendMessage("Command not found");
                    break;
            }
        }
        return true;
    }
}
