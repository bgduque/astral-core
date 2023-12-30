package org.dice.astralcore;

import org.bukkit.plugin.java.JavaPlugin;
import org.dice.astralcore.commands.Fly;
import org.dice.astralcore.commands.Heal;
import org.dice.astralcore.commands.Vanish;
import org.dice.astralcore.commands.gamemodes.C;
import org.dice.astralcore.commands.gamemodes.S;
import org.dice.astralcore.commands.gamemodes.SP;
import org.dice.astralcore.events.VanishListener;

import java.util.Objects;

public class Main extends JavaPlugin {
    private Vanish vanishCmd;

    @Override
    public void onEnable(){
        vanishCmd = new Vanish(this);
        getServer().getPluginManager().registerEvents(new VanishListener(vanishCmd), this);
        registerCommands();
    }

    private void registerCommands(){
        Objects.requireNonNull(getCommand("v")).setExecutor(vanishCmd);
        Objects.requireNonNull(getCommand("fly")).setExecutor(new Fly());
        Objects.requireNonNull(getCommand("heal")).setExecutor(new Heal());
        Objects.requireNonNull(getCommand("c")).setExecutor(new C());
        Objects.requireNonNull(getCommand("s")).setExecutor(new S());
        Objects.requireNonNull(getCommand("sp")).setExecutor(new SP());


    }

}
