package com.danperad.lowhpapi.exceptions;

public class PlayerNotExist extends Exception{
    private final String message;
    public PlayerNotExist(String playerName){
        this.message = "Player \""+playerName+"\" does not exist";
    }

    @Override
    public String getMessage(){
        return message;
    }
}
