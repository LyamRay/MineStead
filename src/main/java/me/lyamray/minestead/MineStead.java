package me.lyamray.minestead;

import lombok.Getter;
import me.lyamray.minestead.commands.ResetTutorialCMD;
import me.lyamray.minestead.database.Database;
import me.lyamray.minestead.database.load.LoadFromDatabase;
import me.lyamray.minestead.database.save.SaveToDatabase;
import me.lyamray.minestead.license.LicenseChecker;
import me.lyamray.minestead.player.listeners.*;
import me.lyamray.minestead.tutorial.listeners.BlockPhysicsListener;
import me.lyamray.minestead.tutorial.listeners.CustomClickListener;
import me.lyamray.minestead.tutorial.listeners.PlayerMoveListener;
import me.lyamray.minestead.tutorial.listeners.TutorialWaterListener;
import me.lyamray.minestead.tutorial.managers.FarmingDialogManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
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

        try {
            LicenseChecker.getInstance().checkLicense();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onDisable() {
        SaveToDatabase.getInstance().saveAllPlayerData();
        SaveToDatabase.getInstance().saveAllAnimalData();
        FarmingDialogManager.getInstance().cleanUpWater();
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
                new CustomClickListener(),
                new TutorialWaterListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        getCommand("resettut").setExecutor(new ResetTutorialCMD());
    }
}
