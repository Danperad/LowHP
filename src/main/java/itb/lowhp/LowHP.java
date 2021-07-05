package itb.lowhp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

public final class LowHP extends JavaPlugin {
    /*
    public static Map<String, String> playersLife;
    public static Map<String, String> advPlayers;
    */
    public static Map<String, List<String>> playersList;
/*
    protected Map<String, String> readList(String name) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("plugins/LowHP/" + name + ".yml"));
        Yaml yaml = new Yaml();
        return yaml.load(inputStream);
    }

    protected static void writeList(String name) throws IOException {
        PrintWriter writer = new PrintWriter(new File("plugins/LowHP/" + name + ".yml"));
        Yaml yaml = new Yaml();
        if (name.equals("players")) yaml.dump(playersLife, writer);
        else yaml.dump(advPlayers, writer);
    }
 */

    protected Map<String, List<String>> readList() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("plugins/LowHP/playerslist.yml"));
        Yaml yaml = new Yaml();
        return yaml.load(inputStream);
    }

    protected static void writeList() throws IOException {
        PrintWriter writer = new PrintWriter(new File("plugins/LowHP/playerslist.yml"));
        Yaml yaml = new Yaml();
        yaml.dump(playersList, writer);
    }


    protected static void SetName(Player player) {
        String playerr = player.getName();
        // int lifes = Integer.parseInt(LowHP.playersLife.get(playerr));
        int lifes = Integer.parseInt(LowHP.playersList.get(playerr).get(0));
        if (lifes > 0)
            // player.setPlayerListName(ChatColor.RED + "[" + LowHP.playersLife.get(playerr) + "] " + ChatColor.WHITE + playerr + " " + ChatColor.YELLOW + LowHP.advPlayers.get(playerr));
            player.setPlayerListName(ChatColor.RED + "[" + lifes + "] " + ChatColor.WHITE + playerr + " " + ChatColor.YELLOW + LowHP.playersList.get(playerr).get(1));
        else
            player.setPlayerListName(ChatColor.DARK_PURPLE + "[" + -lifes + "] " + ChatColor.WHITE + playerr + " " + ChatColor.YELLOW + LowHP.playersList.get(playerr).get(1));

    }

    @Override
    public void onEnable() {
        Logger log = this.getLogger();
        log.info("Start load...");
        File dir = new File("plugins/LowHP");
        dir.mkdir();
        /*
        File playersLists = new File("plugins/LowHP", "players.yml");
        if (!playersLists.exists()) {
            try {
                playersLists.createNewFile();
                Map<String, String> mapp = new HashMap();
                mapp.put("Papaaaaa", "9");
                PrintWriter writer = new PrintWriter("plugins/LowHP/players.yml");
                Yaml yaml = new Yaml();
                yaml.dump(mapp, writer);
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

        File advLists = new File("plugins/LowHP", "advancement.yml");
        if (!advLists.exists()) {
            try {
                advLists.createNewFile();
                Map<String, String> mapp = new HashMap();
                mapp.put("Papaaaaa", "0");
                PrintWriter writer = new PrintWriter("plugins/LowHP/advancement.yml");
                Yaml yaml = new Yaml();
                yaml.dump(mapp, writer);
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

         */
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
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

        try {
            /*
            playersLife = this.readList("players");
            advPlayers = this.readList("advancement");
             */
            playersList = readList();
        } catch (FileNotFoundException var7) {
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
