package me.lyamray.minestead.tutorial.dialog.farming.handlers;

import lombok.Getter;
import lombok.Setter;
import me.lyamray.minestead.utils.items.ItemStacks;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import me.lyamray.minestead.utils.timers.TimerUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class FarmingDialogHandler {

    @Getter
    private static final FarmingDialogHandler instance = new FarmingDialogHandler();

    private HashMap<UUID, Location> waterLocation = new HashMap<>();

    public void handleFarmingDialog(Player player) {
        Location location = player.getLocation()
                .add(player.getLocation().getDirection().normalize().multiply(2));

        Block targetBlock = location.getBlock();
        Location blockLocation = targetBlock.getLocation();
        waterLocation.putIfAbsent(player.getUniqueId(), blockLocation);

        targetBlock.setType(Material.WATER);

        TimerUtil.runTaskLater(() -> {
            MiniMessage.sendMessage(Messages.FARMING_TUTORIAL_MESSAGE_1.getMessage(player), player);
        }, 20L);

        TimerUtil.runTaskLater(() -> {
            player.getInventory().setItem(4, ItemStacks.leegWaterFlesje(1));
            player.getInventory().setHeldItemSlot(4);
            MiniMessage.sendMessage(Messages.FARMING_TUTORIAL_MESSAGE_2.getMessage(player), player);
        }, 40L);
    }

    public void completed(Player player) {
        TimerUtil.runTaskLater(() -> {
            MiniMessage.sendMessage(Messages.FARMING_TUTORIAL_MESSAGE_3.getMessage(player), player);
        }, 20L);
        TimerUtil.runTaskLater(() -> {
            player.getInventory().clear();
        }, 60L);
    }

    public void cleanUpWater() {
        for (Location loc : waterLocation.values()) {
            if (loc.getBlock().getType() == Material.WATER) {
                loc.getBlock().setType(Material.AIR);
            }
        }
        waterLocation.clear();
    }

    public void cleanUpWater(UUID playerUUID) {
        Location loc = waterLocation.get(playerUUID);
        if (loc != null && loc.getBlock().getType() == Material.WATER) {
            loc.getBlock().setType(Material.AIR);
            waterLocation.remove(playerUUID);
        }
    }
}
