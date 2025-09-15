package me.lyamray.minestead;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.commands.ResetTutorialCMD;
import me.lyamray.minestead.database.Database;
import me.lyamray.minestead.database.load.LoadFromDatabase;
import me.lyamray.minestead.database.save.SaveToDatabase;
import me.lyamray.minestead.player.listeners.*;
import me.lyamray.minestead.tutorial.dialog.community.handlers.CommunityDialogHandler;
import me.lyamray.minestead.tutorial.dialog.community.listeners.PlayerInteractAtEntityListener;
import me.lyamray.minestead.tutorial.dialog.farming.handlers.FarmingDialogHandler;
import me.lyamray.minestead.tutorial.dialog.farming.listeners.BlockPhysicsListener;
import me.lyamray.minestead.tutorial.dialog.farming.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

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
        LoadFromDatabase.getInstance().loadAnimalDataAsync(() -> log.info("Alle animal-data is succesvol ingeladen!"));
    }

    @Override
    public void onDisable() {
        SaveToDatabase.getInstance().saveAllPlayerDataSync(); //Sync
        SaveToDatabase.getInstance().saveAllAnimalDataSync(); //Sync

        CommunityDialogHandler.getInstance().cleanUpAllNpcs();
        FarmingDialogHandler.getInstance().cleanUpWater();
    }

    private void registerListeners() {
        Stream.of(
                PlayerDrinkPotionListener.getInstance(),
                PlayerDropItemListener.getInstance(),
                PlayerInventoryClickListener.getInstance(),
                PlayerItemSwapListener.getInstance(),
                PlayerJoinListener.getInstance(),
                PlayerMoveListener.getInstance(),
                PlayerPreLoginListener.getInstance(),
                PlayerQuitListener.getInstance(),
                PlayerInteractAtEntityListener.getInstance(),
                PlayerInteractListener.getInstance(),
                BlockPhysicsListener.getInstance()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        getCommand("resettut").setExecutor(new ResetTutorialCMD());
    }
}
