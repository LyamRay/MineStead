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
        handleNpc(location, player, "pieter", NpcData.PIETER,
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_PIETER_MESSAGE_1.getMessage(player),
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_PIETER_MESSAGE_2.getMessage(player));
    }

    public void handleFleurNpc(Location location, Player player) {
        MiniMessage.clearChat(player); // clear chat only for Fleur, if needed
        handleNpc(location, player, "fleur", NpcData.FLEUR,
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_FLEUR_MESSAGE_1.getMessage(player),
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_FLEUR_MESSAGE_2.getMessage(player));
    }

    public void handleHenkNpc(Location location, Player player) {

    }

    public void handleHannaNpc(Location location, Player player) {

    }

    public void handleDaanNpc(Location location, Player player) {

    }

    public void done() {

    }

    private void handleNpc(Location location, Player player, String npcBaseName, NpcData npcData,
                           String firstMessage, String secondMessage) {

        Npc existing = Npcs.getNpc(npcBaseName + player.getUniqueId());
        if (existing != null) {
            Npcs.removeNpc(existing);
        }

        TimerUtil.runTaskLater(() -> MiniMessage.sendMessage(firstMessage, player), 20L);

        TimerUtil.runTaskLater(() -> {
            Npcs.createNpc(npcBaseName + player.getUniqueId(), player.getUniqueId(),
                    location, npcData.getTexture(), npcData.getDisplayName(), true);
            MiniMessage.sendMessage(secondMessage, player);
        }, 40L);
    }

    private boolean npcExists(String baseName, Player player) {
        String fullName = baseName + player.getUniqueId();
        return Npcs.getNpc(fullName) != null;
    }
}
