package ml.zdoctor.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ReceiptGivenEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player fromPlayer;
    private Player toPlayer;
    private Integer money;
    private String items;

    public ReceiptGivenEvent(Player fromPlayer, Player toPlayer, Integer money, String items) {
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
        this.money = money;
        this.items = items;
    }

    public Player getFromPlayer() {
        return fromPlayer;
    }

    public Player getToPlayer() {
        return toPlayer;
    }

    public Integer getMoney() {
        return money;
    }

    public String getItems() {
        return items;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}