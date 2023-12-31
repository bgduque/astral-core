package org.dice.astralcore;

import org.bukkit.plugin.java.JavaPlugin;
import org.dice.astralcore.commands.Vanish;
import org.dice.astralcore.events.VanishListener;
import org.dice.astralcore.misc.Register;

public class Main extends JavaPlugin {
    private Vanish vanishCmd;
    private Register register;
    @Override
    public void onEnable(){
        register = new Register(this);
        getLogger().fine("[Core] Enabled!");
        vanishCmd = new Vanish(this);

        register.command();
        getServer().getPluginManager().registerEvents(new VanishListener(vanishCmd), this);
    }

    @Override
    public void onDisable() {
        getLogger().fine("[Core] Disabled!");
    }

}