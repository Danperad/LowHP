package com.danperad.lowhplib.events;

import com.danperad.lowhplib.PlayerLow;
import com.danperad.lowhplib.db.DAO;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        PlayerLow playerLow = new PlayerLow(e.getPlayer());
        DAO.insertPlayer(playerLow);
    }
}
