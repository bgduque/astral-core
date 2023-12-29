package org.dice.astralcore.commands.gamemodes;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;


public class S implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String s, String[] strings){

        if (cmd.getName().equalsIgnoreCase("s") && sender.hasPermission("gm.survival") && sender instanceof Player p) {
            p.setGameMode(GameMode.SURVIVAL);
            sender.sendMessage("[Core] Set to Survival");
        } else {
            Logger logger = Logger.getLogger(S.class.getName());
            logger.log(Level.WARNING, "[WARN] This is not a console command!");

        }

        return false;
    }
}
