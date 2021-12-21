package com.danperad.lowhp.commands;

import com.danperad.lowhp.LowHP;
import com.danperad.lowhplib.PlayerLow;
import com.danperad.lowhplib.db.DAO;
import com.danperad.lowhpsetname.SetName;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


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
                            PlayerLow playerLow = DAO.getPlayer(target.getUniqueId().toString());
                            int lifes = Integer.parseInt(args[2]) - playerLow.getLifes();
                            playerLow.setLifes(Integer.parseInt(args[2]));
                            DAO.updatePlayer(playerLow);
                            if(LowHP.isNameEnabled()) SetName.SetName(target);
                            target.getPlayer().sendMessage(ChatColor.GRAY + "Administrators added " + lifes + " lives to you");
                            if (playerLow.getLifes() > 0 && hardLife) target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
                            else if (playerLow.getLifes() <= 0 && hardLife && after) {
                                target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hpafter);
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
                        Player target = sender.getServer().getPlayer(args[1]);
                        assert target != null;
                        PlayerLow playerLow = DAO.getPlayer(target.getUniqueId().toString());
                        playerLow.setAdv(Integer.valueOf(args[2]));
                        DAO.updatePlayer(playerLow);
                        SetName.SetName(target);
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
