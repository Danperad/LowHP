package com.danperad.lowhpsetname;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class LowHPSetName extends JavaPlugin {
    private static FileConfiguration config;
    Logger log = this.getLogger();
    public static FileConfiguration getConf() {
        return config;
    }

    private void CreateFile() {
        File dir = getDataFolder();
        dir.mkdir();
    }

    @Override
    public void onEnable() {
        log.info("Start load...");
        if (this.getServer().getPluginManager().getPlugin("LowHPLib") == null) {
            log.info("LowHPLib dont installed");
            return;
        }
        CreateFile();
        config = this.getConfig();

        getServer().getPluginManager().registerEvents(new ChangeWorld(), this);
        log.info("Load complete!");

    }

    @Override
    public void onDisable() {
        log.info("Oops :(");
    }
}
