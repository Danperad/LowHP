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
    boolean after = LowHP.getConf().getBoolean("lifeAfterDeath");
    boolean hardLife = LowHP.getConf().getBoolean("hardLife");
    String lifes = LowHP.getConf().getString("lifes");
    boolean bac = LowHP.getConf().getBoolean("bac");
    double hp = Double.parseDouble(LowHP.getConf().getString("hp"));
    double hpafter = Double.parseDouble(LowHP.getConf().getString("hpAfter"));
    boolean advtolife = LowHP.getConf().getBoolean("advToLife");
    String advforlife = LowHP.getConf().getString("advsForLife");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException {
        if (!LowHP.playersList.containsKey(e.getPlayer().getName())) {
            List<String> listt = new ArrayList<>();
            if (hardLife) listt.add(lifes);
            else listt.add("0");
            if (bac) listt.add("-1");
            else listt.add("0");
            LowHP.playersList.put(e.getPlayer().getName(), listt);
            e.getPlayer().setMaxHealth(hp);
            LowHP.writeList();
        }
        LowHP.SetName(e.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) throws IOException {
        String playerr = e.getEntity().getName();
        List<String> listt = LowHP.playersList.get(playerr);
        int hp;
        if (hardLife) {
            hp = Integer.parseInt(LowHP.playersList.get(playerr).get(0)) - 1;
            if (hp < 1 && after) {
                e.getEntity().setMaxHealth(hpafter);
            }
        }
        else {
            hp = Integer.parseInt(LowHP.playersList.get(playerr).get(0)) + 1;
        }
        listt.set(0, String.valueOf(hp));
        LowHP.playersList.replace(playerr, listt);
        LowHP.writeList();
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
        if (advtolife && adv % Integer.parseInt(advforlife) == 0 && adv > 0 && Integer.parseInt(LowHP.playersList.get(playerr).get(0)) > 0) {
            int hp = Integer.parseInt(LowHP.playersList.get(playerr).get(0));
            listt.set(0,String.valueOf(hp+1));
            e.getPlayer().sendMessage(ChatColor.AQUA + "+1 Life for completing 50 Advancements");
        }
        LowHP.playersList.replace(playerr, listt);
        LowHP.writeList();
        LowHP.SetName(e.getPlayer());
    }
}
