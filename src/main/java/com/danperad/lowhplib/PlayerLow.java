package com.danperad.lowhplib;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

public class PlayerLow {

    @Getter
    @Setter
    private String UUID;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int lifes;

    @Getter
    @Setter
    private int adv;

    public PlayerLow(Player player){
        UUID = player.getUniqueId().toString();
        name = player.getName();
        lifes = 0;
        adv = 0;
    }

    public PlayerLow() {

    }

    public void incLifes(){
        lifes++;
    }
    public void incAdv(){
        adv++;
    }
}
