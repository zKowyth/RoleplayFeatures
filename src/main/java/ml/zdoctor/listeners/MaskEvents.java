package ml.zdoctor.listeners;

import me.neznamy.tab.api.TABAPI;
import ml.zdoctor.utils.Features;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static ml.zdoctor.API.API.*;

public class MaskEvents implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (Features.isEnabled(Features.Feature.MASK)) {
            ItemStack mask = new ItemStack(Material.valueOf(getIDOfItem("mask")));
            ItemMeta meta = mask.getItemMeta();
            meta.setDisplayName(PlaceHolders(e.getPlayer(), getDisplayNameOfItem("mask")));
            meta.setLore(getLoreOfItem("mask"));
            mask.setItemMeta(meta);

            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (e.getPlayer().getInventory().getItemInMainHand() != null && e.getPlayer().getInventory().getItemInMainHand().equals(mask)) {
                    if (e.getPlayer().getInventory().getHelmet() == null) {
                        e.getPlayer().getInventory().getItemInMainHand().setAmount(-1);
                        e.getPlayer().getInventory().setHelmet(mask);
                        TABAPI.getPlayer(e.getPlayer().getUniqueId()).hideNametag();
                    } else {
                        e.getPlayer().sendMessage(getConfigMessage("mask.helmet-on"));
                    }
                }
            }
        }

    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (Features.isEnabled(Features.Feature.MASK)) {
            ItemStack mask = new ItemStack(Material.valueOf(getIDOfItem("mask")));
            ItemMeta meta = mask.getItemMeta();
            meta.setDisplayName(PlaceHolders((Player) e.getWhoClicked(), getDisplayNameOfItem("mask")));
            meta.setLore(getLoreOfItem("mask"));
            mask.setItemMeta(meta);

            if (e.getClickedInventory() != null && e.getCurrentItem() != null && e.getSlotType().equals(InventoryType.SlotType.ARMOR) && e.getCurrentItem().equals(mask)) {
                e.setCancelled(true);
                e.getWhoClicked().getInventory().setHelmet(new ItemStack(Material.AIR));
                e.getWhoClicked().getInventory().addItem(mask);
                TABAPI.getPlayer(e.getWhoClicked().getUniqueId()).showNametag();
            }
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (Features.isEnabled(Features.Feature.MASK)) {
            if (hasMask(e.getEntity())) {
                TABAPI.getPlayer(e.getEntity().getUniqueId()).showNametag();
            }
        }

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (Features.isEnabled(Features.Feature.MASK)) {
            if (hasMask(e.getPlayer())) {
                e.setCancelled(true);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getLocation().distance(e.getPlayer().getLocation()) <= getSettingInt("mask.chat-distance")) {
                        player.sendMessage(getSettingString("mask.message-format").replace("{message}", e.getMessage()));
                    }
                }
            }
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (hasMask(e.getPlayer())) {
            TABAPI.getPlayer(e.getPlayer().getUniqueId()).showNametag();
        }
    }
}