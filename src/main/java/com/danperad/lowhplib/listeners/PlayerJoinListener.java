package com.danperad.lowhplib.listeners;

import com.danperad.lowhplib.PlayerLow;
import com.danperad.lowhplib.db.DAO;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (DAO.getPlayer(e.getPlayer().getUniqueId().toString()) == null) {
            PlayerLow playerLow = new PlayerLow(e.getPlayer());
            DAO.insertPlayer(playerLow);
        }
    }
}
