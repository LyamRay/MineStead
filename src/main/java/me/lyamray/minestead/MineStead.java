package me.lyamray.minestead;

import lombok.Getter;
import me.lyamray.minestead.database.Database;
import me.lyamray.minestead.database.load.LoadFromDatabase;
import me.lyamray.minestead.player.listeners.PlayerLoginEvent;
import me.lyamray.minestead.player.listeners.PlayerPreLoginEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class MineStead extends JavaPlugin {

    @Getter
    private static MineStead instance;

    @Override
    public void onEnable() {
        instance = this;
        registerListeners();
        registerCommands();
        Database.getInstance().setupDatabase();
        LoadFromDatabase.getInstance().loadAnimalData();
    }

    @Override
    public void onDisable() {

    }

    private void registerListeners() {
        Arrays.asList(
                new PlayerPreLoginEvent(),
                new PlayerLoginEvent()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        //getCommand("test").setExecutor(new //executer class);
    }
}
