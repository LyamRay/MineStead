package me.lyamray.minestead.tutorial.listeners;

import me.lyamray.minestead.tutorial.dialog.TutorialDecisionDialog;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (TutorialDecisionDialog.getInstance().getHasAcceptedTutorial().containsKey(uuid)) {
            event.setTo(event.getFrom().setDirection(event.getTo().getDirection()));
        }
    }
}
