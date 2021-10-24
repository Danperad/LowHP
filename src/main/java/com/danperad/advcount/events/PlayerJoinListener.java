package com.danperad.advcount.events;

import com.danperad.advcount.AdvCount;
import com.danperad.advcount.PlayerList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String playerName = e.getPlayer().getName();
        PlayerList p = AdvCount.getPlayersList();
        if (!p.hasPlayer(playerName)) {
            p.addPlayer(playerName);
        }
    }
}
