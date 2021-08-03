package ml.zdoctor.commands;

import ml.zdoctor.API.API;
import ml.zdoctor.API.AgeSetEvent;
import ml.zdoctor.utils.CommandMaker;
import ml.zdoctor.utils.Features;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static ml.zdoctor.API.API.*;

public class Age extends CommandMaker {

    public Age() {
        super(getSettingString("age.command"));
    }
    @Override
    public void run(CommandSender sender, String cl, String[] args) {
        if (Features.isEnabled(Features.Feature.AGE)) {
            if (sender.hasPermission(API.getPermission("age"))) {
                if (sender instanceof Player) {
                    if (!getSettingBoolean("age.can-change-age")) {
                        if (isAgeSet(sender.getName())) {
                            sender.sendMessage(getConfigMessage("age.cant-change"));
                        } else {
                            if (args.length == 1) {
                                if (Integer.parseInt(args[0]) > getSettingInt("age.min-age") && Integer.parseInt(args[0]) < getSettingInt("age.max-age")) {
                                    sender.sendMessage(getConfigMessage("age.age-set").replace("{age}", args[0]));
                                    setAge(sender.getName(), Integer.parseInt(args[0]));
                                    Bukkit.getServer().getPluginManager().callEvent(new AgeSetEvent((Player) sender, Integer.parseInt(args[0])));
                                } else {
                                    sender.sendMessage(getConfigMessage("age.invalid-age"));
                                }
                            } else {
                                sender.sendMessage(getConfigMessage("age.usage"));
                            }
                        }
                    } else {
                        if (args.length == 1) {
                            if (Integer.parseInt(args[0]) > getSettingInt("age.min-age") && Integer.parseInt(args[0]) < getSettingInt("age.max-age")) {
                                sender.sendMessage(getConfigMessage("age.age-set"));
                                setAge(sender.getName(), Integer.parseInt(args[0]));
                                Bukkit.getServer().getPluginManager().callEvent(new AgeSetEvent((Player) sender, Integer.parseInt(args[0])));
                            } else {
                                sender.sendMessage(getConfigMessage("age.invalid-age"));
                            }
                        } else {
                            sender.sendMessage(getConfigMessage("age.usage"));
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