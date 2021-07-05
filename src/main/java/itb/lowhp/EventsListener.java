package itb.lowhp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventsListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException {
        if (!LowHP.playersList.containsKey(e.getPlayer().getName())) {
            List<String> listt = new ArrayList<>();
            listt.add("9");
            listt.add("-1");
            LowHP.playersList.put(e.getPlayer().getName(), listt);
            // LowHP.playersLife.put(e.getPlayer().getName(), String.valueOf(9));
            // LowHP.advPlayers.put(e.getPlayer().getName(), "-1");
            e.getPlayer().setMaxHealth(2.0);
            // LowHP.writeList("players");
            // LowHP.writeList("advancement");
            LowHP.writeList();
        }
        LowHP.SetName(e.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) throws IOException {
        String playerr = e.getEntity().getName();
        List<String> listt = LowHP.playersList.get(playerr);
        int hp = Integer.parseInt(LowHP.playersList.get(playerr).get(0));
        listt.set(0,String.valueOf(hp-1));
        LowHP.playersList.replace(playerr, listt);
        LowHP.writeList();
        // int hp = Integer.parseInt(LowHP.playersLife.get(playerr));
        // LowHP.playersLife.replace(playerr, String.valueOf(hp - 1));
        // LowHP.writeList("players");
        if (hp < 1) {
            e.getEntity().setMaxHealth(20.0);
        }
        LowHP.SetName(e.getEntity());
    }

    @EventHandler
    public void achivmentGet(PlayerAdvancementDoneEvent e) throws IOException {
        String advName = e.getAdvancement().getKey().getKey();
        if (advName.startsWith("recipes/") || advName.startsWith("technical/")) return;
        String playerr = e.getPlayer().getName();
        int adv = Integer.parseInt(LowHP.playersList.get(playerr).get(1))+1;
        List<String> listt = LowHP.playersList.get(playerr);
        listt.set(1,String.valueOf(adv));
        //int adv = Integer.parseInt(LowHP.advPlayers.get(playerr))+1;
        //LowHP.advPlayers.replace(playerr, String.valueOf(adv));
        //LowHP.writeList("advancement");
        if (adv % 50 == 0 && adv > 0 && Integer.parseInt(LowHP.playersList.get(playerr).get(0)) > 0) {
            int hp = Integer.parseInt(LowHP.playersList.get(playerr).get(0));
            listt.set(0,String.valueOf(hp+1));
            //LowHP.playersLife.replace(playerr, String.valueOf(hp + 1));
            //LowHP.writeList("players");
            e.getPlayer().sendMessage(ChatColor.AQUA + "+1 Life for completing 50 Advancements");
        }
        LowHP.playersList.replace(playerr, listt);
        LowHP.writeList();
        LowHP.SetName(e.getPlayer());
    }
}
