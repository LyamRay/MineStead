package me.lyamray.minestead.tutorial.listeners;

import me.lyamray.minestead.tutorial.managers.FarmingDialogManager;
import me.lyamray.minestead.utils.items.ItemStacks;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TutorialWaterListener implements Listener {

    @EventHandler
    public void onBottleFill(PlayerInteractEvent event) {
        if (!isRightClickBlock(event)) return;

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Block clickedBlock = event.getClickedBlock();

        if (!isValidItem(item) || !isTutorialWater(clickedBlock)) return;

        fillBottle(player);
        event.setCancelled(true);
    }

    private boolean isRightClickBlock(PlayerInteractEvent event) {
        return event.getAction() == Action.RIGHT_CLICK_BLOCK;
    }

    private boolean isValidItem(ItemStack item) {
        if (item == null) return false;
        ItemStack template = ItemStacks.leegWaterFlesje(1);
        return item.isSimilar(template);
    }

    private boolean isTutorialWater(Block block) {
        return block != null &&
                FarmingDialogManager.getInstance().getWaterLocation().containsValue(block.getLocation());
    }

    private void fillBottle(Player player) {
        player.getInventory().remove(ItemStacks.leegWaterFlesje(1));
        player.getInventory().setItem(4, ItemStacks.waterFlesje(1));
        FarmingDialogManager.getInstance().tutorialCompleted(player);
    }
}