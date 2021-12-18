package com.danperad.lowhp.events;

import com.danperad.lowhp.LowHP;
import com.danperad.lowhplib.PlayerLow;
import com.danperad.lowhplib.db.DAO;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class PlayerGetAdvListener implements Listener {
    boolean hardLife = LowHP.getConf().getBoolean("hardLife");
    int lifes = LowHP.getConf().getInt("lifes");
    int advforlife = LowHP.getConf().getInt("advsForLife");

    @EventHandler(priority = EventPriority.LOW)
    public void achivmentGet(PlayerAdvancementDoneEvent e){
        String advName = e.getAdvancement().getKey().getKey();
        if (advName.startsWith("recipes/") || advName.startsWith("technical/")) return;
        PlayerLow playerLow = DAO.getPlayer(e.getPlayer().getUniqueId().toString());
        if ((advforlife > 0) && (playerLow.getAdv() % advforlife == 0) && playerLow.getLifes() > lifes && hardLife) {
            playerLow.setLifes(playerLow.getLifes()-1);
            DAO.updatePlayer(playerLow);
            e.getPlayer().sendMessage(ChatColor.AQUA + "+1 Life for completing 50 Advancements");
        }
    }
}
