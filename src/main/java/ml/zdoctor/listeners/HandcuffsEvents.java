package ml.zdoctor.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static ml.zdoctor.API.API.*;

public class HandcuffsEvents implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        if (getSettingString("handcuffs.action-on-player").equalsIgnoreCase("slowness")) {
            if (e.getPlayer().getInventory().getItemInMainHand() != null) {
                if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(getDisplayNameOfItem("handcuffs"))) {
                    if (e.getRightClicked().getType().equals(EntityType.PLAYER)) {
                        Player player = (Player) e.getRightClicked();
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, getSettingInt("handcuffs.slowness-duration"), 2, false, false));
                    }
                }
            }
        }
    }
}
