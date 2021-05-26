package ml.zdoctor.commands;

import ml.zdoctor.API.API;
import ml.zdoctor.API.InvseeOpenEvent;
import ml.zdoctor.utils.CCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static ml.zdoctor.API.API.*;

public class Invsee extends CCommand {

    public Invsee() {
        super(getSettingString("invsee.command"));
    }
    @Override
    public void run(CommandSender sender, String cl, String[] args) {

        int distance = getSettingInt("invsee.max-distance");

        if (sender.hasPermission(API.getPermission("invsee"))) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    Player criminal = Bukkit.getPlayer(args[0]);
                    if (criminal.isOnline()) {
                        if (((Player) sender).getLocation().distance(criminal.getLocation()) < distance) {
                            ((Player) sender).openInventory(criminal.getInventory());
                            Bukkit.getServer().getPluginManager().callEvent(new InvseeOpenEvent((Player) sender, criminal, criminal.getInventory()));
                        } else {
                            sender.sendMessage(PlaceHolders((Player) sender, getConfigMessage("invsee.too-distant").replace("{distance}", String.valueOf(distance))));
                        }
                    } else {
                        sender.sendMessage(PlaceHolders((Player) sender, getConfigMessage("general.player-not-online")));
                    }
                } else {
                    sender.sendMessage(PlaceHolders((Player) sender, getConfigMessage("invsee.usage")));
                }
            } else {
                sender.sendMessage(getConfigMessage("general.only-players"));
            }
        } else {
            sender.sendMessage(getConfigMessage("general.no-permission"));
        }
    }
}