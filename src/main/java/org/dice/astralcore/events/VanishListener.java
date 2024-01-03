package org.dice.astralcore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.dice.astralcore.Main;
import org.dice.astralcore.commands.Vanish;

public class VanishListener implements Listener {
    private final Vanish vanish;
    private final Main mainInstance;

    public VanishListener(Vanish vanish, Main mainInstance) {
        this.vanish = vanish;
        this.mainInstance = mainInstance;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onNewJoin(PlayerJoinEvent event) {
        Player newJoin = event.getPlayer();
        for (Player vanishedPlayer : vanish.getVanishedPlayers()) {
            if (!newJoin.hasPermission("v.see")) {
                newJoin.hidePlayer(vanish.getPlugin(), vanishedPlayer);
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void playerPickupItem(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player p && vanish.getVanishedPlayers().contains(p)) {
            event.setCancelled(true);
        }
    }
}
