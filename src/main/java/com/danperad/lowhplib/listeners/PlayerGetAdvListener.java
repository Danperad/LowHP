package com.danperad.lowhplib.listeners;

import com.danperad.lowhplib.PlayerLow;
import com.danperad.lowhplib.db.DAO;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class PlayerGetAdvListener implements Listener {
    @EventHandler
    public void achivmentGet(PlayerAdvancementDoneEvent e){
        String advName = e.getAdvancement().getKey().getKey();
        if (advName.startsWith("recipes/") || advName.startsWith("technical/")) return;
        PlayerLow player = DAO.getPlayer(e.getPlayer().getUniqueId().toString());
        player.incAdv();
        DAO.updatePlayer(player);
    }
}
