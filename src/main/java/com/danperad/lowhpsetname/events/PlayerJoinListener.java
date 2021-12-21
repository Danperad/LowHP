package com.danperad.lowhpsetname.events;

import com.danperad.lowhpsetname.SetName;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        SetName.SetName(e.getPlayer());
    }
}
