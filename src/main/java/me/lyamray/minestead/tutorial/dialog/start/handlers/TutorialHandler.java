package me.lyamray.minestead.tutorial.dialog.start.handlers;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class TutorialHandler {

    @Getter
    private static final TutorialHandler instance = new TutorialHandler();

    public void handleDialog(Player player) {
        player.getInventory().clear();
        Random random = new Random();
        double x = random.nextInt(2001) - 1000;
        double z = random.nextInt(2001) - 1000;

        double y = 300;
        float yaw = 180f;
        float pitch = 0f;

        Location location = new Location(player.getWorld(), x, y, z, yaw, pitch).toCenterLocation();
        player.teleport(location);
        player.setGravity(false);
    }

    public void stopHandlingDialog(Player player) {
        Location location = new Location(player.getWorld(), 0, -60, 0).toCenterLocation();
        player.teleport(location);
        player.setGravity(true);
    }
}
