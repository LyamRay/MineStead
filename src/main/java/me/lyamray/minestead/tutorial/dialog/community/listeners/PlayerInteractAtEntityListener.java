package me.lyamray.minestead.tutorial.dialog.community.listeners;

import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import me.lyamray.minestead.tutorial.dialog.community.handlers.NpcLogicHandler;
import me.lyamray.minestead.tutorial.dialog.community.messages.CommunityTutorialMessages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import me.lyamray.minestead.utils.npc.NpcData;
import me.lyamray.minestead.utils.npc.Npcs;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerInteractAtEntityListener implements Listener {

    @EventHandler
    public void onNpcInteract(NpcInteractEvent event) {
        Npc npc = event.getNpc();
        Player player = event.getPlayer();
        Location location = npc.getData().getLocation().toCenterLocation();

        String npcName = npc.getData().getName();
        String baseName = npcName.replaceFirst(player.getUniqueId().toString(), "");

        NpcData npcEnum = switch (baseName.toLowerCase()) {
            case "pieter" -> NpcData.PIETER;
            case "fleur"  -> NpcData.FLEUR;
            case "henk"   -> NpcData.HENK;
            case "hanna"  -> NpcData.HANNA;
            case "daan"   -> NpcData.DAAN;
            default -> null;
        };

        if (npcEnum == null) return;

        switch (npcEnum) {
            case PIETER -> {
                MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_PIETER_MESSAGE_3.getMessage(player), player);
                Npc pieterNpc = Npcs.getNpc("pieter" + player.getUniqueId());
                Npcs.removeNpc(pieterNpc);
                NpcLogicHandler.getInstance().handleFleurNpc(location, event.getPlayer());
            }

            case FLEUR -> {
                Npcs.createNpc("henk" + player.getUniqueId(), player.getUniqueId(), location, NpcData.PIETER.getTexture(),
                        NpcData.PIETER.getDisplayName(), true);
                NpcLogicHandler.getInstance().handleHenkNpc(location, event.getPlayer());
            }

            case HENK -> {
                Npcs.createNpc("hanna" + player.getUniqueId(), player.getUniqueId(), location, NpcData.PIETER.getTexture(),
                        NpcData.PIETER.getDisplayName(), true);
                NpcLogicHandler.getInstance().handleHannaNpc(location, event.getPlayer());
            }
            case HANNA -> {
                Npcs.createNpc("daan" + player.getUniqueId(), player.getUniqueId(), location, NpcData.PIETER.getTexture(),
                        NpcData.PIETER.getDisplayName(), true);
                NpcLogicHandler.getInstance().handleDaanNpc(location, event.getPlayer());
            }
            case DAAN -> NpcLogicHandler.getInstance().done();
        }
    }
}