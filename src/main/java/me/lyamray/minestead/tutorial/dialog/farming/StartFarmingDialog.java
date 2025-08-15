package me.lyamray.minestead.tutorial.dialog.farming;

import io.papermc.paper.dialog.Dialog;
import lombok.Getter;
import me.lyamray.minestead.tutorial.dialog.end.EndTutorial;
import me.lyamray.minestead.tutorial.dialog.farming.handlers.FarmingDialogHandler;
import me.lyamray.minestead.tutorial.dialog.template.DialogTemplate;
import org.bukkit.entity.Player;

public class StartFarmingDialog {

    @Getter
    private static StartFarmingDialog instance = new StartFarmingDialog();

    public void startFarmingTutorial(Player player) {
        Dialog farmingDialog = DialogTemplate.getInstance().createDialogWithCallbacks(
                "<gradient:#c89651:#8adb9a>Start jouw eigen moestuin!</gradient>",
                "<gradient:#b2ac9f:#abc4b9>Het beheren, verzorgen en onderhouden van jouw moestuin is de sleutel van een goede homestead.</gradient>",
                "<color:#c9ffe2>Toon me hoe!</color>",
                "<color:#84968d>Klik hier om te leren hoe je moet planten, water geven en je planten te oogsten!</color>",
                100,
                (response, audience) -> FarmingDialogHandler.getInstance().handleFarmingDialog(player),
                "<color:#d1c6ae>Ik weet dit al!</color>",
                "<color:#9c978e>Klik hier om dit stukje van de tutorial over te slaan!</color>",
                100,
                (response, audience) -> EndTutorial.getInstance().tutorialEnded(player)
        );
        player.showDialog(farmingDialog);
    }
}
