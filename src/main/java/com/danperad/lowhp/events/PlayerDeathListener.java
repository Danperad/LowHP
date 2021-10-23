package com.danperad.lowhp.events;

import com.danperad.lowhp.LowHP;
import org.bukkit.GameMode;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    boolean after = LowHP.getConf().getBoolean("lifeAfterDeath");
    boolean hardLife = LowHP.getConf().getBoolean("hardLife");
    int lifes = LowHP.getConf().getInt("lifes");
    double hpafter = LowHP.getConf().getDouble("hpAfter");

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        int hp = e.getEntity().getStatistic(Statistic.DEATHS);
        if(hardLife) {
            if (hp > lifes && after) {
                e.getEntity().setMaxHealth(hpafter);
            } else
                e.getEntity().setGameMode(GameMode.SPECTATOR);
        }
    }
}
