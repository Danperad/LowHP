package itb.lowhp;

import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsLowHP implements CommandExecutor {
    boolean after = LowHP.getConf().getBoolean("lifeAfterDeath");
    boolean hardLife = LowHP.getConf().getBoolean("hardLife");
    double hpafter = Double.parseDouble(LowHP.getConf().getString("hpAfter"));

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("lowhp")) {
            if ("outme".equals(args[0])) {
                if (sender.getServer().getPlayer(sender.getName()).getMaxHealth() < hpafter && !hardLife && after) {
                    Player target;
                    if (sender instanceof Player) {
                        target = (Player) sender;
                    } else if (args.length > 1) {
                        target = sender.getServer().getPlayer(args[1]);
                    } else {
                        sender.sendMessage("Неверный формат");
                        return true;
                    }
                    target.setMaxHealth(hpafter);
                    target.setHealth(hpafter);
                    List<String> listt = LowHP.playersList.get(target.getName());
                    listt.set(0, "0");
                    LowHP.playersList.replace(target.getName(), listt);
                    LowHP.SetName(target);
                    try {
                        LowHP.writeList();
                    } catch (IOException var7) {
                        var7.printStackTrace();
                    }
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
