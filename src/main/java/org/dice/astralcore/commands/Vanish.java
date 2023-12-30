package org.dice.astralcore.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Vanish implements CommandExecutor {

    private final Set<Player> vanishedPlayers = new HashSet<>();
    private final Plugin plugin;

    public Vanish(Plugin plugin) {
        this.plugin = plugin;
    }

    public Set<Player> getVanishedPlayers() {
        return vanishedPlayers;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            System.out.println("This is not a console command!");
        }

        Player p = (Player) commandSender;

        if (!p.hasPermission("v.use")) { return false; }

        if (vanishedPlayers.contains(p)) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.showPlayer(plugin, p);
            }

            vanishedPlayers.remove(p);
            p.playerListName(Component.text(p.getName()));
            p.sendMessage("Visible");

        } else {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (!onlinePlayer.hasPermission("v.see")) {
                    onlinePlayer.hidePlayer(plugin, p);
                }
            }

            vanishedPlayers.add(p);
            p.playerListName(Component.text("[V]", NamedTextColor.GRAY).append(Component.text(p.getName())));
            p.sendMessage("Invisible!");
        }

        return true;
    }
}
