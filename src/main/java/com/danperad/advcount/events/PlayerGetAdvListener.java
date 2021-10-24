package com.danperad.advcount.events;

import com.danperad.advcount.AdvCount;
import com.danperad.advcount.PlayerList;
import com.danperad.advcount.exceptions.PlayerNotExist;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class PlayerGetAdvListener implements Listener {
    @EventHandler
    public void achivmentGet(PlayerAdvancementDoneEvent e){
        String advName = e.getAdvancement().getKey().getKey();
        if (advName.startsWith("recipes/") || advName.startsWith("technical/")) return;
        String playerName = e.getPlayer().getName();
        PlayerList p = AdvCount.getPlayersList();
        try {
            int adv = p.getPlayerData(playerName) +1;
            p.replacePlayer(playerName, adv);
        } catch (PlayerNotExist ex) {
            ex.printStackTrace();
        }
    }
}
