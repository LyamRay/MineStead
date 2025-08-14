package me.lyamray.minestead.player.listeners;

import me.lyamray.minestead.tutorial.dialog.start.StartTutorial;
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

        if (StartTutorial.getInstance().getHasAcceptedTutorial().containsKey(uuid)) {
            event.setTo(event.getFrom().setDirection(event.getTo().getDirection()));
        }
    }
}
