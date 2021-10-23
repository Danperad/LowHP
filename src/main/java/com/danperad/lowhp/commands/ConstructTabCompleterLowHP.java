package com.danperad.lowhp.commands;

import java.util.ArrayList;
import java.util.List;

import com.danperad.lowhp.LowHP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class ConstructTabCompleterLowHP implements TabCompleter {
    boolean hardLife = LowHP.getConf().getBoolean("hardLife");
    boolean after = LowHP.getConf().getBoolean("lifeAfterDeath");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) return null;
        if (command.getName().equalsIgnoreCase("lowhp"))
            if (args.length == 1) {
                List<String> list = new ArrayList<>();
                if (!hardLife && after) list.add("outme");
                return list;
            }
        return null;
    }
}
