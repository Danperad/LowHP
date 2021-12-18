package com.danperad.lowhpsetname;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class ChangeWorld implements Listener {
    @EventHandler
    public void ChangeWorlds(PlayerChangedWorldEvent e){
        SetName.SetName(e.getPlayer());
    }
}
