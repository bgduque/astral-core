package org.dice.astralcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Speed implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            Logger.getLogger(Speed.class.getName()).log(Level.WARNING, "This is not a console command!");
            return false;
        }

        Player p = (Player) commandSender;

        if (strings.length == 0) {
            p.sendMessage("Please enter a value.");
            return true;
        }

        if (p.hasPermission("core.setFlySpeed")) {
            try {
                float speed = Float.parseFloat(strings[0]) / 10;
                if (speed <0 || speed > 1) {
                    p.sendMessage("Speed must be ranging from 1 - 10");
                    return true;
                }
                p.setFlySpeed(speed);
                p.sendMessage("Set speed to " + strings[0]);
            } catch (NumberFormatException e) {
                p.sendMessage("Invalid speed value.");
            }
        } else {
            p.sendMessage("You're not flying or missing permissions!");
        }

        return true;
    }
}
