package me.lyamray.minestead.tutorial.dialog.start;

import io.papermc.paper.dialog.Dialog;
import lombok.Getter;
import lombok.Setter;
import me.lyamray.minestead.tutorial.dialog.farming.StartFarmingDialog;
import me.lyamray.minestead.tutorial.dialog.start.handlers.TutorialHandler;
import me.lyamray.minestead.tutorial.dialog.template.DialogTemplate;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class StartTutorial {

    @Getter
    private static final StartTutorial instance = new StartTutorial();

    private HashMap<UUID, Boolean> hasAcceptedTutorial = new HashMap<>();

    public Dialog createStartDialog() {
        return DialogTemplate.getInstance().createDialogWithCallbacks(
                "<gradient:#c89651:#8adb9a>Welkom op MineStead!</gradient>",
                "<gradient:#b2ac9f:#abc4b9>Welkom op MineStead! Laten we starten bij de basics. " +
                        "Je zal leren hoe de game werkt, hoe je quests moet doen en hoe je planten/dieren zult moeten verzorgen!</gradient>",
                "<color:#c9ffe2>Start de tutorial.</color>",
                "<color:#84968d>Klik hier om de tutorial te starten!</color>",
                100,
                (view, audience) -> {
                    if (audience instanceof Player player) {
                        handleStartTutorial(player);
                    }
                },
                "<color:#b2ac9f>Skip de tutorial.</color>",
                "<color:#9c978e>Klik hier om de tutorial te skippen!</color>",
                100,
                (view, audience) -> {
                    if (audience instanceof Player player) {
                        handleSkipTutorial(player);
                    }
                }
        );
    }

    private void handleStartTutorial(Player player) {
        hasAcceptedTutorial.putIfAbsent(player.getUniqueId(), true);
        TutorialHandler.getInstance().handleDialog(player);
        player.closeDialog();
        StartFarmingDialog.getInstance().startFarmingTutorial(player);
    }

    private void handleSkipTutorial(Player player) {
        TutorialHandler.getInstance().tutorialEnded(player);
        MiniMessage.sendMessage(Messages.TUTORIAL_SKIPPED.getMessage(player), player);
        player.closeDialog();
    }
}