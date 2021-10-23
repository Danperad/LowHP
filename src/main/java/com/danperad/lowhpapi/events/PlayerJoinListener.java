package com.danperad.lowhpapi.events;

import com.danperad.lowhpapi.LowHPAPI;
import com.danperad.lowhpapi.PlayerList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String playerName = e.getPlayer().getName();
        PlayerList p = LowHPAPI.getPlayersList();
        if (!p.hasPlayer(playerName)) {
            p.addPlayer(playerName);
        }
    }
}
