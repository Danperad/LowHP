package com.danperad.lowhplib;

import com.danperad.lowhplib.db.DAO;
import com.danperad.lowhplib.events.PlayerGetAdvListener;
import com.danperad.lowhplib.events.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class LowHPLib extends JavaPlugin {
    Logger log = this.getLogger();

    @Override
    public void onEnable() {
        log.info("Start load...");
        DAO.init();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerGetAdvListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
