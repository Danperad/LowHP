package com.danperad.lowhplib;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class PlayerLow {

    @Id
    @Getter
    @Column(name = "UUID")
    private String UUID;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "lifes")
    private int lifes;

    @Getter
    @Setter
    @Column(name = "adv")
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
