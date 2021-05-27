package ml.zdoctor.commands;

import ml.zdoctor.API.API;
import ml.zdoctor.API.ReceiptGivenEvent;
import ml.zdoctor.utils.CommandMaker;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static ml.zdoctor.API.API.*;

public class Receipt extends CommandMaker {

    public Receipt() {
        super(getSettingString("receipt.command"));
    }
    @Override
    public void run(CommandSender sender, String cl, String[] args) {
        ItemStack receipt = new ItemStack(Material.valueOf(getIDOfItem("receipt")));
        ItemMeta meta = receipt.getItemMeta();

        String items = "";

        for (int i = 4; i < args.length; i++) {
            String arg = args[i] + " ";
            items = items + arg;
        }

        if (args.length >= 4) {
            Player user = Bukkit.getPlayer(args[0]);
            if (sender.hasPermission(API.getPermission("receipt").replace("{company}", args[2]))) {
                if (user.isOnline()) {
                    meta.setDisplayName(PlaceHolders((Player) sender, getDisplayNameOfItem("receipt").replace("{to_player}", user.getName()).replace("{by_player}", sender.getName()).replace("{money}", args[1]).replace("{items}", items).replace("{company}", args[2])));
                    meta.setLore(getLoreOfReceipt(user, (Player) sender, Integer.valueOf(args[1]), items, args[2]));
                    receipt.setItemMeta(meta);
                    user.getInventory().addItem(receipt);
                    sender.sendMessage(PlaceHolders((Player) sender, getConfigMessage("receipt.receipt-given").replace("{to_player}", user.getName()).replace("{by_player}", sender.getName()).replace("{money}", args[1]).replace("{items}", items).replace("{company}", args[2])));
                    Bukkit.getServer().getPluginManager().callEvent(new ReceiptGivenEvent((Player) sender, user, Integer.valueOf(args[1]), items));
                } else {
                    sender.sendMessage(PlaceHolders((Player) sender, getConfigMessage("general.player-not-online")));
                }
            } else {
                sender.sendMessage(PlaceHolders((Player) sender, getConfigMessage("receipt.not-employee")));
            }
        } else {
            sender.sendMessage(PlaceHolders((Player) sender, getConfigMessage("receipt.usage")));
        }
    }

    private List<String> getLoreOfReceipt (Player to_player, Player by_player, Integer money, String items, String company)
    {
        List<String> lores = new ArrayList<>();
        for (Object lore : getConfiguration().getStringList("items.receipt.lore")) {
            lores.add(Color(lore.toString()).replace("{to_player}", to_player.getName()).replace("{by_player}", by_player.getName()).replace("{money}", money.toString()).replace("{items}", items).replace("{company}", company));
        }
        return lores;
    }
}