package ml.zdoctor.listeners;

import me.neznamy.tab.api.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static ml.zdoctor.API.API.*;

public class MaskEvents implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(getDisplayNameOfItem("mask"))) {
                if (e.getPlayer().getInventory().getHelmet() != null) {
                    e.getPlayer().sendMessage(getConfigMessage("mask.helmet-on"));
                } else {
                    e.getPlayer().getInventory().setHelmet(e.getPlayer().getInventory().getItemInMainHand());
                    e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                    TABAPI.hideNametag(e.getPlayer().getUniqueId());
                }
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null) {
            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals(getDisplayNameOfItem("mask")) && e.getSlotType() == InventoryType.SlotType.ARMOR) {
                e.setCancelled(true);
                e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
                e.getCurrentItem().setAmount(0);
                TABAPI.showNametag(e.getWhoClicked().getUniqueId());
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        TABAPI.showNametag(e.getEntity().getPlayer().getUniqueId());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        int distance = getSettingInt("mask.chat-distance");

        if (e.getPlayer().getInventory().getHelmet() != null && e.getPlayer().getInventory().getHelmet().getItemMeta().getDisplayName().equals(getDisplayNameOfItem("mask"))) {
            e.setCancelled(true);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getLocation().distance(e.getPlayer().getLocation()) <= distance) {
                    player.sendMessage(Color(getSettingString("mask.message-format").replace("{message}", e.getMessage())));
                }
            }
        }
    }
}
