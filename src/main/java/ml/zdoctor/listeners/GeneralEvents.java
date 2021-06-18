package ml.zdoctor.listeners;

import ml.zdoctor.RoleplayFeatures;
import ml.zdoctor.utils.UpdateChecker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static ml.zdoctor.API.API.*;

public class GeneralEvents implements Listener {

    @EventHandler
    public static void onJoin(PlayerJoinEvent e) {
        if (getSettingBoolean("general.update-checker-enabled")) {
            if (e.getPlayer().hasPermission(getPermission("update-checker-message"))) {
                new UpdateChecker(RoleplayFeatures.getInstance(), 92730).getVersion(version -> {
                    if (!RoleplayFeatures.getInstance().getDescription().getVersion().equalsIgnoreCase(version)) {

                        e.getPlayer().sendMessage("§eThere is a new update available! Check the spigot page:");
                        e.getPlayer().sendMessage("§f§nhttps://www.spigotmc.org/resources/roleplayfeatures-fully-configurable-%E2%97%8F-must-have.92730/");
                    }
                });
            }
        }

    }

}
