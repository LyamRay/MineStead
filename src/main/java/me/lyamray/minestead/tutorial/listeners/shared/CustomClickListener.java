package me.lyamray.minestead.tutorial.listeners.shared;

import io.papermc.paper.connection.PlayerGameConnection;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.event.player.PlayerCustomClickEvent;
import lombok.Getter;
import me.lyamray.minestead.tutorial.dialog.TutorialDecisionDialog;
import me.lyamray.minestead.tutorial.dialog.TutorialDialog;
import me.lyamray.minestead.tutorial.handlers.FarmingDialogHandler;
import me.lyamray.minestead.tutorial.handlers.TutorialHandler;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

@SuppressWarnings("UnstableApiUsage")
public class CustomClickListener implements Listener {

    @Getter
    private static CustomClickListener instace = new CustomClickListener();

    @EventHandler
    public void onTutorialDecision(PlayerCustomClickEvent event) {
        Key key = event.getIdentifier();
        String keyString = key.asString();

        if (!(event.getCommonConnection() instanceof PlayerGameConnection conn)) {
            return;
        }

        Player player = conn.getPlayer();
        UUID uuid = player.getUniqueId();

        switch (keyString) {
            case "minestead:tutorial-accepting/yes" -> {
                closeDialog(player);
                TutorialHandler.getInstance().handleDialog(player);
                TutorialDecisionDialog.getInstance().getHasAcceptedTutorial().putIfAbsent(uuid, true);
                startFarmingTutorial(player);
            }

            case "minestead:tutorial-accepting/no" -> {
                closeDialog(player);
                TutorialHandler.getInstance().tutorialEnded(player);
                MiniMessage.sendMessage(Messages.TUTORIAL_SKIPPED.getMessage(player), player);
            }

            case "minestead:farming-accepting/yes" -> {
                closeDialog(player);
                FarmingDialogHandler.getInstance().handleFarmingDialog(player);
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
                MiniMessage.sendMessage(Messages.TUTORIAL_COMPLETED.getMessage(player), player);
                TutorialHandler.getInstance().tutorialEnded(player);
            }

            default -> {
                // Ignore other clicks
            }
        }
    }

    private void closeDialog(Player player) {
        player.closeDialog();
    }

    public void handleFarmingTutorial(Player player) {
        startCommunityQuestTutorial(player);
        //handle directly next tutorial (community)
    }

    private void startFarmingTutorial(Player player) {
        Dialog farmingDialog = TutorialDialog.getInstance().createDialog(
                "<gradient:#c89651:#8adb9a>Start jouw eigen moestuin!</gradient>",
                "<gradient:#b2ac9f:#abc4b9>Het beheren, verzorgen en onderhouden van jouw moestuin is " +
                        "de sleutel van een goede homestead.</gradient>",
                "<color:#c9ffe2>Toon me hoe!</color>",
                "<color:#84968d>Klik hier om te leren hoe je moet planten, water geven " +
                        "en je planten te oogsten!</color>",
                Key.key("minestead:farming-accepting/yes"),
                "<color:#d1c6ae>Ik weet dit al!</color>",
                "<color:#9c978e>Klik hier om dit stukje van de tutorial over te slaan!</color>",
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
                "<color:#84968d>Klik hier om uitleg te krijgen over opdrachten en taken!</color>",
                Key.key("minestead:community-accepting/yes"),
                "<color:#d1c6ae>Ik weet dit al!</color>",
                "<color:#9c978e>Klik hier om dit stukje van de tutorial over te slaan!</color>",
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
                "<color:#84968d>Klik hier om te leren hoe je dieren moet kopen, onderhouden, " +
                        "slachten of breeden!</color>",
                Key.key("minestead:animals-accepting/yes"),
                "<color:#d1c6ae>Ik weet dit al!</color>",
                "<color:#9c978e>Klik hier om dit stukje van de tutorial over te slaan en " +
                        "de tutorial te beëindigen!</color>",
                Key.key("minestead:animals-accepting/no")
        );
        player.showDialog(animalDialog);
    }
}
