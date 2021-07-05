package itb.lowhp;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("lowhp")) {
            if (args[0].equalsIgnoreCase("setlifes") && sender.hasPermission("lowhp.admin") && args.length == 3) {
                try {
                    Player target = sender.getServer().getPlayer(args[1]);
                    String name = target.getName();
                    int lifes = Integer.parseInt(args[2]) - Integer.parseInt(LowHP.playersLife.get(name));
                    LowHP.playersLife.replace(target.getName(), args[2]);
                    LowHP.SetName(target);
                    Objects.requireNonNull(target.getPlayer()).sendMessage(ChatColor.GRAY + "Administrators added " + lifes + " lives to you");
                    try {
                        LowHP.writeList("players");
                    } catch (IOException var7) {
                        var7.printStackTrace();
                    }
                    target.setMaxHealth(2.0);
                    sender.sendMessage("Complete!");
                    return true;
                } catch (NullPointerException e) {
                    if (!LowHP.playersLife.containsKey(args[1])) {
                        sender.sendMessage("Персонажа не существует!");
                        return true;
                    } else {
                        LowHP.playersLife.replace(args[1], args[2]);
                        try {
                            LowHP.writeList("players");
                        } catch (IOException var7) {
                            var7.printStackTrace();
                        }
                    }
                    sender.sendMessage("Complete!");
                }
            } else if (args[0].equalsIgnoreCase("setlifes") && sender.hasPermission("lowhp.admin") && args.length != 3) {
                sender.sendMessage("Неверный формат команды");
            } else if (args[0].equalsIgnoreCase("setlifes") && !sender.hasPermission("lowhp.admin")) {
                sender.sendMessage("You don't have Permission");
            } else if (args[0].equalsIgnoreCase("setadv") && sender.hasPermission("lowhp.admin") && args.length == 3) {
                try {
                    Player target = sender.getServer().getPlayer(args[1]);
                    String name = target.getName();
                    LowHP.advPlayers.replace(target.getName(), args[2]);
                    LowHP.SetName(target);
                    try {
                        LowHP.writeList("advancement");
                    } catch (IOException var7) {
                        var7.printStackTrace();
                    }
                    sender.sendMessage("Complete!");
                } catch (NullPointerException e) {
                    if (!LowHP.advPlayers.containsKey(args[1])) {
                        sender.sendMessage("Персонажа не существует!");
                        return true;
                    } else {
                        LowHP.advPlayers.replace(args[1], args[2]);
                        try {
                            LowHP.writeList("advancement");
                        } catch (IOException var7) {
                            var7.printStackTrace();
                        }
                    }
                    sender.sendMessage("Complete!");
                }
            } else if (args[0].equalsIgnoreCase("setadv") && sender.hasPermission("lowhp.admin") && args.length != 3) {
                sender.sendMessage("Неверный формат команды");
            } else if (args[0].equalsIgnoreCase("setadv") && !sender.hasPermission("lowhp.admin")) {
                sender.sendMessage("You don't have Permission");
            } else if (args[0].equalsIgnoreCase("outme") && sender.getServer().getPlayer(sender.getName()).getMaxHealth() < 20.0) {
                Player target;
                if (sender instanceof Player) {
                    target = (Player) sender;
                } else if (args.length > 1) {
                    target = sender.getServer().getPlayer(args[1]);
                } else {
                    sender.sendMessage("Неверный формат");
                    return true;
                }
                target.setMaxHealth(20.0);
                target.setHealth(20.0);
                LowHP.playersLife.replace(target.getName(), String.valueOf(0));
                LowHP.SetName(target);
                try {
                    LowHP.writeList("players");
                } catch (IOException var7) {
                    var7.printStackTrace();
                }

                sender.sendMessage("Complete!");
            } else if (args[0].equalsIgnoreCase("outme")) {
                sender.sendMessage("You already in this mode");
            }
            sender.sendMessage("Complete!");
            return true;
        } else return false;
    }
}
