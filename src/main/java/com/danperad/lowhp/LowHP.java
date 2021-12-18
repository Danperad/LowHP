package com.danperad.lowhp;

import java.io.File;
import java.util.logging.Logger;

import com.danperad.lowhp.commands.CommandsLowHP;
import com.danperad.lowhp.commands.CommandsLowHPAdmin;
import com.danperad.lowhp.commands.ConstructTabCompleterLowHP;
import com.danperad.lowhp.commands.ConstructTabCompleterLowHPAdmin;
import com.danperad.lowhp.events.PlayerDeathListener;
import com.danperad.lowhp.events.PlayerGetAdvListener;
import com.danperad.lowhp.events.PlayerJoinListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class LowHP extends JavaPlugin {
    private static FileConfiguration config;
    Logger log = this.getLogger();

    public static boolean isNameEnabled() {
        return nameEnabled;
    }

    private static boolean nameEnabled = false;

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

        if (this.getServer().getPluginManager().getPlugin("LowHPSetName") != null) {
            nameEnabled = true;
        }
        CreateFile();
        config = this.getConfig();

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerGetAdvListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getCommand("LowHP").setExecutor(new CommandsLowHP());
        getCommand("LowHPAdm").setExecutor(new CommandsLowHPAdmin());
        getCommand("LowHP").setTabCompleter(new ConstructTabCompleterLowHP());
        getCommand("LowHPAdm").setTabCompleter(new ConstructTabCompleterLowHPAdmin());
        log.info("Let the game begin!");

    }

    @Override
    public void onDisable() {
        log.info("Oops :(");
    }
}
