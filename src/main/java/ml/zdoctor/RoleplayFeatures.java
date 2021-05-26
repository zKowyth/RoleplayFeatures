package ml.zdoctor;

import ml.zdoctor.listeners.EmergencyCalls;
import ml.zdoctor.listeners.MaskEvents;
import ml.zdoctor.listeners.PlayerInteractEvents;
import ml.zdoctor.commands.*;
import ml.zdoctor.papi.Expansion;
import ml.zdoctor.utils.CCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Arrays;

import static ml.zdoctor.API.API.*;

public final class RoleplayFeatures extends JavaPlugin {

    private static SimpleCommandMap scm;
    private SimplePluginManager spm;
    private static RoleplayFeatures plugin;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        setupSimpleCommandMap();


        getServer().getPluginManager().registerEvents(new EmergencyCalls(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEvents(), this);

        registerCommands(new Ambulance(), new Handcuffs(), new Invsee(), new Mask(), new Police(), new Receipt(), new Rf());

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

    private void registerCommands(CCommand... commands) {
        Arrays.stream(commands).forEach(command -> scm.register("pluginname", command));//Register the plugin
    }

    private void setupSimpleCommandMap() {
        spm = (SimplePluginManager) this.getServer().getPluginManager();
        Field f = null;
        try {
            f = SimplePluginManager.class.getDeclaredField("commandMap");
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            scm = (SimpleCommandMap) f.get(spm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SimpleCommandMap getCommandMap() {
        return scm;
    }
}
