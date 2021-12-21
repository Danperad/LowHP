package com.danperad.lowhpsetname;

import java.io.File;
import java.util.logging.Logger;

import com.danperad.lowhpsetname.events.PlayerChangeWorldListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class LowHPSetName extends JavaPlugin {
    private static FileConfiguration config;

    private static boolean hpEnabled = false;
    Logger log = this.getLogger();
    public static FileConfiguration getConf() {
        return config;
    }

    public static boolean isHpEnabled() {
        return hpEnabled;
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
        if (this.getServer().getPluginManager().getPlugin("LowHP") != null) {
            hpEnabled = true;
        }
        CreateFile();
        config = this.getConfig();

        getServer().getPluginManager().registerEvents(new PlayerChangeWorldListener(), this);
        log.info("Load complete!");

    }

    @Override
    public void onDisable() {
        log.info("Oops :(");
    }
}
