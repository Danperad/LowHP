package com.danperad.lowhp;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import com.danperad.lowhp.commands.CommandsLowHP;
import com.danperad.lowhp.commands.CommandsLowHPAdmin;
import com.danperad.lowhp.commands.ConstructTabCompleterLowHP;
import com.danperad.lowhp.commands.ConstructTabCompleterLowHPAdmin;
import com.danperad.lowhp.events.PlayerDeathListener;
import com.danperad.lowhp.events.PlayerGetAdvListener;
import com.danperad.lowhp.events.PlayerJoinListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class LowHP extends JavaPlugin {
    private static FileConfiguration config;
    Logger log = this.getLogger();
    private static boolean isEnabled = false;
    public static FileConfiguration getConf() {
        return config;
    }

    public static boolean getPluginSetName(){
        return isEnabled;
    }

    /*protected static void SetName(Player player) {
        String playerr = player.getName();
        List<String> listt = playersList.get(playerr);
        int lifes = Integer.parseInt(listt.get(0));
        int adv = Integer.parseInt(listt.get(1));
        String twoname;
        if (player.getWorld().getName().equals("world")) {
            twoname = ChatColor.GREEN + playerr + " " + ChatColor.YELLOW + adv;
        } else if (player.getWorld().getName().equals("world_nether")) {
            twoname = ChatColor.RED + playerr + " " + ChatColor.YELLOW + adv;
        } else twoname = ChatColor.DARK_PURPLE + playerr + " " + ChatColor.YELLOW + adv;
        if (config.getBoolean("hardLife")) {
            if (lifes > 0 && !config.getBoolean("lifeAfterDeath"))
                player.setPlayerListName(ChatColor.RED + "[" + lifes + "] " + twoname);
            else
                player.setPlayerListName(ChatColor.LIGHT_PURPLE + "[" + -lifes + "] " + twoname);
        } else player.setPlayerListName(ChatColor.LIGHT_PURPLE + "[" + lifes + "] " + twoname);
    }*/

    private void CreateFile() {
        File dir = getDataFolder();
        dir.mkdir();
        File conf = new File("plugins/LowHP/config.yml");
        if (!conf.exists()) {
            this.getConfig().set("hardLife", true); // Жизни в минус
            this.getConfig().set("lifeAfterDeath", false); // Жизнь после смерти
            this.getConfig().set("hp", 2); // Количество здоровья до
            this.getConfig().set("hpAfter", 20); // Количество здоровья после
            this.getConfig().set("lifes", 9); // Количество жизней
            this.getConfig().set("advsForLife", 1); // Достижений для жизни ( >= 1 )
            try {
                conf.createNewFile();
                this.getConfig().save(conf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onEnable() {
        log.info("Start load...");
        CreateFile();
        config = this.getConfig();
        for (Plugin p : getServer().getPluginManager().getPlugins()) {
            if (p.getName().equalsIgnoreCase("lowhpsetname")) isEnabled = true;
        }
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
