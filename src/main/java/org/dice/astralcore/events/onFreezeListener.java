package org.dice.astralcore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.dice.astralcore.commands.Freeze;

public class onFreezeListener implements Listener {
    private Freeze freeze;
    @EventHandler(priority = EventPriority.HIGH)
    public void onFrozenState(PlayerMoveEvent event) {
        if (freeze.getFrozenPlayers().contains(event.getPlayer())) {
            event.setCancelled(false);
        }
    }
}
