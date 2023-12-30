package org.dice.astralcore;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.dice.astralcore.commands.Vanish;
import org.dice.astralcore.events.VanishListener;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;

public class Main extends JavaPlugin {
    private Vanish vanishCmd;

    @Override
    public void onEnable(){
        getLogger().fine("[Core] Enabled!");
        vanishCmd = new Vanish(this);
        getServer().getPluginManager().registerEvents(new VanishListener(vanishCmd), this);
        registerCommands();
    }

    @Override
    public void onDisable() {
        getLogger().fine("[Core] Disabled!");
    }

    private void registerCommands(){
        Reflections reflections = new Reflections("org.dice.astralcore.commands");
        Set<Class<? extends CommandExecutor>> classes = reflections.getSubTypesOf(CommandExecutor.class);

        for (Class<? extends CommandExecutor> c : classes ) {
            try {
                CommandExecutor executor;
                String commandName = c.getSimpleName();
                PluginCommand command = getCommand(commandName);

                try {
                    Constructor<? extends CommandExecutor> constructor = c.getDeclaredConstructor(Main.class);
                    executor = constructor.newInstance(this);
                } catch (NoSuchMethodException e) {
                    executor = c.getDeclaredConstructor().newInstance();
                }

                if (command != null) {
                    command.setExecutor(executor);
                } else {
                    getLogger().warning("Command " + commandName + " not found in plugin.yml!");
                }
            } catch (Exception e) {
                getLogger().severe("Failed to Register command: " + c.getName());
                e.printStackTrace();
            }
        }
    }
}