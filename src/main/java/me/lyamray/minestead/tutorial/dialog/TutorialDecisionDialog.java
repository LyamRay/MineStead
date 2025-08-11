package me.lyamray.minestead.tutorial.dialog;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.DialogKeys;
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

public class TutorialDecisionDialog implements PluginBootstrap {

    @Getter
    private static final TutorialDecisionDialog instance = new TutorialDecisionDialog();

    private HashMap<UUID, Boolean> hasAcceptedTutorial;

    public void bootstrap(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(RegistryEvents.DIALOG.compose(),
                e -> e.registry().register(
                        DialogKeys.create(Key.key("homestead:tutorial_confirm")),
                        builder -> builder
                                .base(DialogBase.builder(Component.text("Start Tutorial?").color(NamedTextColor.GREEN))
                                        .canCloseWithEscape(false)
                                        .body(List.of(DialogBody.plainMessage(Component.text("Wil je de HomeStead tutorial starten?"))))
                                        .build()
                                )

                                .type(DialogType.confirmation(
                                        ActionButton.builder(Component.text("Ja").color(NamedTextColor.GREEN))
                                                .tooltip(Component.text("Klik hier om de tutorial te starten!").color(NamedTextColor.DARK_GRAY))
                                                .action(DialogAction.customClick(Key.key("homestead:tutorial-accepting/yes"), null))
                                                .build(),

                                        ActionButton.builder(Component.text("Nee").color(NamedTextColor.RED))
                                                .tooltip(Component.text("Klik hier om de tutorial te weigeren!").color(NamedTextColor.GRAY))
                                                .action(DialogAction.customClick(Key.key("homestead:tutorial-accepting/no"), null))
                                                .build()
                                ))
                ));
    }
}
