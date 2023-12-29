package org.dice.astralcore;

import org.bukkit.plugin.java.JavaPlugin;
import org.dice.astralcore.commands.gamemodes.S;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        registerCommands();
    }

    private void registerCommands(){
        getCommand("S").setExecutor(new S());
    }
}
