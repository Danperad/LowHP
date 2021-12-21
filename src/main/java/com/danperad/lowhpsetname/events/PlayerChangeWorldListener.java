package com.danperad.lowhpsetname.events;

import com.danperad.lowhpsetname.SetName;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangeWorldListener implements Listener {
    @EventHandler
    public void ChangeWorlds(PlayerChangedWorldEvent e){
        SetName.SetName(e.getPlayer());
    }
}
