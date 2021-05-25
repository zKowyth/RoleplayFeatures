package ml.zdoctor.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

public class InvseeOpenEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player viewer;
    private Player viewed;
    private Inventory inventory;

    public InvseeOpenEvent(Player viewer, Player viewed, Inventory inventory) {
        this.viewer = viewer;
        this.viewed = viewed;
        this.inventory = inventory;
    }

    public Player getPlayerViewer() {
        return viewer;
    }

    public Player getPlayerViewed() {
        return viewed;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}