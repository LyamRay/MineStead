package me.lyamray.minestead.tutorial.dialog.farming.listeners;

import me.lyamray.minestead.tutorial.dialog.farming.handlers.FarmingDialogHandler;
import me.lyamray.minestead.tutorial.dialog.start.StartTutorial;
import me.lyamray.minestead.utils.items.ItemStacks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onBottleFill(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (!StartTutorial.getInstance().getHasAcceptedTutorial()
                .containsKey(player.getUniqueId())) return;
        if (item == null || !item.isSimilar(ItemStacks.leegWaterFlesje(1))) return;

        player.getInventory().setItem(4, ItemStacks.waterFlesje(1));
        FarmingDialogHandler.getInstance().completed(player);
    }
}