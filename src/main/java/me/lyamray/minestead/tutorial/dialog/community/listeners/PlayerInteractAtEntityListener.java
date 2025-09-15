package me.lyamray.minestead.tutorial.dialog.community.listeners;

import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import lombok.Getter;
import me.lyamray.minestead.tutorial.dialog.community.handlers.CommunityDialogHandler;
import me.lyamray.minestead.tutorial.dialog.community.handlers.NpcLogicHandler;
import me.lyamray.minestead.tutorial.dialog.community.messages.CommunityTutorialMessages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import me.lyamray.minestead.utils.npc.NpcData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerInteractAtEntityListener implements Listener {

    @Getter
    private static final PlayerInteractAtEntityListener instance = new PlayerInteractAtEntityListener();

    @EventHandler
    public void onNpcInteract(NpcInteractEvent event) {
        Npc npc = event.getNpc();
        Player player = event.getPlayer();

        String npcName = npc.getData().getName();
        String baseName = npcName.replaceFirst(player.getUniqueId().toString(), "");

        if (!npcName.endsWith(player.getUniqueId().toString())) return;

        NpcData npcEnum = switch (baseName.toLowerCase()) {
            case "pieter" -> NpcData.PIETER;
            case "fleur" -> NpcData.FLEUR;
            case "henk" -> NpcData.HENK;
            case "hanna" -> NpcData.HANNA;
            case "daan" -> NpcData.DAAN;
            default -> null;
        };

        if (npcEnum == null) return;

        Location location = player.getLocation().clone();
        location.add(player.getLocation().getDirection().setY(0).normalize().multiply(2));
        location.toCenterLocation();

        switch (npcEnum) {
            case PIETER -> {
                MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_PIETER_MESSAGE_3.getMessage(player), player);
                CommunityDialogHandler.getInstance().cleanUpNpc(player);
                NpcLogicHandler.getInstance().handleFleurNpc(location, event.getPlayer());
            }

            case FLEUR -> {
                MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_FLEUR_MESSAGE_3.getMessage(player), player);
                CommunityDialogHandler.getInstance().cleanUpNpc(player);
                NpcLogicHandler.getInstance().handleHenkNpc(location, event.getPlayer());
            }

            case HENK -> {
                MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_HENK_MESSAGE_3.getMessage(player), player);
                CommunityDialogHandler.getInstance().cleanUpNpc(player);
                NpcLogicHandler.getInstance().handleHannaNpc(location, event.getPlayer());
            }
            case HANNA -> {
                MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_HANNA_MESSAGE_3.getMessage(player), player);
                CommunityDialogHandler.getInstance().cleanUpNpc(player);
                NpcLogicHandler.getInstance().handleDaanNpc(location, event.getPlayer());
            }

            case DAAN -> {
                MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_DAAN_MESSAGE_3.getMessage(player), player);
                CommunityDialogHandler.getInstance().cleanUpNpc(player);
                NpcLogicHandler.getInstance().done(player);
            }
        }
    }
}