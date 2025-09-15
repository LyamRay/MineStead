package me.lyamray.minestead.player.listeners;

import lombok.Getter;
import me.lyamray.minestead.tutorial.dialog.start.StartTutorial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerDrinkPotionListener implements Listener {

    @Getter
    private static final PlayerDrinkPotionListener instance = new PlayerDrinkPotionListener();

    @EventHandler
    public void onPotionDrinkEvent(PlayerItemConsumeEvent event) {
        if (StartTutorial.getInstance().getHasAcceptedTutorial().containsKey(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
