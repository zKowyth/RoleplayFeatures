package ml.zdoctor;

import ml.zdoctor.listeners.EmergencyCalls;
import ml.zdoctor.listeners.MaskEvents;
import ml.zdoctor.listeners.PlayerInteractEvents;
import ml.zdoctor.commands.*;
import ml.zdoctor.papi.Expansion;
import org.bukkit.Bukkit;
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
        getCommand(getSettingString("mask.command")).setExecutor(new Mask());
        getCommand("rf").setExecutor(new Rf());

        getServer().getPluginManager().registerEvents(new EmergencyCalls(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEvents(), this);

        getLogger().info(Color("&e------------------"));
        getLogger().info(Color("&6&l RoleplayFeatures "));
        getLogger().info(Color("&7      v"+getDescription().getVersion()));
        getLogger().info(Color("&e------------------"));


        if (getServer().getPluginManager().isPluginEnabled("TAB")) {
            getServer().getPluginManager().registerEvents(new MaskEvents(), this);
            getLogger().info(Color("&aTAB plugin found! Mask enabled!"));
        } else {
            getLogger().info(Color("&cTAB plugin not found! Mask not enabled!"));
        }

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            new Expansion().register();
            getLogger().info(Color("&aPlaceholderAPI is enabled, time expansion registered!"));
        } else {
            getLogger().info(Color("&cPlaceholderAPI is not enabled, time expansion not registered!"));
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
