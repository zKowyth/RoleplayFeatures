package ml.zdoctor.utils;

import ml.zdoctor.RoleplayFeatures;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

public abstract class CCommand extends Command implements PluginIdentifiableCommand {
    CommandSender sender;
   RoleplayFeatures plugin = RoleplayFeatures.getInstance();
    protected CCommand(String name) {
        super(name);
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    public abstract void run(CommandSender sender, String commandLabel, String[] arguments);

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] arguments) {
        this.sender = sender;
        run(sender, commandLabel, arguments);
        return true;
    }
}