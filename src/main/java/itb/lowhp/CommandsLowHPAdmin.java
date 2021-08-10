package itb.lowhp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public class CommandsLowHPAdmin implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("lowhpadm") && sender.hasPermission("lowhp.admin")) {
            switch (args[0]) {
                case "setlifes":
                    if (args.length == 3) {
                        try {
                            Player target = sender.getServer().getPlayer(args[1]);
                            String name = target.getName();
                            List<String> listt = LowHP.playersList.get(name);
                            int lifes = Integer.parseInt(args[2]) - Integer.parseInt(LowHP.playersList.get(name).get(0));
                            listt.set(0, args[2]);
                            LowHP.playersList.replace(name, listt);
                            LowHP.SetName(target);
                            target.getPlayer().sendMessage(ChatColor.GRAY + "Administrators added " + lifes + " lives to you");
                            try {
                                LowHP.writeList();
                            } catch (IOException var7) {
                                var7.printStackTrace();
                            }
                            if (Integer.parseInt(args[2]) > 0) target.setMaxHealth(2.0);
                            sender.sendMessage("Complete!");
                            return true;
                        } catch (NullPointerException e) {
                            if (!LowHP.playersList.containsKey(args[1])) {
                                sender.sendMessage("Персонажа не существует!");
                                return true;
                            } else {
                                List<String> listt = LowHP.playersList.get(args[1]);
                                listt.set(0, args[2]);
                                LowHP.playersList.replace(args[1], listt);
                                try {
                                    LowHP.writeList();
                                } catch (IOException var7) {
                                    var7.printStackTrace();
                                }
                            }
                            sender.sendMessage("Complete!");
                        }
                    } else {
                        sender.sendMessage("Неверный формат команды");
                    }
                    break;
                case "setadv":
                    if (args.length == 3) {
                        try {
                            Player target = sender.getServer().getPlayer(args[1]);
                            String name = target.getName();
                            List<String> listt = LowHP.playersList.get(name);
                            listt.set(1, args[2]);
                            LowHP.playersList.replace(target.getName(), listt);
                            LowHP.SetName(target);
                            try {
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
                                listt.set(1, args[2]);
                                LowHP.playersList.replace(args[1], listt);
                                try {
                                    LowHP.writeList();
                                } catch (IOException var7) {
                                    var7.printStackTrace();
                                }
                            }
                            sender.sendMessage("Complete!");
                        }
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
