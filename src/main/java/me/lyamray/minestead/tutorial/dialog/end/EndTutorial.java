package me.lyamray.minestead.tutorial.dialog.end;

import lombok.Getter;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.tutorial.dialog.start.StartTutorial;
import me.lyamray.minestead.tutorial.dialog.start.handlers.TutorialHandler;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import me.lyamray.minestead.utils.money.Money;
import org.bukkit.entity.Player;

public class EndTutorial {

    @Getter
    private static EndTutorial instance = new EndTutorial();

    public void tutorialEnded(Player player) {
        PlayerData playerData = PlayerData.getInstance().getPlayerDataCache().get(player.getUniqueId());

        if (playerData == null) {
            MiniMessage.sendMessage(Messages.TUTORIAL_USER_DOES_NOT_EXIST.getMessage(player), player);
            return;
        }

        int money = Money.getMoney(player);

        if (!playerData.isTutorialFinished()) {
            StartTutorial.getInstance().getHasAcceptedTutorial().remove(player.getUniqueId());
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
