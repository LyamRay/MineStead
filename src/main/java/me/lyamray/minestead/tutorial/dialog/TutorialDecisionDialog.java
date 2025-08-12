package me.lyamray.minestead.tutorial.dialog;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import lombok.Getter;
import lombok.Setter;
import me.lyamray.minestead.utils.messages.MiniMessage;
import net.kyori.adventure.key.Key;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuppressWarnings("UnstableApiUsage")
public class TutorialDecisionDialog {

    @Getter
    private static final TutorialDecisionDialog instance = new TutorialDecisionDialog();

    private HashMap<UUID, Boolean> hasAcceptedTutorial;

    public Dialog createTutorialConfirmDialog() {
        return Dialog.create(builder -> builder
                .empty()
                .base(
                        DialogBase.builder(MiniMessage.deserializeMessage("<gradient:#c89651:#8adb9a>Welkom op MineStead!</gradient>"))
                                .canCloseWithEscape(false)
                                .body(List.of(
                                        DialogBody.plainMessage(MiniMessage.deserializeMessage(
                                                "<gradient:#b2ac9f:#abc4b9>Welkom op MineStead! Laten we starten bij de basics. " +
                                                        "Je zal leren hoe de game werkt, hoe je quests moet doen en hoe je planten/dieren zult moeten verzorgen!</gradient>"
                                        ))
                                ))
                                .build()
                )
                .type(DialogType.confirmation(
                        ActionButton.create(
                                MiniMessage.deserializeMessage("<color:#c9ffe2>Start de tutorial.</color>"),
                                MiniMessage.deserializeMessage("<gradient:#2f2e2d:#1e201f>Klik hier om de tutorial te starten!</gradient>"),
                                100,
                                DialogAction.customClick(Key.key("minestead:tutorial-accepting/yes"), null)
                        ),
                        ActionButton.create(
                                MiniMessage.deserializeMessage("<color:#b2ac9f>Skip de tutorial.</color>"),
                                MiniMessage.deserializeMessage("<gradient:#2f2e2d:#1e201f>Klik hier om de tutorial te skippen!</gradient>"),
                                100,
                                DialogAction.customClick(Key.key("minestead:tutorial-accepting/no"), null)
                        )
                ))
        );
    }
}
