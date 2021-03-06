package com.danperad.lowhp.events;

import com.danperad.lowhp.LowHP;
import com.danperad.lowhpsetname.SetName;
import org.bukkit.Statistic;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    double hp = LowHP.getConf().getDouble("hp");
    double hpafter = LowHP.getConf().getDouble("hpAfter");

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e){
        int time = e.getPlayer().getStatistic(Statistic.LEAVE_GAME);
        double maxhp = e.getPlayer().getMaxHealth();
        if(time == 0 || (maxhp != hp && maxhp != hpafter)){
            e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
        }
        if(LowHP.isNameEnabled()) SetName.SetName(e.getPlayer());
    }
}
