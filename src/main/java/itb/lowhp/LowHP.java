package itb.lowhp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

public final class LowHP extends JavaPlugin {
    public static Map<String, List<String>> playersList;
    private static FileConfiguration config;

    private Map<String, List<String>> readList() throws IOException {
        InputStream inputStream = new FileInputStream(new File("plugins/LowHP/playerslist.yml"));
        Yaml yaml = new Yaml();
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

    public static FileConfiguration getConf(){
        return config;
    }

    protected static void SetName(Player player) {
        String playerr = player.getName();
        int lifes = Integer.parseInt(LowHP.playersList.get(playerr).get(0));
        if (lifes > 0)
            player.setPlayerListName(ChatColor.RED + "[" + lifes + "] " + ChatColor.WHITE + playerr + " " + ChatColor.YELLOW + LowHP.playersList.get(playerr).get(1));
        else
            player.setPlayerListName(ChatColor.DARK_PURPLE + "[" + -lifes + "] " + ChatColor.WHITE + playerr + " " + ChatColor.YELLOW + LowHP.playersList.get(playerr).get(1));

    }

    private void CreateFile() {
        File dir = new File("plugins/LowHP");
        dir.mkdir();
        File players = new File("plugins/LowHP", "playerslist.yml");
        if (!players.exists()) {
            try {
                players.createNewFile();
                List<String> listt = new ArrayList<>();
                listt.add("9");
                listt.add("-1");
                Map<String, List<String>> mapp = new HashMap();
                mapp.put("Papaaaaa", listt);
                PrintWriter writer = new PrintWriter("plugins/LowHP/playerslist.yml");
                Yaml yaml = new Yaml();
                yaml.dump(mapp, writer);
                writer.close();
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }
        File conf = new File("plugins/LowHP", "config.yml");
        if (!conf.exists()) {
            this.getConfig().set("hardLife", true); // Жизни в минус
            this.getConfig().set("lifeAfterDeath", false); // Жизнь после смерти
            this.getConfig().set("hp", 2); // Количество здоровья до
            this.getConfig().set("hpAfter", 20); // Количество здоровья после
            this.getConfig().set("bac", false); // Blaze and Caves
            this.getConfig().set("lifes", 9); // Количество жизней
            this.getConfig().set("advToLife", false); // Достижения за жизнь
            this.getConfig().set("advsForLife", -1); // Достижений для жизни
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
        Logger log = this.getLogger();
        log.info("Start load...");
        CreateFile();
        config = this.getConfig();
        try {
            playersList = readList();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        Commands handler = new Commands();
        TabCompleter completer = new ConstructTabCompleter();
        getServer().getPluginManager().registerEvents(new EventsListener(), this);
        getCommand("LowHP").setExecutor(handler);
        getCommand("LowHP").setTabCompleter(completer);
        log.info("Let the game begin!");

    }

    @Override
    public void onDisable() {
        Logger log = this.getLogger();
        log.info("Oops :(");
    }
}
