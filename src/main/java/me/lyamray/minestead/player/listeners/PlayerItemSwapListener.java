package me.lyamray.minestead.player.listeners;

import me.lyamray.minestead.tutorial.dialog.TutorialDecisionDialog;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerItemSwapListener implements Listener {

    @EventHandler
    public void onItemSwapEvent(PlayerSwapHandItemsEvent event) {
        if (TutorialDecisionDialog.getInstance().getHasAcceptedTutorial().containsKey(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
