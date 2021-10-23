package com.danperad.lowhp.events;

import com.danperad.lowhp.LowHP;
import com.danperad.lowhpapi.LowHPAPI;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class PlayerGetAdvListener implements Listener {
    boolean hardLife = LowHP.getConf().getBoolean("hardLife");
    int lifes = LowHP.getConf().getInt("lifes");
    int advforlife = LowHP.getConf().getInt("advsForLife");
    @EventHandler
    public void achivmentGet(PlayerAdvancementDoneEvent e){
        String advName = e.getAdvancement().getKey().getKey();
        if (advName.startsWith("recipes/") || advName.startsWith("technical/")) return;
        String playerName = e.getPlayer().getName();
        int hp = e.getPlayer().getStatistic(Statistic.DEATHS);
        int adv = LowHPAPI.getAPI().getPlayerData(playerName);
        if ((advforlife > 0) && (adv % advforlife == 0) && hp > lifes && hardLife) {
            e.getPlayer().setStatistic(Statistic.DEATHS, hp-1);
            e.getPlayer().sendMessage(ChatColor.AQUA + "+1 Life for completing 50 Advancements");
        }
    }
}
