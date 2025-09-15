package me.lyamray.minestead.player.listeners;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.player.data.PlayerDataHandler;
import me.lyamray.minestead.tutorial.dialog.start.StartTutorial;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

@Slf4j
public class PlayerJoinListener implements Listener {
    @Getter
    private static final PlayerJoinListener instance = new PlayerJoinListener();
    @EventHandler
    public void playerJoins(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        event.joinMessage(Component.text(""));
        player.setGameMode(GameMode.ADVENTURE);

        try {
            PlayerData playerData = PlayerDataHandler.getInstance().getData(uuid);

            String message = playerData.isTutorialFinished()
                    ? Messages.PLAYER_JOIN_MESSAGE.getMessage(player)
                    : Messages.PLAYER_FIRST_TIME_JOIN_MESSAGE.getMessage(player);

            MiniMessage.sendMessage(message, player);

            if (!playerData.isTutorialFinished()) {
                player.getInventory().clear();
                player.showDialog(StartTutorial.getInstance().createStartDialog(player));
            }

        } catch (Exception e) {
            log.warn("Error handling player join for UUID: {}", uuid, e);
        }
    }
}
