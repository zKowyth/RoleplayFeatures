package ml.zdoctor.listeners;

import ml.zdoctor.API.API;
import ml.zdoctor.RoleplayFeatures;
import ml.zdoctor.utils.Features;
import ml.zdoctor.utils.UpdateChecker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static ml.zdoctor.API.API.getPermission;
import static ml.zdoctor.API.API.getSettingBoolean;

public class DisablePlayerPvP implements Listener {

    @EventHandler
    public static void onDamage(EntityDamageByEntityEvent e) {

        // Took this from my personal server core, If you wanna join here's the ip ^^
        // mc.oak-network.ml

        if (Features.isEnabled(Features.Feature.DISABLEPVP)) {
            if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
                e.setCancelled(true);
                if (API.getSettingBoolean("disable-player-pvp.cant-do-it-message-enabled")) {
                    e.getDamager().sendMessage(API.getConfigMessage("disable-player-pvp.cant-pvp"));
                }
            }
        }
    }
}
