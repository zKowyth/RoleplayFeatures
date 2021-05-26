package ml.zdoctor.commands;

import ml.zdoctor.API.API;
import ml.zdoctor.API.AmbulanceCallEvent;
import ml.zdoctor.API.PoliceCallEvent;
import ml.zdoctor.utils.CCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static ml.zdoctor.API.API.getConfigMessage;
import static ml.zdoctor.API.API.getSettingString;

public class Police extends CCommand {

    public Police() {
        super(getSettingString("police.command"));
    }
    @Override
    public void run(CommandSender sender, String cl, String[] args) {

        if (sender.hasPermission(API.getPermission("police"))) {
            if (sender instanceof Player) {
                Bukkit.getServer().getPluginManager().callEvent(new PoliceCallEvent((Player) sender));
            } else {
                sender.sendMessage(getConfigMessage("general.only-players"));
            }
        } else {
            sender.sendMessage(getConfigMessage("general.no-permission"));
        }
    }
}