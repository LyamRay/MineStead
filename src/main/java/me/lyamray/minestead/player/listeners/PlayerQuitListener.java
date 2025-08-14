package me.lyamray.minestead.player.listeners;

import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.database.save.SaveToDatabase;
import me.lyamray.minestead.tutorial.managers.FarmingDialogManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

@Slf4j
public class PlayerQuitListener implements Listener {

    @EventHandler
    public void playerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        try {
            SaveToDatabase.getInstance().savePlayerData(uuid);
        } catch (Exception e) {
            log.warn("There was an error trying to save the UUID {} to the database!", uuid, e);
        }
        FarmingDialogManager.getInstance().cleanUpWater(uuid);
    }
}
