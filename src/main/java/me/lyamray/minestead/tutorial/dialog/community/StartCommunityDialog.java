package me.lyamray.minestead.tutorial.dialog.community;

import io.papermc.paper.dialog.Dialog;
import lombok.Getter;
import me.lyamray.minestead.tutorial.dialog.community.handlers.CommunityDialogHandler;
import me.lyamray.minestead.tutorial.dialog.end.EndTutorial;
import me.lyamray.minestead.tutorial.dialog.shared.template.DialogTemplate;
import org.bukkit.entity.Player;

public class StartCommunityDialog {

    @Getter
    private static StartCommunityDialog instance = new StartCommunityDialog();

    public void startCommunityQuestTutorial(Player player) {
        Dialog communityDialog = DialogTemplate.getInstance().createDialogWithCallbacks(
                "<gradient:#c89651:#8adb9a>Betreed de community!</gradient>",
                "<gradient:#b2ac9f:#abc4b9>Voltooi verschillende opdrachten en taken bij je buren om zo een goede reputatie te krijgen! Een hogere reputatie zal beloond worden!</gradient>",
                "<color:#c9ffe2>Hoe werkt dit?</color>",
                "<color:#84968d>Klik hier om uitleg te krijgen over opdrachten en taken!</color>",
                100,
                (response, audience) -> CommunityDialogHandler.getInstance().handleCommunityDialog(player),
                "<color:#d1c6ae>Ik weet dit al!</color>",
                "<color:#9c978e>Klik hier om dit stukje van de tutorial over te slaan!</color>",
                100,
                (response, audience) -> EndTutorial.getInstance().tutorialEnded(player)
        );
        player.showDialog(communityDialog);
    }
}
