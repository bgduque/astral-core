package org.dice.astralcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Freeze implements CommandExecutor, Listener {
    private final Set<Player> frozenPlayers = new HashSet<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length == 0) {
            commandSender.sendMessage("Specify a player!");
        }

        Player target = Bukkit.getPlayer(strings[0]);
        if (target == null) {
            commandSender.sendMessage("Player not found!");
            return true;
        }

        /*Here we actually can see that the value of player
        has been initialized and instated as the command sender
        if the commandSender is ! a player then it returns,
        executes the last block, and it does not go on.
         */

        if (frozenPlayers.contains(target)) {
            frozenPlayers.remove(target);
            target.sendMessage("You have been unfrozen!");
            commandSender.sendMessage("Player " + target.getName() + " has been frozen!");
        } else {
            frozenPlayers.add(target);
            target.sendMessage("Frozen!");
            commandSender.sendMessage("Player " + target.getName() + " have been frozen!");
            return true;
        }
        return true;
    }
}
