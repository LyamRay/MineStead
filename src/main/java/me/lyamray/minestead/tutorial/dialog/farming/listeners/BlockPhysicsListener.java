package me.lyamray.minestead.tutorial.dialog.farming.listeners;

import lombok.Getter;
import me.lyamray.minestead.player.listeners.PlayerPreLoginListener;
import me.lyamray.minestead.tutorial.dialog.farming.handlers.FarmingDialogHandler;
import me.lyamray.minestead.tutorial.dialog.start.StartTutorial;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockPhysicsListener implements Listener {

    @Getter
    private static final BlockPhysicsListener instance = new BlockPhysicsListener();

    @EventHandler
    public void onWaterFlow(BlockFromToEvent event) {
        Block block = event.getBlock();
        Location blockLoc = block.getLocation();

        if (FarmingDialogHandler.getInstance().getWaterLocation().containsValue(blockLoc)
                && !StartTutorial.getInstance().getHasAcceptedTutorial().isEmpty()) {
            event.setCancelled(true);
        }
    }
}
