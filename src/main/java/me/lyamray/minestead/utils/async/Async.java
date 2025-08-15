package me.lyamray.minestead.utils.async;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Async {
    public static void runAsync(JavaPlugin plugin, Runnable task) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
    }

    public static void runSync(JavaPlugin plugin, Runnable task) {
        Bukkit.getScheduler().runTask(plugin, task);
    }
}