package org.dice.astralcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            return false;
        }

        Player p = (Player) commandSender;

        if (commandSender.hasPermission("a.fly")) {
            if (p.getAllowFlight()) {
                p.sendMessage("Flight Disabled!");
                p.setAllowFlight(false);
                p.setFlying(false);
            } else {
                p.sendMessage("Flight Enabled!");
                p.setAllowFlight(true);
                p.setFlying(true);
            }
        }
        return true;
    }
}
