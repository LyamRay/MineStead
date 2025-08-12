package me.lyamray.minestead.tutorial.listeners;

import io.papermc.paper.connection.PlayerGameConnection;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.event.player.PlayerCustomClickEvent;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.tutorial.dialog.TutorialDialog;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import me.lyamray.minestead.utils.money.Money;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

@SuppressWarnings("UnstableApiUsage")
public class CustomClickEvent implements Listener {

    @EventHandler
    public void onTutorialDecision(PlayerCustomClickEvent event) {
        Key key = event.getIdentifier();
        String keyString = key.asString();

        if (!(event.getCommonConnection() instanceof PlayerGameConnection conn)) {
            return;
        }

        Player player = conn.getPlayer();
        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = PlayerData.getInstance().getPlayerDataCache().get(playerUuid);

        switch (keyString) {
            case "minestead:tutorial-accepting/yes" -> {
                if (playerData != null) {
                    playerData.setTutorialFinished(false);
                }
                closeDialog(player);
                startFarmingTutorial(player);
            }

            case "minestead:tutorial-accepting/no" -> {
                if (playerData != null) {
                    playerData.setTutorialFinished(true);
                }
                closeDialog(player);
                skipTutorial(player);
            }

            case "minestead:farming-accepting/yes" -> {
                closeDialog(player);

            }

            case "minestead:farming-accepting/no" -> {
                closeDialog(player);
                startCommunityQuestTutorial(player);
            }

            case "minestead:community-accepting/yes" -> {
                closeDialog(player);
            }

            case "minestead:community-accepting/no" -> {
                closeDialog(player);
                startAnimalTutorial(player);
            }

            case "minestead:animals-accepting/yes" -> {
                closeDialog(player);
            }

            case "minestead:animals-accepting/no" -> {
                closeDialog(player);
                tutorialEnded(player);
            }

            default -> {
                // Ignore other clicks
            }
        }
    }

    private void closeDialog(Player player) {
        player.closeDialog();
    }

    private void startFarmingTutorial(Player player) {
        Dialog farmingDialog = TutorialDialog.getInstance().createDialog(
                "<gradient:#c89651:#8adb9a>Start jouw eigen moestuin!</gradient>",
                "<gradient:#b2ac9f:#abc4b9>Het beheren, verzorgen en onderhouden van jouw moestuin is " +
                        "de sleutel van een goede homestead.</gradient>",
                "<color:#c9ffe2>Toon me hoe!</color>",
                "<gradient:#2f2e2d:#1e201f>Klik hier om te leren hoe je moet planten, water geven " +
                        "en je planten te oogsten!</gradient>",
                Key.key("minestead:farming-accepting/yes"),
                "<color:#b2ac9f>Ik weet dit al!</color>",
                "<gradient:#2f2e2d:#1e201f>Klik hier om dit stukje van de tutorial over te slaan!</gradient>",
                Key.key("minestead:farming-accepting/no")
        );
        player.showDialog(farmingDialog);
    }

    private void startCommunityQuestTutorial(Player player) {
        Dialog communityDialog = TutorialDialog.getInstance().createDialog(
                "<gradient:#c89651:#8adb9a>Betreed de community!</gradient>",
                "<gradient:#b2ac9f:#abc4b9>Voltooi verschillende opdrachten en taken bij je buren om zo een " +
                        "goede reputatie te krijgen! Een hogere reputatie zal beloond worden!</gradient>",
                "<color:#c9ffe2>Hoe werkt dit?</color>",
                "<gradient:#2f2e2d:#1e201f>Klik hier om uitleg te krijgen over opdrachten en taken!</gradient>",
                Key.key("minestead:community-accepting/yes"),
                "<color:#b2ac9f>Ik weet dit al!</color>",
                "<gradient:#2f2e2d:#1e201f>Klik hier om dit stukje van de tutorial over te slaan!</gradient>",
                Key.key("minestead:community-accepting/no")
        );
        player.showDialog(communityDialog);
    }

    private void startAnimalTutorial(Player player) {
        Dialog animalDialog = TutorialDialog.getInstance().createDialog(
                "<gradient:#c89651:#8adb9a>Onderhoud jouw eigen dieren!</gradient>",
                "<gradient:#b2ac9f:#abc4b9>Het beheren, verzorgen en onderhouden van jouw dieren is de sleutel " +
                        "van een goede homestead!</gradient>",
                "<color:#c9ffe2>Toon me hoe!</color>",
                "<gradient:#2f2e2d:#1e201f>Klik hier om te leren hoe je dieren moet kopen, onderhouden, " +
                        "slachten of breeden!</gradient>",
                Key.key("minestead:animals-accepting/yes"),
                "<color:#b2ac9f>Ik weet dit al!</color>",
                "<gradient:#2f2e2d:#1e201f>Klik hier om dit stukje van de tutorial over te slaan en " +
                        "de tutorial te beëindigen!</gradient>",
                Key.key("minestead:animals-accepting/no")
        );
        player.showDialog(animalDialog);
    }

    private void skipTutorial(Player player) {
        MiniMessage.sendMessage(Messages.TUTORIAL_SKIPPED.getMessage(player), player);
    }

    private void tutorialEnded(Player player) {
        PlayerData playerData = PlayerData.getInstance().getPlayerDataCache().get(player.getUniqueId());

        if (playerData == null) {
            MiniMessage.sendMessage(Messages.TUTORIAL_USER_DOES_NOT_EXIST.getMessage(player), player);
            return;
        }

        int money = Money.getMoney(player);

        if (!playerData.isTutorialFinished()) {
            playerData.setTutorialFinished(true);
            MiniMessage.sendMessage(Messages.TUTORIAL_COMPLETED.getMessage(player), player);
            if (money != 0) {
                Money.setMoney(player, 500);
                return;
            }
            Money.addMoney(player, 500);
        }
    }
}
