package me.lyamray.minestead.player.listeners;

import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.database.load.LoadFromDatabase;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.player.data.DataCache;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

@Slf4j
public class PlayerPreLoginEvent implements Listener {

    @EventHandler
    public void playerLoginEvent(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();

        PlayerData playerData = LoadFromDatabase.getInstance().loadPlayerData(uuid);
        UUID dataUuid = playerData.getUuid();
        DataCache.getInstance().getPlayerDataCache().putIfAbsent(dataUuid, playerData);
    }
}
