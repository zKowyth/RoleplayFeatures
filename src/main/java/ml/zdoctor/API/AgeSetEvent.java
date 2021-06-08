package ml.zdoctor.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AgeSetEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player player;
    private Integer age;

    public AgeSetEvent(Player player, Integer age) {
        this.player = player;
        this.age = age;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}