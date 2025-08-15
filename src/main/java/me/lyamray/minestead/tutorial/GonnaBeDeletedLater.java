package me.lyamray.minestead.tutorial;

import io.papermc.paper.dialog.Dialog;
import lombok.Getter;
import me.lyamray.minestead.tutorial.dialog.end.EndTutorial;
import me.lyamray.minestead.tutorial.dialog.template.DialogTemplate;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

@SuppressWarnings("UnstableApiUsage")
public class GonnaBeDeletedLater implements Listener {

    @Getter
    private static GonnaBeDeletedLater instancedaanplugge = new GonnaBeDeletedLater();

    private void startCommunityQuestTutorial(Player player) {
        Dialog communityDialog = DialogTemplate.getInstance().createDialogWithCallbacks(
                "<gradient:#c89651:#8adb9a>Betreed de community!</gradient>",
                "<gradient:#b2ac9f:#abc4b9>Voltooi verschillende opdrachten en taken bij je buren om zo een goede reputatie te krijgen! Een hogere reputatie zal beloond worden!</gradient>",
                "<color:#c9ffe2>Hoe werkt dit?</color>",
                "<color:#84968d>Klik hier om uitleg te krijgen over opdrachten en taken!</color>",
                100,
                (response, audience) -> startAnimalTutorial(player),
                "<color:#d1c6ae>Ik weet dit al!</color>",
                "<color:#9c978e>Klik hier om dit stukje van de tutorial over te slaan!</color>",
                100,
                (response, audience) -> EndTutorial.getInstance().tutorialEnded(player)
        );
        player.showDialog(communityDialog);
    }

    private void startAnimalTutorial(Player player) {
        Dialog animalDialog = DialogTemplate.getInstance().createDialogWithCallbacks(
                "<gradient:#c89651:#8adb9a>Onderhoud jouw eigen dieren!</gradient>",
                "<gradient:#b2ac9f:#abc4b9>Het beheren, verzorgen en onderhouden van jouw dieren is de sleutel van een goede homestead!</gradient>",
                "<color:#c9ffe2>Toon me hoe!</color>",
                "<color:#84968d>Klik hier om te leren hoe je dieren moet kopen, onderhouden, slachten of breeden!</color>",
                100,
                (response, audience) -> EndTutorial.getInstance().tutorialEnded(player),
                "<color:#d1c6ae>Ik weet dit al!</color>",
                "<color:#9c978e>Klik hier om dit stukje van de tutorial over te slaan en de tutorial te beëindigen!</color>",
                100,
                (response, audience) -> EndTutorial.getInstance().tutorialEnded(player)
        );
        player.showDialog(animalDialog);
    }
}
