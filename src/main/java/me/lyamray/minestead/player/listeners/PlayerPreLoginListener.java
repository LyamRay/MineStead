package me.lyamray.minestead.player.listeners;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.database.load.LoadFromDatabase;
import me.lyamray.minestead.player.data.PlayerData;
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

        PlayerData playerData = LoadFromDatabase.getInstance().loadPlayerData(uuid);
        if (playerData == null) return;

        UUID dataUuid = playerData.getUuid();

        PlayerData.getInstance().getPlayerDataCache().putIfAbsent(dataUuid, playerData);
    }
}
