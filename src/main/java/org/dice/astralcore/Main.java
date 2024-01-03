package org.dice.astralcore;

import org.bukkit.plugin.java.JavaPlugin;
import org.dice.astralcore.commands.Vanish;
import org.dice.astralcore.misc.Register;

public class Main extends JavaPlugin {
    private Register register;
    private Vanish vanish;

    @Override
    public void onEnable(){
        vanish = new Vanish();
        register = new Register(this, vanish);
        register.command();
        register.events();
    }

    @Override
    public void onDisable() {
        getLogger().fine("[Core] Disabled!");
    }

}