package me.lyamray.minestead.player.listeners;

import lombok.Getter;
import me.lyamray.minestead.tutorial.dialog.start.StartTutorial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerItemSwapListener implements Listener {
    @Getter
    private static final PlayerItemSwapListener instance = new PlayerItemSwapListener();
    @EventHandler
    public void onItemSwapEvent(PlayerSwapHandItemsEvent event) {
        if (StartTutorial.getInstance().getHasAcceptedTutorial().containsKey(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
