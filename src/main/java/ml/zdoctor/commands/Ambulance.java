package ml.zdoctor.commands;

import ml.zdoctor.API.AmbulanceCallEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static ml.zdoctor.API.API.*;

public class Ambulance implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase(getSettingString("ambulance.command"))) {
            if (sender.hasPermission(getPermission("ambulance"))) {
                if (sender instanceof Player) {
                    Bukkit.getServer().getPluginManager().callEvent(new AmbulanceCallEvent((Player) sender));
                } else {
                    sender.sendMessage(PlaceHolders((Player) sender, getConfigMessage("general.only-players")));
                }
            }
        }
        return true;
    }
}
