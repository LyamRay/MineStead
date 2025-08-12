package me.lyamray.minestead.tutorial.managers;

import lombok.Getter;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.tutorial.dialog.TutorialDecisionDialog;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import me.lyamray.minestead.utils.money.Money;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TutorialManager {

    @Getter
    private static final TutorialManager instance = new TutorialManager();

    public void handleDialog(Player player) {
        Location location = new Location(player.getWorld(), 0, 1000, 0);
        player.teleport(location);
        player.setGravity(false);
    }

    public void stopHandlingDialog(Player player) {
        Location location = new Location(player.getWorld(), 0, 0, 0);
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
                return;
            }

            TutorialDecisionDialog.getInstance().getHasAcceptedTutorial().remove(player.getUniqueId());
            TutorialManager.getInstance().stopHandlingDialog(player);
            Money.addMoney(player, 500);
        }
    }
}
