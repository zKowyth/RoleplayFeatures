package ml.zdoctor.listeners;

import ml.zdoctor.utils.Features;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static ml.zdoctor.RoleplayFeatures.*;
import static ml.zdoctor.API.API.*;

public class AgeGenderEvents implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (Features.isEnabled(Features.Feature.GENDER)) {
            if (!getSettingBoolean("gender.chat-allow")) {
                if (!isGenderSet(e.getPlayer().getName())) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(getConfigMessage("gender.cant-chat"));
                }
            }
        }


        if (Features.isEnabled(Features.Feature.AGE)) {
            if (!getSettingBoolean("age.chat-allow")) {
                if (!isAgeSet(e.getPlayer().getName())) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(getConfigMessage("age.cant-chat"));
                }
            }
        }

    }
}
