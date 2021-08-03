package ml.zdoctor.listeners;

import ml.zdoctor.API.AmbulanceCallEvent;
import ml.zdoctor.API.PoliceCallEvent;
import ml.zdoctor.utils.Features;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static ml.zdoctor.API.API.*;

public class EmergencyCalls implements Listener {

    @EventHandler
    public void onPoliceCall(PoliceCallEvent e) {
        if (Features.isEnabled(Features.Feature.POLICE)) {
            Player player = e.getPlayer();

            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();

            Bukkit.broadcast(getConfigMessage("police.receive-message").replace("{x}", String.valueOf(x)).replace("{y}", String.valueOf(y)).replace("{z}", String.valueOf(z)), getSettingString("police.receive-permission"));
            e.getPlayer().sendMessage(getConfigMessage("police.called"));
        }

    }

    @EventHandler
    public void onAmbulanceCall(AmbulanceCallEvent e) {
        if (Features.isEnabled(Features.Feature.AMBULANCE)) {
            Player player = e.getPlayer();

            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();

            Bukkit.broadcast(getConfigMessage("ambulance.receive-message").replace("{x}", String.valueOf(x)).replace("{y}", String.valueOf(y)).replace("{z}", String.valueOf(z)), getSettingString("ambulance.receive-permission"));
            e.getPlayer().sendMessage(getConfigMessage("ambulance.called"));
        }

    }
}
