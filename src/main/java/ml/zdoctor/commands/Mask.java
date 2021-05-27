package ml.zdoctor.commands;

import ml.zdoctor.API.API;
import ml.zdoctor.utils.CommandMaker;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static ml.zdoctor.API.API.*;

public class Mask extends CommandMaker {

    public Mask() {
        super(getSettingString("mask.command"));
    }
    @Override
    public void run(CommandSender sender, String cl, String[] args) {

        if (sender.hasPermission(API.getPermission("mask"))) {
            if (sender instanceof Player) {
                ItemStack mask = new ItemStack(Material.valueOf(getIDOfItem("mask")));
                ItemMeta meta = mask.getItemMeta();
                meta.setDisplayName(PlaceHolders((Player) sender, getDisplayNameOfItem("mask")));
                meta.setLore(getLoreOfItem("mask"));
                mask.setItemMeta(meta);

                ((Player) sender).getInventory().addItem(mask);
            } else {
                sender.sendMessage(getConfigMessage("general.only-players"));
            }
        } else {
            sender.sendMessage(getConfigMessage("general.no-perm"));
        }
    }
}