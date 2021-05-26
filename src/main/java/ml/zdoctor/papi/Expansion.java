package ml.zdoctor.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

import static ml.zdoctor.time.Time.convertTime;

public class Expansion extends PlaceholderExpansion {

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "zDoctor_";
    }

    @Override
    public String getIdentifier() {
        return "roleplay";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {

        // %roleplay_hours%
        if (identifier.equals("hours")) {
            return String.valueOf(convertTime(p.getWorld().getTime(), TimeUnit.HOURS));
        }
        // %roleplay_minutes%
        if (identifier.equals("minutes")) {
            return String.valueOf(convertTime(p.getWorld().getTime(), TimeUnit.MINUTES));
        }
        // %roleplay_minutes%
        if (identifier.equals("seconds")) {
            return String.valueOf(convertTime(p.getWorld().getTime(), TimeUnit.SECONDS));
        }

        return null;
    }
}