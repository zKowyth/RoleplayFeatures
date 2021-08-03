package ml.zdoctor.API;

import me.clip.placeholderapi.PlaceholderAPI;
import ml.zdoctor.utils.Features;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static ml.zdoctor.RoleplayFeatures.*;

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
     * Returns the string with placeholders
     *
     * @return The string with placeholders
     */
    public static String PlaceHolders(Player p, String string) {
        string = PlaceholderAPI.setPlaceholders(p, string);
        return string;
    }

    /**
     * Returns if a feature is enabled
     *
     * @return if a feature is enabled
     */
    public static boolean isEnabled(Features.Feature feature) {
        return Features.isEnabled(feature);
    }

    /**
     * Returns the string from messages config section with color codes
     *
     * @return The string from messages config section with color codes
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
        return getInstance().getConfig();
    }

    /**
     * Returns a string from the settings section
     *
     * @return A string from the settings section
     */
    public static String getSettingString(String path) {
        return getInstance().getConfig().getString("settings."+path);
    }

    /**
     * Returns an integer from the settings section
     *
     * @return An integer from the settings section
     */
    public static Integer getSettingInt(String path) {
        return getInstance().getConfig().getInt("settings."+path);
    }

    /**
     * Returns a boolean from the settings section
     *
     * @return A boolean from the settings section
     */
    public static Boolean getSettingBoolean(String path) {
        return getInstance().getConfig().getBoolean("settings." + path);
    }

    /**
     * Returns a boolean from the settings section
     *
     * @return A boolean from the settings section
     */
    public static List getSettingList(String path) {
        return getInstance().getConfig().getList("settings." + path);
    }

    /**
     * Returns if the age of a player is set
     *
     * @return If the age of a player is set
     */
    public static Boolean isAgeSet(String player) {
        if (getInstance().getConfig().isSet("gender_and_age."+player+".age")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if the gender of a player is set
     *
     * @return If the gender of a player is set
     */
    public static Boolean isGenderSet(String player) {
        if (getInstance().getConfig().isSet("gender_and_age."+player+".gender")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the age of a player
     *
     * @return The age of a player
     */
    public static Integer getAge(String player) {

        return getInstance().getConfig().getInt("gender_and_age."+player+".age");
    }

    /**
     * Returns the gender of a player
     *
     * @return The gender of a player
     */
    public static String getGender(String player) {

        return getInstance().getConfig().getString("gender_and_age."+player+".gender");
    }

    /**
     * Sets the age of a player
     */
    public static void setAge(String player, Integer age) {
        getConfiguration().set("gender_and_age."+player+".age", age);
    }

    /**
     * Sets the gender of a player
     */
    public static void setGender(String player, String gender) {
        getConfiguration().set("gender_and_age."+player+".gender", gender);
    }

    /**
     * Returns if the player has the mask
     *
     * @return If the player has the mask
     */
    public static Boolean hasMask(Player player) {
        ItemStack mask = new ItemStack(Material.valueOf(getIDOfItem("mask")));
        ItemMeta meta = mask.getItemMeta();
        meta.setDisplayName(PlaceHolders(player, getDisplayNameOfItem("mask")));
        meta.setLore(getLoreOfItem("mask"));
        mask.setItemMeta(meta);

        if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().equals(mask)) {
            return true;
        } else {
            return false;
        }
    }
}
