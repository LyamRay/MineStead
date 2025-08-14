package me.lyamray.minestead.player.listeners;

import me.lyamray.minestead.tutorial.dialog.TutorialDecisionDialog;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerInventoryClickListener implements Listener {

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        if (TutorialDecisionDialog.getInstance().getHasAcceptedTutorial().containsKey(event.getWhoClicked().getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
