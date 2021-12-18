package com.danperad.lowhp.events;

import com.danperad.lowhp.LowHP;
import com.danperad.lowhplib.PlayerLow;
import com.danperad.lowhplib.db.DAO;
import com.danperad.lowhpsetname.SetName;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    boolean after = LowHP.getConf().getBoolean("lifeAfterDeath");
    boolean hardLife = LowHP.getConf().getBoolean("hardLife");
    int lifes = LowHP.getConf().getInt("lifes");
    double hpafter = LowHP.getConf().getDouble("hpAfter");

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        if(hardLife) {
            PlayerLow playerLow = DAO.getPlayer(e.getEntity().getUniqueId().toString());
            if (playerLow.getLifes() > lifes && after) {
                e.getEntity().setMaxHealth(hpafter);
            } else
                e.getEntity().setGameMode(GameMode.SPECTATOR);
        }
        if(LowHP.isNameEnabled()) SetName.SetName(e.getEntity());
    }
}
