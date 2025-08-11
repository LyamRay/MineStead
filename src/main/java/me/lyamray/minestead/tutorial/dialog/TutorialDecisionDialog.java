package me.lyamray.minestead.tutorial.dialog;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

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

    public static Dialog createTutorialConfirmDialog() {
        return Dialog.create(builder -> builder
                .empty()
                .base(
                        DialogBase.builder(Component.text("Start de HomeStead tutorial!").color(NamedTextColor.GRAY))
                                .canCloseWithEscape(false)
                                .body(List.of(
                                        DialogBody.plainMessage(Component.text("Wil je de HomeStead tutorial starten?").color(NamedTextColor.GRAY))
                                ))
                                .build()
                )
                .type(DialogType.confirmation(
                        ActionButton.create(
                                Component.text("Ja").color(NamedTextColor.GRAY),
                                Component.text("Klik hier om de tutorial te starten!").color(NamedTextColor.DARK_GRAY),
                                100,
                                DialogAction.customClick(Key.key("homestead:tutorial-accepting/yes"), null)
                        ),
                        ActionButton.create(
                                Component.text("Nee").color(NamedTextColor.GRAY),
                                Component.text("Klik hier om de tutorial te weigeren!").color(NamedTextColor.DARK_GRAY),
                                100,
                                DialogAction.customClick(Key.key("homestead:tutorial-accepting/no"), null)
                        )
                ))
        );
    }
}
