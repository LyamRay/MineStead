package me.lyamray.minestead.player.listeners;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.database.load.LoadFromDatabase;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.player.data.PlayerDataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

@Slf4j
public class PlayerPreLoginListener implements Listener {

    @Getter
    private static final PlayerPreLoginListener instance = new PlayerPreLoginListener();

    @EventHandler
    public void playerLoginEvent(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();

        LoadFromDatabase.getInstance().loadPlayerDataAsync(uuid, playerData -> {
            if (playerData != null) {
                UUID dataUuid = playerData.getUuid();
                PlayerDataHandler.getInstance().addData(playerData);
            }
        });
    }
}
