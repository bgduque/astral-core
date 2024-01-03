package org.dice.astralcore.misc;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.dice.astralcore.Main;
import org.dice.astralcore.commands.Vanish;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;

import static org.bukkit.Bukkit.getLogger;

public class Register {
    private final Main mainInstance;
    private final Vanish vanish;

    public Register(Main mainInstance, Vanish vanish) {
        this.mainInstance = mainInstance;
        this.vanish = vanish;
    }

    public void command() {
        Reflections reflections = new Reflections("org.dice.astralcore.commands");
        Set<Class<? extends CommandExecutor>> classes = reflections.getSubTypesOf(CommandExecutor.class);

        for (Class<? extends CommandExecutor> c : classes) {
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

    public void events() {
        Reflections reflections = new Reflections("org.dice.astralcore.events");
        Set<Class<? extends Listener>> classes = reflections.getSubTypesOf(Listener.class);

        PluginManager pluginManager = Bukkit.getPluginManager();

        for (Class<? extends Listener> c : classes) {
            try {
                Constructor<? extends Listener> constructor = c.getDeclaredConstructor(Main.class, Vanish.class);
                Listener listener = constructor.newInstance(mainInstance, vanish);

                pluginManager.registerEvents(listener, mainInstance);
            } catch (Exception e) {
                getLogger().severe("Failed to register event(s): " + c.getName());
                e.printStackTrace();
            }
        }
    }
}
