package com.danperad.advcount;

import com.danperad.advcount.exceptions.PlayerNotExist;

public class API {
    private final PlayerList playerList;
    public API(PlayerList playerList){
        this.playerList = playerList;
    }
    public boolean hasPlayer(String name){
        return playerList.hasPlayer(name);
    }
    public int getPlayerData(String name){
        try {
            return this.playerList.getPlayerData(name);
        } catch (PlayerNotExist e) {
            return -1;
        }
    }
    public boolean replacePlayer(String name, Integer val){
        return this.playerList.replacePlayer(name, val);
    }
}
