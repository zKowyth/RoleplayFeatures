package ml.zdoctor.commands;

import ml.zdoctor.API.API;
import ml.zdoctor.API.AmbulanceCallEvent;
import ml.zdoctor.utils.CommandMaker;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static ml.zdoctor.API.API.*;

public class Ambulance extends CommandMaker {

    public Ambulance() {
        super(getSettingString("ambulance.command"));
    }
    @Override
    public void run(CommandSender sender, String cl, String[] args) {

        if (sender.hasPermission(API.getPermission("ambulance"))) {
            if (sender instanceof Player) {
                Bukkit.getServer().getPluginManager().callEvent(new AmbulanceCallEvent((Player) sender));
            } else {
                sender.sendMessage(getConfigMessage("general.only-players"));
            }
        } else {
            sender.sendMessage(getConfigMessage("general.no-permission"));
        }
    }
}