package me.lyamray.minestead.player.listeners;

import lombok.Getter;
import me.lyamray.minestead.tutorial.dialog.start.StartTutorial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerInventoryClickListener implements Listener {
    @Getter
    private static final PlayerInventoryClickListener instance = new PlayerInventoryClickListener();
    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        if (StartTutorial.getInstance().getHasAcceptedTutorial().containsKey(event.getWhoClicked().getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
