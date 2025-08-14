package me.lyamray.minestead.tutorial.listeners;

import me.lyamray.minestead.tutorial.dialog.TutorialDecisionDialog;
import me.lyamray.minestead.tutorial.managers.FarmingDialogManager;
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

        if (FarmingDialogManager.getInstance().getWaterLocation().containsValue(blockLoc)
                && !TutorialDecisionDialog.getInstance().getHasAcceptedTutorial().isEmpty()) {
            event.setCancelled(true);
        }
    }
}
