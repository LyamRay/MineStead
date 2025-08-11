package me.lyamray.minestead.player.listeners;

import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

@Slf4j
public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoins(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        String message;

        try {
            PlayerData playerData = PlayerData.getInstance().getPlayerDataCache().get(uuid);
            if (playerData == null) {
                playerData = new PlayerData(uuid, 0, 0, false);
                PlayerData.getInstance().getPlayerDataCache().put(uuid, playerData);

                message = Messages.PLAYER_FIRST_TIME_JOIN_MESSAGE.getMessage(player);

                log.info("Added the UUID: {} to the player data cache on join!", uuid);

            } else {
                message = Messages.PLAYER_JOIN_MESSAGE.getMessage(player);
            }

            MiniMessage.sendMessage(message, player);
        } catch (Exception e) {
            log.warn("An error occurred while handling player join for UUID: {}", uuid, e);
        }
    }
}
