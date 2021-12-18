package com.danperad.lowhplib.listeners;

import com.danperad.lowhplib.PlayerLow;
import com.danperad.lowhplib.db.DAO;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void achivmentGet(PlayerDeathEvent e) {
        PlayerLow player = DAO.getPlayer(e.getEntity().getUniqueId().toString());
        player.incLifes();
        DAO.updatePlayer(player);
    }
}
