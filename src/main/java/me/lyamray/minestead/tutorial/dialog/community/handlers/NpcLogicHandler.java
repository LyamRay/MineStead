package me.lyamray.minestead.tutorial.dialog.community.handlers;

import de.oliver.fancynpcs.api.Npc;
import lombok.Getter;
import me.lyamray.minestead.tutorial.dialog.community.messages.CommunityTutorialMessages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import me.lyamray.minestead.utils.npc.NpcData;
import me.lyamray.minestead.utils.npc.Npcs;
import me.lyamray.minestead.utils.timers.TimerUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NpcLogicHandler {

    @Getter
    private static NpcLogicHandler instance = new NpcLogicHandler();

    public void handlePieterNpc(Location location, Player player) {
        if (npcExists("pieter", player)) {
            Npc npc = Npcs.getNpc("pieter" + player.getUniqueId());
            Npcs.removeNpc(npc);
        }

        TimerUtil.runTaskLater(() -> MiniMessage.sendMessage(
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_PIETER_MESSAGE_1.getMessage(player), player), 20L);

        TimerUtil.runTaskLater(() -> {
            Npcs.createNpc("pieter" + player.getUniqueId(), player.getUniqueId(),
                    location, NpcData.PIETER.getTexture(), NpcData.PIETER.getDisplayName(), true);
            MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_PIETER_MESSAGE_2.getMessage(player), player);
        }, 40L);
    }

    public void handleFleurNpc(Location location, Player player) {
        MiniMessage.clearChat(player);

        if (npcExists("fleur", player)) {
            Npc npc = Npcs.getNpc("fleur" + player.getUniqueId());
            Npcs.removeNpc(npc);
        }

        TimerUtil.runTaskLater(() -> MiniMessage.sendMessage(
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_FLEUR_MESSAGE_1.getMessage(player), player), 20L);

        TimerUtil.runTaskLater(() -> {
            MiniMessage.sendMessage(
                    CommunityTutorialMessages.COMMUNITY_TUTORIAL_FLEUR_MESSAGE_2.getMessage(player), player);
            Npcs.createNpc("fleur" + player.getUniqueId(), player.getUniqueId(), location, NpcData.PIETER.getTexture(),
                    NpcData.FLEUR.getDisplayName(), true);
        }, 40L);
    }

    public void handleHenkNpc(Location location, Player player) {

    }

    public void handleHannaNpc(Location location, Player player) {

    }

    public void handleDaanNpc(Location location, Player player) {

    }

    public void done() {

    }

    private boolean npcExists(String baseName, Player player) {
        String fullName = baseName + player.getUniqueId();
        return Npcs.getNpc(fullName) != null;
    }
}
