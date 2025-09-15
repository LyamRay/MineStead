package me.lyamray.minestead.player.listeners;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.database.save.SaveToDatabase;
import me.lyamray.minestead.tutorial.dialog.community.handlers.CommunityDialogHandler;
import me.lyamray.minestead.tutorial.dialog.farming.handlers.FarmingDialogHandler;
import me.lyamray.minestead.utils.npc.Npcs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

@Slf4j
public class PlayerQuitListener implements Listener {
    @Getter
    private static final PlayerQuitListener instance = new PlayerQuitListener();
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        SaveToDatabase.getInstance().savePlayerDataAsync(uuid);

        try {
            FarmingDialogHandler.getInstance().cleanUpWater(uuid);
            Npcs.removeAllNpcsForPlayer(uuid);
            CommunityDialogHandler.getInstance().getPlayerNpcs().remove(player.getUniqueId());
        } catch (Exception e) {
            log.warn("Error cleaning up for UUID {}: {}", uuid, e.getMessage(), e);
        }
    }
}
