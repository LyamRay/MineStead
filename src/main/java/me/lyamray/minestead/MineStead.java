package me.lyamray.minestead;

import lombok.Getter;
import me.lyamray.minestead.database.Database;
import me.lyamray.minestead.database.load.LoadFromDatabase;
import me.lyamray.minestead.database.save.SaveToDatabase;
import me.lyamray.minestead.player.listeners.PlayerJoinListener;
import me.lyamray.minestead.player.listeners.PlayerPreLoginListener;
import me.lyamray.minestead.tutorial.listeners.CustomClickEvent;
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
        SaveToDatabase.getInstance().saveAllPlayerData();
        SaveToDatabase.getInstance().saveAllAnimalData();
    }

    private void registerListeners() {
        Arrays.asList(
                new PlayerPreLoginListener(),
                new PlayerJoinListener(),
                new CustomClickEvent()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        //getCommand("test").setExecutor(new //executer class);
    }
}
