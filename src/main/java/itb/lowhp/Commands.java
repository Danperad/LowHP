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
                    List<String> listt = LowHP.playersList.get(name);
                    // int lifes = Integer.parseInt(args[2]) - Integer.parseInt(LowHP.playersLife.get(target.getName()));
                    // LowHP.playersLife.replace(target.getName(), args[2]);
                    int lifes = Integer.parseInt(args[2]) - Integer.parseInt(LowHP.playersList.get(name).get(0));
                    listt.set(0,args[2]);
                    LowHP.playersList.replace(name, listt);
                    LowHP.SetName(target);
                    // target.setPlayerListName(ChatColor.RED + "[" + LowHP.playersLife.get(target.getName()) + "] " + ChatColor.WHITE + target.getName() + " " + ChatColor.YELLOW + LowHP.advPlayers.get(target.getName()));
                    Objects.requireNonNull(target.getPlayer()).sendMessage(ChatColor.GRAY + "Administrators added " + lifes + " lives to you");
                    try {
                        // LowHP.writeList("players");
                        LowHP.writeList();
                    } catch (IOException var7) {
                        var7.printStackTrace();
                    }
                    target.setMaxHealth(2.0);
                    sender.sendMessage("Complete!");
                    return true;
                } catch (NullPointerException e) {
                    if (!LowHP.playersList.containsKey(args[1])) {
                        sender.sendMessage("Персонажа не существует!");
                        return true;
                    } else {
                        List<String> listt = LowHP.playersList.get(args[1]);
                        listt.set(0,args[2]);
                        LowHP.playersList.replace(args[1], listt);
                        try {
                            // LowHP.writeList("players");
                            LowHP.writeList();
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
                    List<String> listt = LowHP.playersList.get(name);
                    listt.set(1,args[2]);
                    // LowHP.advPlayers.replace(target.getName(), args[2]);
                    LowHP.playersList.replace(target.getName(), listt);
                    LowHP.SetName(target);
                    //target.setPlayerListName(ChatColor.RED + "[" + LowHP.playersLife.get(target.getName()) + "] " + ChatColor.WHITE + target.getName() + " " + ChatColor.YELLOW + LowHP.advPlayers.get(target.getName()));
                    try {
                        //LowHP.writeList("advancement");
                        LowHP.writeList();
                    } catch (IOException var7) {
                        var7.printStackTrace();
                    }
                    sender.sendMessage("Complete!");
                } catch (NullPointerException e) {
                    if (!LowHP.playersList.containsKey(args[1])) {
                        sender.sendMessage("Персонажа не существует!");
                        return true;
                    } else {
                        List<String> listt = LowHP.playersList.get(args[1]);
                        listt.set(1,args[2]);
                        LowHP.playersList.replace(args[1], listt);
                        try {
                            // LowHP.writeList("advancement");
                            LowHP.writeList();
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
                List<String> listt = LowHP.playersList.get(target.getName());
                listt.set(0,"0");
                // LowHP.playersLife.replace(target.getName(), String.valueOf(0));
                LowHP.playersList.replace(target.getName(), listt);
                LowHP.SetName(target);
                try {
                    LowHP.writeList();
                    // LowHP.writeList("players");
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
