package me.lyamray.minestead.tutorial.dialog.shared.template;

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
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiConsumer;

@SuppressWarnings("UnstableApiUsage")
public class DialogTemplate {

    @Getter
    private static DialogTemplate instance = new DialogTemplate();

    public static class Builder {

        private String title;
        private String bodyText;
        private String positiveText;
        private String positiveTooltip = "";
        private int positiveWidth = 100;
        private BiConsumer<Player, DialogResponseView> positiveAction;
        private String negativeText;
        private String negativeTooltip = "";
        private int negativeWidth = 100;
        private BiConsumer<Player, DialogResponseView> negativeAction;

        public Builder title(String title) { this.title = title; return this; }
        public Builder body(String bodyText) { this.bodyText = bodyText; return this; }

        public Builder positive(String text, BiConsumer<Player, DialogResponseView> action) {
            this.positiveText = text;
            this.positiveAction = action;
            return this;
        }

        public Builder positive(String text, String tooltip, int width, BiConsumer<Player, DialogResponseView> action) {
            this.positiveText = text;
            this.positiveTooltip = tooltip;
            this.positiveWidth = width;
            this.positiveAction = action;
            return this;
        }

        public Builder negative(String text, BiConsumer<Player, DialogResponseView> action) {
            this.negativeText = text;
            this.negativeAction = action;
            return this;
        }

        public Builder negative(String text, String tooltip, int width, BiConsumer<Player, DialogResponseView> action) {
            this.negativeText = text;
            this.negativeTooltip = tooltip;
            this.negativeWidth = width;
            this.negativeAction = action;
            return this;
        }

        public Dialog build() {
            return Dialog.create(builder -> builder
                    .empty()
                    .base(DialogBase.builder(MiniMessage.deserializeMessage(title))
                            .canCloseWithEscape(false)
                            .body(List.of(DialogBody.plainMessage(MiniMessage.deserializeMessage(bodyText))))
                            .build()
                    )
                    .type(DialogType.confirmation(
                            ActionButton.create(
                                    MiniMessage.deserializeMessage(positiveText),
                                    MiniMessage.deserializeMessage(positiveTooltip),
                                    positiveWidth,
                                    DialogAction.customClick((view, audience) -> {
                                        if (audience instanceof Player player) {
                                            positiveAction.accept(player, view);
                                        }
                                    }, ClickCallback.Options.builder().uses(1).build())
                            ),
                            ActionButton.create(
                                    MiniMessage.deserializeMessage(negativeText),
                                    MiniMessage.deserializeMessage(negativeTooltip),
                                    negativeWidth,
                                    DialogAction.customClick((view, audience) -> {
                                        if (audience instanceof Player player) {
                                            negativeAction.accept(player, view);
                                        }
                                    }, ClickCallback.Options.builder().uses(1).build())
                            )
                    ))
            );
        }
    }

    public static Builder builder() { return new Builder(); }
}
