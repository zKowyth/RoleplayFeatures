package ml.zdoctor.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GenderSetEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player player;
    private String gender;

    public GenderSetEvent(Player player, String gender) {
        this.player = player;
        this.gender = gender;
    }

    public Player getPlayer() {
        return player;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}