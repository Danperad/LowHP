package com.danperad.lowhpapi;

import com.danperad.lowhpapi.exceptions.PlayerNotExist;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public final class PlayerList {
    private final Map<String, Integer> playerList;

    public PlayerList() {
        this.playerList = new HashMap<>();
    }

    public PlayerList(Map<String, Integer> map) {
        this.playerList = map;
    }

    public boolean hasPlayer(String name) {
        return this.playerList.containsKey(name);
    }

    public void addPlayer(String name) {
        this.playerList.put(name, 0);
        try {
            writeList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean replacePlayer(String name, Integer val) {
        if (!this.playerList.containsKey(name)) return false;
        this.playerList.replace(name, val);
        try {
            writeList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int getPlayerData(String name) throws PlayerNotExist {
        if(!hasPlayer(name)) throw new PlayerNotExist(name);
        return this.playerList.get(name);
    }

    private void writeList() throws IOException {
        PrintWriter writer = new PrintWriter("plugins/LowHPAPI/playerslist.yml");
        Yaml yaml = new Yaml();
        yaml.dump(this.playerList, writer);
        writer.close();
    }
}
