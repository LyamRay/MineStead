package me.lyamray.minestead.tutorial.listeners.farming;

import me.lyamray.minestead.tutorial.dialog.TutorialDecisionDialog;
import me.lyamray.minestead.tutorial.handlers.FarmingDialogHandler;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockPhysicsListener implements Listener {

    @EventHandler
    public void onWaterFlow(BlockFromToEvent event) {
        Block block = event.getBlock();
        Location blockLoc = block.getLocation();

        if (FarmingDialogHandler.getInstance().getWaterLocation().containsValue(blockLoc)
                && !TutorialDecisionDialog.getInstance().getHasAcceptedTutorial().isEmpty()) {
            event.setCancelled(true);
        }
    }
}
