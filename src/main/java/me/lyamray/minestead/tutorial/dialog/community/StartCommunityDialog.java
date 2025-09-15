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
        Dialog communityDialog = DialogTemplate.builder()
                .title("<gradient:#c89651:#8adb9a>Betreed de community!</gradient>")
                .body("<gradient:#b2ac9f:#abc4b9>Voltooi verschillende opdrachten en taken bij je buren om zo een goede reputatie te krijgen! Een hogere reputatie zal beloond worden!</gradient>")
                .positive("<color:#c9ffe2>Hoe werkt dit?</color>", (p, view) ->
                        CommunityDialogHandler.getInstance().handleCommunityDialog(player))
                .negative("<color:#d1c6ae>Ik weet dit al!</color>", (p, view) ->
                        EndTutorial.getInstance().tutorialEnded(player))
                .build();

        player.showDialog(communityDialog);
    }
}
