package me.lyamray.minestead.player.listeners;

import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.tutorial.dialog.TutorialDecisionDialog;
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

        try {
            PlayerData playerData = PlayerData.getInstance().getPlayerDataCache().computeIfAbsent(uuid,
                    id -> new PlayerData(id, 0, 0, false));

            String message = playerData.isTutorialFinished()
                    ? Messages.PLAYER_JOIN_MESSAGE.getMessage(player)
                    : Messages.PLAYER_FIRST_TIME_JOIN_MESSAGE.getMessage(player);

            MiniMessage.sendMessage(message, player);

            if (!playerData.isTutorialFinished()) {
                player.showDialog(TutorialDecisionDialog.createTutorialConfirmDialog());
            }

        } catch (Exception e) {
            log.warn("Error handling player join for UUID: {}", uuid, e);
        }
    }
}
