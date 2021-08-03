package ml.zdoctor.commands;

import ml.zdoctor.API.API;
import ml.zdoctor.API.AgeSetEvent;
import ml.zdoctor.API.GenderSetEvent;
import ml.zdoctor.utils.CommandMaker;
import ml.zdoctor.utils.Features;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static ml.zdoctor.API.API.*;

public class Gender extends CommandMaker {

    public Gender() {
        super(getSettingString("gender.command"));
    }
    @Override
    public void run(CommandSender sender, String cl, String[] args) {

        if (Features.isEnabled(Features.Feature.GENDER)) {
            if (sender.hasPermission(API.getPermission("gender"))) {
                if (sender instanceof Player) {
                    if (!getSettingBoolean("gender.can-change-gender")) {
                        if (isAgeSet(sender.getName())) {
                            sender.sendMessage(getConfigMessage("gender.cant-change"));
                        } else {
                            if (args.length == 1) {
                                if (getSettingList("gender.genders").contains(args[0])) {
                                    sender.sendMessage(getConfigMessage("gender.gender-set").replace("{gender}", args[0]));
                                    setGender(sender.getName(), args[0]);
                                    Bukkit.getServer().getPluginManager().callEvent(new GenderSetEvent((Player) sender, args[0]));
                                } else {
                                    sender.sendMessage(getConfigMessage("gender.invalid-gender"));
                                }
                            } else {
                                sender.sendMessage(getConfigMessage("gender.usage"));
                            }
                        }
                    } else {
                        if (args.length == 1) {
                            if (getSettingList("gender.genders").contains(args[0])) {
                                sender.sendMessage(getConfigMessage("gender.gender-set"));
                                setGender(sender.getName(), args[0]);
                                Bukkit.getServer().getPluginManager().callEvent(new GenderSetEvent((Player) sender, args[0]));
                            } else {
                                sender.sendMessage(getConfigMessage("gender.invalid-gender"));
                            }
                        } else {
                            sender.sendMessage(getConfigMessage("gender.usage"));
                        }
                    }
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