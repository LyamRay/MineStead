package me.lyamray.minestead.tutorial.dialog.start;

import io.papermc.paper.dialog.Dialog;
import lombok.Getter;
import lombok.Setter;
import me.lyamray.minestead.tutorial.dialog.end.EndTutorial;
import me.lyamray.minestead.tutorial.dialog.farming.StartFarmingDialog;
import me.lyamray.minestead.tutorial.dialog.shared.messages.SharedTutorialMessages;
import me.lyamray.minestead.tutorial.dialog.shared.handlers.TutorialHandler;
import me.lyamray.minestead.tutorial.dialog.shared.template.DialogTemplate;
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

    public Dialog createStartDialog(Player player) {
        return DialogTemplate.builder()
                .title("<gradient:#c89651:#8adb9a>Welkom op MineStead!</gradient>")
                .body("<gradient:#b2ac9f:#abc4b9>Welkom op MineStead! Laten we starten bij de basics. " +
                        "Je zal leren hoe de game werkt, hoe je quests moet doen en hoe je planten/dieren zult moeten verzorgen!</gradient>")
                .positive("<color:#c9ffe2>Start de tutorial.</color>", (p, view) -> handleStartTutorial(player))
                .negative("<color:#b2ac9f>Skip de tutorial.</color>", (p, view) -> handleSkipTutorial(player))
                .build();
    }

    private void handleStartTutorial(Player player) {
        hasAcceptedTutorial.putIfAbsent(player.getUniqueId(), true);
        TutorialHandler.getInstance().handleDialog(player);
        player.closeDialog();
        StartFarmingDialog.getInstance().startFarmingTutorial(player);
    }

    private void handleSkipTutorial(Player player) {
        hasAcceptedTutorial.remove(player.getUniqueId());
        player.closeDialog();
        MiniMessage.sendMessage(SharedTutorialMessages.TUTORIAL_SKIPPED.getMessage(player), player);
        EndTutorial.getInstance().tutorialEnded(player);
    }
}
