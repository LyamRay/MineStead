package me.lyamray.minestead.tutorial;

import io.papermc.paper.dialog.Dialog;
import lombok.Getter;
import me.lyamray.minestead.tutorial.dialog.end.EndTutorial;
import me.lyamray.minestead.tutorial.dialog.shared.template.DialogTemplate;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class GonnaBeDeletedLater implements Listener {

    @Getter
    private static GonnaBeDeletedLater instance = new GonnaBeDeletedLater();

    private void startAnimalTutorial(Player player) {
        Dialog animalDialog = DialogTemplate.builder()
                .title("<gradient:#c89651:#8adb9a>Onderhoud jouw eigen dieren!</gradient>")
                .body("<gradient:#b2ac9f:#abc4b9>Het beheren, verzorgen en onderhouden van jouw dieren is de sleutel van een goede homestead!</gradient>")
                .positive("<color:#c9ffe2>Toon me hoe!</color>", (p, view) ->
                        EndTutorial.getInstance().tutorialEnded(player))
                .negative("<color:#d1c6ae>Ik weet dit al!</color>", (p, view) ->
                        EndTutorial.getInstance().tutorialEnded(player))
                .build();

        player.showDialog(animalDialog);
    }
}
