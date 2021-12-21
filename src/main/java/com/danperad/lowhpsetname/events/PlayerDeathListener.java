package com.danperad.lowhpsetname.events;

import com.danperad.lowhpsetname.SetName;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void ChangeWorlds(PlayerDeathEvent e){
        SetName.SetName(e.getEntity());
    }
}
