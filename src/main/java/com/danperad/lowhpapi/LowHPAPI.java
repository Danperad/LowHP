package com.danperad.lowhpapi;

import com.danperad.lowhpapi.events.PlayerGetAdvListener;
import com.danperad.lowhpapi.events.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.logging.Logger;

public final class LowHPAPI extends JavaPlugin {
    static PlayerList playersList;
    Logger log = this.getLogger();

    public static PlayerList getPlayersList(){return playersList;}

    private Map<String, Integer> readList() throws IOException {
        InputStream inputStream = new FileInputStream("plugins/LowHPAPI/playerslist.yml");
        Yaml yaml = new Yaml();
        if (yaml.load(inputStream) == null) {
            inputStream.close();
            return null;
        }
        Map<String, Integer> now = yaml.load(inputStream);
        inputStream.close();
        return now;
    }

    private void CreateFile() throws IOException {
        File dir = getDataFolder();
        dir.mkdir();
        File players = new File("plugins/LowHPAPI/playerslist.yml");
        if (!players.exists()) {
            players.createNewFile();
            log.info("Creating PlayerList");
        }
    }

    @Override
    public void onEnable() {
        log.info("Start load...");
        try {
            CreateFile();
            Map<String, Integer> temp = readList();
            if (temp == null) {
                playersList = new PlayerList();
            }
            else playersList = new PlayerList(temp);
            log.info("Complete load PlayerList");
        } catch (IOException e) {
            e.printStackTrace();
            onDisable();
        }
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerGetAdvListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
