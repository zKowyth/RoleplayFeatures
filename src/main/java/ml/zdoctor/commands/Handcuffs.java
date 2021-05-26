package ml.zdoctor.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static ml.zdoctor.API.API.*;

public class Handcuffs implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ItemStack handcuffs = new ItemStack(Material.valueOf(getIDOfItem("handcuffs")));
        ItemMeta meta = handcuffs.getItemMeta();
        meta.setDisplayName(PlaceHolders((Player) sender, getDisplayNameOfItem("handcuffs")));
        meta.setLore(getLoreOfItem("handcuffs"));
        handcuffs.setItemMeta(meta);

        if (command.getName().equalsIgnoreCase(getSettingString("handcuffs.command"))) {
            if (sender.hasPermission(getPermission("handcuffs"))) {
                if (sender instanceof Player) {
                    ((Player) sender).getInventory().addItem(handcuffs);
                } else {
                    sender.sendMessage(getConfigMessage("general.only-players"));
                }
            }
        }
        return true;
    }
}