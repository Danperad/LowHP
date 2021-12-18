package com.danperad.lowhpsetname;

import com.danperad.lowhplib.PlayerLow;
import com.danperad.lowhplib.db.DAO;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SetName {
    public static void SetName(Player player) {
        PlayerLow playerLow = DAO.getPlayer(player.getUniqueId().toString());
        String playerName = player.getName();
        int lifes = playerLow.getLifes();
        int adv = playerLow.getAdv();
        String twoname;
        if (player.getWorld().getName().equals("world")) {
            twoname = ChatColor.GREEN + playerName + " " + ChatColor.YELLOW + adv;
        } else if (player.getWorld().getName().equals("world_nether")) {
            twoname = ChatColor.RED + playerName + " " + ChatColor.YELLOW + adv;
        } else twoname = ChatColor.DARK_PURPLE + playerName + " " + ChatColor.YELLOW + adv;
        player.setPlayerListName(ChatColor.LIGHT_PURPLE + "[" + lifes + "] " + twoname);
    }
}
