package com.danperad.lowhp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConstructTabCompleterLowHPAdmin implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) return null;
        if (command.getName().equalsIgnoreCase("lowhpadm"))
            if (args.length == 1) {
                List<String> list = new ArrayList<>();
                list.add("setlifes");
                list.add("setadv");
                return list;
            } else if (args.length == 2 && (args[0].equalsIgnoreCase("setlifes") || args[0].equalsIgnoreCase("setadv"))) {
                List<String> list = new ArrayList<>();
                for (Player p : sender.getServer().getOnlinePlayers()) {
                    list.add(p.getName());
                }
                return list;
            }
        return null;
    }
}
