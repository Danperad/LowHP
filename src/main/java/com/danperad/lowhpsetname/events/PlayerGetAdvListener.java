package com.danperad.lowhpsetname.events;

import com.danperad.lowhpsetname.SetName;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class PlayerGetAdvListener implements Listener {
    @EventHandler
    public void achivmentGet(PlayerAdvancementDoneEvent e) {
        String advName = e.getAdvancement().getKey().getKey();
        if (!advName.startsWith("recipes/") && !advName.startsWith("technical/")) {
            SetName.SetName(e.getPlayer());
        }
    }
}
