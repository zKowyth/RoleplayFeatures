package ml.zdoctor.API;

import ml.zdoctor.RoleplayFeatures;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class API {

    /**
     * Returns the string with color codes
     *
     * @return The string with color codes
     */
    public static String Color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Returns the string from config with color codes
     *
     * @return The string from config with color codes
     */
    public static String getConfigMessage(String path) {
        return Color(getConfiguration().getString("messages."+path));
    }

    /**
     * Returns the display name of the item from config with color codes
     *
     * @return The display name of the item from config with color codes
     */
    public static String getDisplayNameOfItem(String item) {
        return Color(getConfiguration().getString("items."+item+".display-name"));
    }

    /**
     * Returns the id of the item from config with
     *
     * @return The id of the item from config with
     */
    public static String getIDOfItem(String item) {
        return getConfiguration().getString("items."+item+".id");
    }

    /**
     * Returns the lore of the item from config with color codes
     *
     * @return The lore of the item from config with color codes
     */
    public static List<String> getLoreOfItem(String item) {
        List<String> lores = new ArrayList<>();
        for (Object lore : getConfiguration().getStringList("items."+item+".lore")) {
            lores.add(Color(lore.toString()));
        }
        return lores;
    }

    /**
     * Returns the permission of the command
     *
     * @return The permission of the command
     */
    public static String getPermission(String permission) {
        return getConfiguration().getString("permissions."+permission);
    }

    /**
     * Returns the configuration
     *
     * @return The configuration
     */
    public static FileConfiguration getConfiguration() {
        return RoleplayFeatures.getInstance().getConfig();
    }

    /**
     * Returns a string from the settings section
     *
     * @return A string from the settings section
     */
    public static String getSettingString(String path) {
        return RoleplayFeatures.getInstance().getConfig().getString("settings."+path);
    }

    /**
     * Returns an integer from the settings section
     *
     * @return An integer from the settings section
     */
    public static Integer getSettingInt(String path) {
        return RoleplayFeatures.getInstance().getConfig().getInt("settings."+path);
    }

    /**
     * Returns a boolean from the settings section
     *
     * @return A boolean from the settings section
     */
    public static Boolean getSettingBoolean(String path) {
        return RoleplayFeatures.getInstance().getConfig().getBoolean("settings." + path);
    }
}
