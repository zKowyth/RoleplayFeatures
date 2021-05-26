package ml.zdoctor.commands;

import ml.zdoctor.API.PoliceCallEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static ml.zdoctor.API.API.*;

public class Mask implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ItemStack mask = new ItemStack(Material.valueOf(getIDOfItem("mask")));
        ItemMeta meta = mask.getItemMeta();
        meta.setDisplayName(PlaceHolders((Player) sender, getDisplayNameOfItem("mask")));
        meta.setLore(getLoreOfItem("mask"));
        mask.setItemMeta(meta);

        if (command.getName().equalsIgnoreCase(getSettingString("mask.command"))) {
            if (sender.hasPermission(getPermission("mask"))) {
                if (sender instanceof Player) {
                    ((Player) sender).getInventory().addItem(mask);
                } else {
                    sender.sendMessage(getConfigMessage("general.only-players"));
                }
            }
        }
        return true;
    }
}
