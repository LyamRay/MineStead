package me.lyamray.minestead.tutorial.listeners;

import io.papermc.paper.connection.PlayerGameConnection;
import io.papermc.paper.event.player.PlayerCustomClickEvent;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

@SuppressWarnings("UnstableApiUsage")
public class CustomClickEvent implements Listener {

    @EventHandler
    public void onTutorialDecision(PlayerCustomClickEvent event) {
        Key key = event.getIdentifier();
        String keyString = key.asString();

        if (!(event.getCommonConnection() instanceof PlayerGameConnection conn)) {
            return;
        }

        Player player = conn.getPlayer();
        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = PlayerData.getInstance().getPlayerDataCache().get(playerUuid);

        switch (keyString) {
            case "homestead:tutorial-accepting/yes" -> {
                if (playerData != null) {
                    playerData.setTutorialFinished(true);
                }
                startTutorial(player);
            }

            case "homestead:tutorial-accepting/no" -> {
                if (playerData != null) {
                    playerData.setTutorialFinished(true);
                    PlayerData.getInstance().getPlayerDataCache().replace(playerUuid, playerData);
                }
                skipTutorial(player);
            }

            default -> {
                // Ignore other clicks
            }
        }
    }

    private void startTutorial(Player player) {
        MiniMessage.sendMessage(Messages.TUTORIAL_COMPLETED.getMessage(player), player);
        player.closeDialog();
    }

    private void skipTutorial(Player player) {
        MiniMessage.sendMessage(Messages.TUTORIAL_SKIPPED.getMessage(player), player);
        player.closeDialog();
    }
}
