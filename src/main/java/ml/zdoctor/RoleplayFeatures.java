package ml.zdoctor;

import ml.zdoctor.Listeners.EmergencyCalls;
import ml.zdoctor.Listeners.MaskEvents;
import ml.zdoctor.Listeners.PlayerInteractEvents;
import ml.zdoctor.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

import static ml.zdoctor.API.API.*;

public final class RoleplayFeatures extends JavaPlugin {

    private static RoleplayFeatures plugin;
    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        getCommand(getSettingString("receipt.command")).setExecutor(new Receipt());
        getCommand(getSettingString("invsee.command")).setExecutor(new Invsee());
        getCommand(getSettingString("police.command")).setExecutor(new Police());
        getCommand(getSettingString("ambulance.command")).setExecutor(new Ambulance());
        getCommand(getSettingString("handcuffs.command")).setExecutor(new Handcuffs());

        getServer().getPluginManager().registerEvents(new EmergencyCalls(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEvents(), this);

        getLogger().info(Color("&e------------------"));
        getLogger().info(Color("&6&l RoleplayFeatures "));
        getLogger().info(Color("&7      v"+getDescription().getVersion()));
        getLogger().info(Color("&e------------------"));


        if (getServer().getPluginManager().isPluginEnabled("TAB")) {
            getServer().getPluginManager().registerEvents(new MaskEvents(), this);
            getLogger().info(Color("&e------------------"));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static RoleplayFeatures getInstance() {
        return plugin;
    }
}
