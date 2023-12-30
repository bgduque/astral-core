package org.dice.astralcore.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Heal implements CommandExecutor{
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            return false;
        }

        Player p = (Player) commandSender;

        if (commandSender.hasPermission("a.heal")) {
            p.setHealth(20.0);
        } else {
            Component message = Component.text("No permission!", NamedTextColor.DARK_RED);
            p.sendMessage(message);
        }
        return true;
    }
}
