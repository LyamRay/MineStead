package me.lyamray.minestead;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.commands.ResetTutorialCMD;
import me.lyamray.minestead.database.Database;
import me.lyamray.minestead.database.load.LoadFromDatabase;
import me.lyamray.minestead.database.save.SaveToDatabase;
import me.lyamray.minestead.license.LicenseChecker;
import me.lyamray.minestead.player.listeners.*;
import me.lyamray.minestead.tutorial.dialog.farming.listeners.BlockPhysicsListener;
import me.lyamray.minestead.player.listeners.PlayerMoveListener;
import me.lyamray.minestead.tutorial.dialog.farming.listeners.PlayerInteractListener;
import me.lyamray.minestead.tutorial.dialog.farming.handlers.FarmingDialogHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Arrays;
@Slf4j
public final class MineStead extends JavaPlugin {

    @Getter
    private static MineStead instance;

    @Override
    public void onEnable() {
        instance = this;
        registerListeners();
        registerCommands();
        Database.getInstance().setupDatabase();
        LoadFromDatabase.getInstance().loadAnimalDataAsync(() -> {
            log.info("Finished loading animals from database.");
        });
    }

    @Override
    public void onDisable() {
        SaveToDatabase.getInstance().saveAllPlayerDataAsync();
        SaveToDatabase.getInstance().saveAllAnimalDataAsync();

        FarmingDialogHandler.getInstance().cleanUpWater();
    }

    private void registerListeners() {
        Arrays.asList(
                new PlayerPreLoginListener(),
                new PlayerJoinListener(),
                new PlayerQuitListener(),
                new PlayerMoveListener(),
                new PlayerDrinkPotionListener(),
                new PlayerDropItemListener(),
                new PlayerItemSwapListener(),
                new BlockPhysicsListener(),
                new PlayerInteractListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        getCommand("resettut").setExecutor(new ResetTutorialCMD());
    }
}
