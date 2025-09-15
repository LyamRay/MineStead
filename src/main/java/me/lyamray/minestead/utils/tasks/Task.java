package me.lyamray.minestead.utils.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Task {
    public static void runAsync(JavaPlugin plugin, Runnable task) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
    }

    public static void runSync(JavaPlugin plugin, Runnable task) {
        Bukkit.getScheduler().runTask(plugin, task);
    }
}