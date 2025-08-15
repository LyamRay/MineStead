package me.lyamray.minestead.utils.async;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Async {
    private static Plugin plugin;

    public static void init(Plugin pl) {
        plugin = pl;
    }

    public static void runAsync(Runnable task) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
    }

    public static void runSync(Runnable task) {
        Bukkit.getScheduler().runTask(plugin, task);
    }
}