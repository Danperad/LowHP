package itb.lowhp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class ConstructTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) return null;
        if (command.getName().equalsIgnoreCase("lowhp") && args.length == 1) {
            List<String> list = new ArrayList<>();
            if (!LowHP.getConf().getBoolean("hardLife") && LowHP.getConf().getBoolean("lifeAfterDeath")) list.add("outme");
            if (sender.hasPermission("lowhp.admin")) {
                list.add("setlifes");
                list.add("setadv");
            }
            return list;
        } else if (command.getName().equalsIgnoreCase("lowhp") && args.length == 2 && (args[0].equalsIgnoreCase("setlifes") || args[0].equalsIgnoreCase("setadv")) && sender.hasPermission("lowhp.admin")) {
            List<String> list = new ArrayList<>();
            for (Player p : sender.getServer().getOnlinePlayers()) {
                list.add(p.getName());
            }
            return list;
        }
        return null;
    }
}
