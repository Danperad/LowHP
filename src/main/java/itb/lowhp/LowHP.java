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
    public static Map<String, List<String>> playersList;

    protected Map<String, List<String>> readList() throws IOException {
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


    protected static void SetName(Player player) {
        String playerr = player.getName();
        int lifes = Integer.parseInt(LowHP.playersList.get(playerr).get(0));
        if (lifes > 0)
            player.setPlayerListName(ChatColor.RED + "[" + lifes + "] " + ChatColor.WHITE + playerr + " " + ChatColor.YELLOW + LowHP.playersList.get(playerr).get(1));
        else
            player.setPlayerListName(ChatColor.DARK_PURPLE + "[" + -lifes + "] " + ChatColor.WHITE + playerr + " " + ChatColor.YELLOW + LowHP.playersList.get(playerr).get(1));

    }
    private void CreateFile(){
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
    }

    private Map<String, String> readPrevList(String name) throws IOException {
        InputStream inputStream = new FileInputStream(new File("plugins/LowHP/" + name + ".yml"));
        Yaml yaml = new Yaml();
        Map<String, String> now = yaml.load(inputStream);
        inputStream.close();
        return now;
    }

    private void CheckPrevFiles(){
        File playersLists = new File("plugins/LowHP", "players.yml");
        File advLists = new File("plugins/LowHP", "advancement.yml");
        if (playersLists.exists() && advLists.exists()) {
            try {
                Map<String, List<String>> newList = new HashMap<>();
                Map<String, String> playersLife = readPrevList("players");
                Map<String, String> advPlayers = readPrevList("advancement");
                for (String key : playersLife.keySet()){
                    List<String> listt = new ArrayList<>();
                    listt.add(playersLife.get(key));
                    listt.add(advPlayers.get(key));
                    newList.put(key,listt);
                }
                PrintWriter writer = new PrintWriter("plugins/LowHP/playerslist.yml");
                Yaml yaml = new Yaml();
                yaml.dump(newList, writer);
                writer.close();
            } catch (IOException var8) {
                var8.printStackTrace();
            }
            playersLists.delete();
            advLists.delete();
        }
    }


    @Override
    public void onEnable() {
        Logger log = this.getLogger();
        log.info("Start load...");

        CheckPrevFiles();
        CreateFile();
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
