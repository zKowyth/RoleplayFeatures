package ml.zdoctor.commands;

import ml.zdoctor.API.API;
import ml.zdoctor.utils.CommandMaker;
import ml.zdoctor.utils.Features;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static ml.zdoctor.API.API.*;

public class Handcuffs extends CommandMaker {

    public Handcuffs() {
        super(getSettingString("handcuffs.command"));
    }
    @Override
    public void run(CommandSender sender, String cl, String[] args) {

        if (Features.isEnabled(Features.Feature.HANDCUFFS)) {
            if (sender.hasPermission(API.getPermission("handcuffs"))) {
                if (sender instanceof Player) {
                    ItemStack handcuffs = new ItemStack(Material.valueOf(getIDOfItem("handcuffs")));
                    ItemMeta meta = handcuffs.getItemMeta();
                    meta.setDisplayName(PlaceHolders((Player) sender, getDisplayNameOfItem("handcuffs")));
                    meta.setLore(getLoreOfItem("handcuffs"));
                    handcuffs.setItemMeta(meta);

                    ((Player) sender).getInventory().addItem(handcuffs);
                } else {
                    sender.sendMessage(getConfigMessage("general.only-players"));
                }
            } else {
                sender.sendMessage(getConfigMessage("general.no-permission"));
            }
        } else {
            sender.sendMessage(getConfigMessage("general.not-enabled"));
        }


    }
}