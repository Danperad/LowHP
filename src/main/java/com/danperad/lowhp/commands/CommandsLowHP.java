package com.danperad.lowhp.commands;

import com.danperad.lowhp.LowHP;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsLowHP implements CommandExecutor {
    boolean after = LowHP.getConf().getBoolean("lifeAfterDeath");
    boolean hardLife = LowHP.getConf().getBoolean("hardLife");
    double hpafter = LowHP.getConf().getDouble("hpAfter");

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("lowhp")) {
            if ("outme".equals(args[0])) {
                if (sender.getServer().getPlayer(sender.getName()).getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() < hpafter && !hardLife && after) {
                    Player target;
                    if (sender instanceof Player) {
                        target = (Player) sender;
                    } else if (args.length > 1) {
                        target = sender.getServer().getPlayer(args[1]);
                    } else {
                        sender.sendMessage("Неверный формат");
                        return true;
                    }
                    target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hpafter);
                    target.setHealth(hpafter);
                    sender.sendMessage("Complete!");
                } else {
                    sender.sendMessage("No No NOOO!!!");
                }
            } else {
                sender.sendMessage("Command not found");
            }
        }
        return true;
    }
}
