package me.lyamray.minestead.tutorial.dialog.template;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.dialog.DialogResponseView;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import lombok.Getter;
import me.lyamray.minestead.utils.messages.MiniMessage;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.event.ClickCallback;

import java.util.List;
import java.util.function.BiConsumer;

@SuppressWarnings("UnstableApiUsage")
public class DialogTemplate {

    @Getter
    private static final DialogTemplate instance = new DialogTemplate();

    public Dialog createDialogWithCallbacks(
            String title,
            String bodyText,
            String positiveText,
            String positiveTooltip,
            int positiveWidth,
            BiConsumer<DialogResponseView, Audience> positiveAction,
            String negativeText,
            String negativeTooltip,
            int negativeWidth,
            BiConsumer<DialogResponseView, Audience> negativeAction) {

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
                                positiveWidth,
                                DialogAction.customClick(
                                        positiveAction::accept,
                                        ClickCallback.Options.builder()
                                                .uses(1)
                                                .build()
                                )
                        ),
                        ActionButton.create(
                                MiniMessage.deserializeMessage(negativeText),
                                MiniMessage.deserializeMessage(negativeTooltip),
                                negativeWidth,
                                DialogAction.customClick(
                                        negativeAction::accept,
                                        ClickCallback.Options.builder()
                                                .uses(1)
                                                .build()
                                )
                        )
                ))
        );
    }
}