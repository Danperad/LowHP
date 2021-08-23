package itb.lowhp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

public final class LowHP extends JavaPlugin {
    protected static Map<String, List<String>> playersList;
    private static FileConfiguration config;
    Logger log = this.getLogger();

    private Map<String, List<String>> readList() throws IOException {
        InputStream inputStream = new FileInputStream(new File("plugins/LowHP/playerslist.yml"));
        Yaml yaml = new Yaml();
        if (yaml.load(inputStream) == null){
            return null;
        }
        Map<String, List<String>> now = yaml.load(inputStream);
        inputStream.close();
        return now;
    }

    protected static void writeList() throws IOException {
        PrintWriter writer = new PrintWriter(new File("plugins/LowHP/playerslist.yml"));
        Yaml yaml = new Yaml();
        yaml.dump(playersList, writer);
        writer.close();
    }

    public static FileConfiguration getConf() {
        return config;
    }

    protected static void SetName(Player player) {
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
    }

    private void CreateFile() {
        File dir = getDataFolder();
        dir.mkdir();
        File players = new File("plugins/LowHP/playerslist.yml");
        if (!players.exists()) {
            try {
                players.createNewFile();
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }
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
        if (!config.getBoolean("hardlife") && config.getBoolean("lifeAfterDeath")) {
            config.set("lifeAfterDeath", false);
            log.info("Error advsForLife");
            onDisable();
        }
        try {
            playersList = readList();
            if (playersList == null){
                playersList = new HashMap<>();
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new EventsListener(), this);
        getCommand("LowHP").setExecutor(new CommandsLowHP());
        getCommand("LowHPAdm").setExecutor(new CommandsLowHPAdmin());
        getCommand("LowHP").setTabCompleter(new ConstructTabCompleterLowHP());
        getCommand("LowHPAdm").setTabCompleter(new ConstructTabCompleterLowHPAdmin());
        log.info("Let the game begin!");

    }

    @Override
    public void onDisable() {
        try {
            writeList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Oops :(");
    }
}
