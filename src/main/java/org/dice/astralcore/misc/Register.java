package org.dice.astralcore.misc;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.dice.astralcore.Main;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;

import static org.bukkit.Bukkit.getLogger;

public class Register {
    private final Main mainInstance;

    public Register(Main mainInstance) {
        this.mainInstance = mainInstance;
    }
    public void command(){
        Reflections reflections = new Reflections("org.dice.astralcore.commands");
        Set<Class<? extends CommandExecutor>> classes = reflections.getSubTypesOf(CommandExecutor.class);

        for (Class<? extends CommandExecutor> c : classes ) {
            try {
                CommandExecutor executor;
                String commandName = c.getSimpleName();
                PluginCommand command = Bukkit.getPluginCommand(commandName);

                try {
                    Constructor<? extends CommandExecutor> constructor = c.getDeclaredConstructor(Main.class);
                    executor = constructor.newInstance(mainInstance);
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
