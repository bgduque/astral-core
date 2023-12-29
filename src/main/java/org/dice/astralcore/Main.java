package org.dice.astralcore;

import org.bukkit.plugin.java.JavaPlugin;
import org.dice.astralcore.commands.gamemodes.S;
import org.dice.astralcore.commands.gamemodes.SP;

import java.util.Objects;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        registerCommands();
    }

    private void registerCommands(){
        Objects.requireNonNull(getCommand("s")).setExecutor(new S());
        Objects.requireNonNull(getCommand("sp")).setExecutor(new SP());
    }
}
