package me.lyamray.minestead.tutorial.listeners;

import io.papermc.paper.connection.PlayerGameConnection;
import io.papermc.paper.event.player.PlayerCustomClickEvent;
import me.lyamray.minestead.player.listeners.PlayerPreLoginListener;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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

        switch (keyString) {
            case "homestead:tutorial-accepting/yes" -> {
                startTutorial(player);
            }

            case "homestead:tutorial-accepting/no" -> {
                skipTutorial(player);
            }
            default -> {
                // Ignore other dialog clicks
            }
        }
    }

    private void startTutorial(Player player) {
    }

    private void skipTutorial(Player player) {

        MiniMessage.sendMessage(Messages.TUTORIAL_SKIPPED.getMessage(player), player);
        player.closeDialog();
    }
}
