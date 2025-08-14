package me.lyamray.minestead.tutorial.dialog.start.handlers;

import lombok.Getter;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.tutorial.dialog.start.StartTutorial;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import me.lyamray.minestead.utils.money.Money;
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
        Location location = new Location(player.getWorld(), 0, 0, 0).toCenterLocation();
        player.teleport(location);
        player.setGravity(true);
    }

    public void tutorialEnded(Player player) {
        PlayerData playerData = PlayerData.getInstance().getPlayerDataCache().get(player.getUniqueId());

        if (playerData == null) {
            MiniMessage.sendMessage(Messages.TUTORIAL_USER_DOES_NOT_EXIST.getMessage(player), player);
            return;
        }

        int money = Money.getMoney(player);

        if (!playerData.isTutorialFinished()) {
            playerData.setTutorialFinished(true);
            if (money != 0) {
                Money.setMoney(player, 500);
                TutorialHandler.getInstance().stopHandlingDialog(player);
                return;
            }

            MiniMessage.sendMessage(Messages.TUTORIAL_COMPLETED.getMessage(player), player);
            StartTutorial.getInstance().getHasAcceptedTutorial().remove(player.getUniqueId());
            TutorialHandler.getInstance().stopHandlingDialog(player);
            Money.addMoney(player, 500);
        }
    }
}
