package ml.zdoctor;

import ml.zdoctor.listeners.*;
import ml.zdoctor.commands.*;
import ml.zdoctor.papi.Expansion;
import ml.zdoctor.utils.CommandMaker;
import ml.zdoctor.utils.UpdateChecker;
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
        getServer().getPluginManager().registerEvents(new HandcuffsEvents(), this);
        getServer().getPluginManager().registerEvents(new AgeGenderEvents(), this);
        getServer().getPluginManager().registerEvents(new GeneralEvents(), this);
        getServer().getPluginManager().registerEvents(new DisablePlayerPvP(), this);

        registerCommands(new Ambulance(), new Handcuffs(), new Invsee(), new Police(), new Receipt(), new Rf(), new Age(), new Mask(), new Gender());

        getLogger().info(Color("&e------------------"));
        getLogger().info(Color("&6&l RoleplayFeatures "));
        getLogger().info(Color("&7      v"+getDescription().getVersion()));
        getLogger().info(Color("&e------------------"));

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            new Expansion().register();
            getLogger().info(Color("&aPlaceholderAPI is enabled, time expansion registered!"));
        } else {
            getLogger().info(Color("&cPlaceholderAPI is not enabled, time expansion not registered!"));
        }

        if (Bukkit.getPluginManager().isPluginEnabled("TAB")) {
            getLogger().info(Color("&aTAB plugin found, mask enabled!"));
            getServer().getPluginManager().registerEvents(new MaskEvents(), this);
        } else {
            getLogger().info(Color("&cTAB plugin not found, mask not enabled!"));
        }

        if (getSettingBoolean("general.update-checker-enabled")) {
            new UpdateChecker(this, 92730).getVersion(version -> {
                if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                    getLogger().info("§aThere are no updates!");
                } else {
                    getLogger().info("§eThere is a new update available! Check the spigot page:");
                    getLogger().info("§f§nhttps://www.spigotmc.org/resources/roleplayfeatures-fully-configurable-%E2%97%8F-must-have.92730/");
                }
            });
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static RoleplayFeatures getInstance() {
        return plugin;
    }

    private void registerCommands(CommandMaker... commands) {
        Arrays.stream(commands).forEach(command -> scm.register("roleplayfeatures", command));
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
