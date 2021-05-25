package ml.zdoctor.commands;

import ml.zdoctor.API.PoliceCallEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static ml.zdoctor.API.API.*;

public class Police implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase(getSettingString("police.command"))) {
            if (sender.hasPermission(getPermission("police"))) {
                if (sender instanceof Player) {
                    Bukkit.getServer().getPluginManager().callEvent(new PoliceCallEvent((Player) sender));
                } else {
                    sender.sendMessage(getConfigMessage("general.only-players"));
                }
            }
        }
        return true;
    }
}
