package me.lyamray.minestead.tutorial.dialog;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import lombok.Getter;
import me.lyamray.minestead.utils.messages.MiniMessage;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class TutorialDialog {

    @Getter
    private static final TutorialDialog instance = new TutorialDialog();

    public Dialog createDialog(
            String title,
            String bodyText,
            String positiveText,
            String positiveTooltip,
            Key positiveKey,
            String negativeText,
            String negativeTooltip,
            Key negativeKey) {

        return Dialog.create(builder -> builder
                .empty()
                .base(
                        DialogBase.builder(MiniMessage.deserializeMessage(title))
                                .canCloseWithEscape(false)
                                .body(List.of(DialogBody.plainMessage(MiniMessage.deserializeMessage(bodyText))))
                                .build()
                )
                .type(DialogType.confirmation(
                        ActionButton.create(
                                MiniMessage.deserializeMessage(positiveText),
                                MiniMessage.deserializeMessage(positiveTooltip),
                                100,
                                DialogAction.customClick(positiveKey, null)
                        ),
                        ActionButton.create(
                                MiniMessage.deserializeMessage(negativeText),
                                MiniMessage.deserializeMessage(negativeTooltip),
                                100,
                                DialogAction.customClick(negativeKey, null)
                        )
                ))
        );
    }
}
