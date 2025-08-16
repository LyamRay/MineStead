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
        CommunityDialogHandler.getInstance().getPlayerNpcs().remove(player.getUniqueId());
        handleNpc(location, player, "fleur", NpcData.FLEUR,
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_FLEUR_MESSAGE_1.getMessage(player),
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_FLEUR_MESSAGE_2.getMessage(player));
    }

    public void handleHenkNpc(Location location, Player player) {
        CommunityDialogHandler.getInstance().getPlayerNpcs().remove(player.getUniqueId());
        handleNpc(location, player, "henk", NpcData.HENK,
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_HENK_MESSAGE_1.getMessage(player),
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_HENK_MESSAGE_2.getMessage(player));
    }

    public void handleHannaNpc(Location location, Player player) {
        CommunityDialogHandler.getInstance().getPlayerNpcs().remove(player.getUniqueId());
        handleNpc(location, player, "hanna", NpcData.HANNA,
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_HANNA_MESSAGE_1.getMessage(player),
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_HANNA_MESSAGE_2.getMessage(player));
    }

    public void handleDaanNpc(Location location, Player player) {
        CommunityDialogHandler.getInstance().getPlayerNpcs().remove(player.getUniqueId());
        handleNpc(location, player, "daan", NpcData.DAAN,
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_DAAN_MESSAGE_1.getMessage(player),
                CommunityTutorialMessages.COMMUNITY_TUTORIAL_DAAN_MESSAGE_2.getMessage(player));
    }

    public void done(Player player) {
        MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_DAAN_MESSAGE_3.getMessage(player), player);
    }

    private void handleNpc(Location location, Player player, String npcBaseName, NpcData npcData,
                           String firstMessage, String secondMessage) {

        Npc existing = Npcs.getNpc(npcBaseName + player.getUniqueId());
        if (existing != null) {
            Npcs.removeNpc(existing);
        }

        TimerUtil.runTaskLater(() -> {
            MiniMessage.clearChat(player);
            MiniMessage.sendMessage(firstMessage, player);
        }, 40L);

        TimerUtil.runTaskLater(() -> {
            Npc npc = Npcs.createNpc(npcBaseName + player.getUniqueId(), player.getUniqueId(),
                    location, npcData.getTexture(), npcData.getDisplayName(), true);

            CommunityDialogHandler.getInstance().getPlayerNpcs().putIfAbsent(player.getUniqueId(), npc);

            MiniMessage.sendMessage(secondMessage, player);
        }, 80L);
    }

    private boolean npcExists(String baseName, Player player) {
        String fullName = baseName + player.getUniqueId();
        return Npcs.getNpc(fullName) != null;
    }
}
