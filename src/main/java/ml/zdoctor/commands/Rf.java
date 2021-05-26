package ml.zdoctor.commands;

import ml.zdoctor.API.API;
import ml.zdoctor.API.AmbulanceCallEvent;
import ml.zdoctor.RoleplayFeatures;
import ml.zdoctor.utils.CCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static ml.zdoctor.API.API.*;

public class Rf extends CCommand {

    public Rf() {
        super("rf");
    }
    @Override
    public void run(CommandSender sender, String cl, String[] args) {

        if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            if (sender.hasPermission(API.getPermission("help"))) {
                sender.sendMessage(Color("&a&lRoleplayFeatures &7v"+RoleplayFeatures.getInstance().getDescription().getVersion()));
                sender.sendMessage(Color("&fCommands:"));
                sender.sendMessage(Color("&6/rf help &7» &eShow this list"));
                sender.sendMessage(Color("&6/rf reload &7» &eReload the config"));
                sender.sendMessage(Color("&6/"+getSettingString("receipt.command")+" &7» &eMake a receipt"));
                sender.sendMessage(Color("&6/"+getSettingString("invsee.command")+" &7» &eSee the inventory of another player"));
                sender.sendMessage(Color("&6/"+getSettingString("police.command")+" &7» &eCall the police"));
                sender.sendMessage(Color("&6/"+getSettingString("ambulance.command")+" &7» &eCall an ambulance"));
                sender.sendMessage(Color("&6/"+getSettingString("mask.command")+" &7» &eGet a mask"));
                sender.sendMessage(Color("&6/"+getSettingString("handcuffs.command")+" &7» &eGet handcuffs"));
            } else {
                sender.sendMessage(Color("&a&lRoleplayFeatures &7By zDoctor_"));
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission(API.getPermission("reload"))) {
                RoleplayFeatures.getInstance().saveConfig();
                RoleplayFeatures.getInstance().reloadConfig();
                sender.sendMessage(Color("&aConfiguration reloaded!"));
                sender.sendMessage(Color("&f&o[Note that for some settings you need to restart the server]"));
            } else {
                sender.sendMessage(getConfigMessage("general.no-permission"));
            }
        } else {
            sender.sendMessage(Color("&a&lRoleplayFeatures &7By zDoctor_"));
            sender.sendMessage(Color("&f&oType /rf help for a list of commands"));
        }
    }
}