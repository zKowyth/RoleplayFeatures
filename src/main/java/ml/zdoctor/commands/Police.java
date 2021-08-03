package ml.zdoctor.commands;

import ml.zdoctor.API.API;
import ml.zdoctor.API.PoliceCallEvent;
import ml.zdoctor.utils.CommandMaker;
import ml.zdoctor.utils.Features;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static ml.zdoctor.API.API.getConfigMessage;
import static ml.zdoctor.API.API.getSettingString;

public class Police extends CommandMaker {

    public Police() {
        super(getSettingString("police.command"));
    }
    @Override
    public void run(CommandSender sender, String cl, String[] args) {

        if (Features.isEnabled(Features.Feature.POLICE)) {
            if (sender.hasPermission(API.getPermission("police"))) {
                if (sender instanceof Player) {
                    Bukkit.getServer().getPluginManager().callEvent(new PoliceCallEvent((Player) sender));
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