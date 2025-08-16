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
        Location location = player.getLocation()
                .add(player.getLocation().getDirection().normalize().multiply(2));
        location.add(0, 0.3,0).toCenterLocation();

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
                MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_FLEUR_MESSAGE_3.getMessage(player), player);
                Npc fleurNpc = Npcs.getNpc("fleur" + player.getUniqueId());
                Npcs.removeNpc(fleurNpc);
                NpcLogicHandler.getInstance().handleHenkNpc(location, event.getPlayer());
            }

            case HENK -> {
                MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_HENK_MESSAGE_3.getMessage(player), player);
                Npc henkNpc = Npcs.getNpc("henk" + player.getUniqueId());
                Npcs.removeNpc(henkNpc);
                NpcLogicHandler.getInstance().handleHannaNpc(location, event.getPlayer());
            }
            case HANNA -> {
                MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_HANNA_MESSAGE_3.getMessage(player), player);
                Npc hannaNpc = Npcs.getNpc("hanna" + player.getUniqueId());
                Npcs.removeNpc(hannaNpc);
                NpcLogicHandler.getInstance().handleDaanNpc(location, event.getPlayer());
            }

            case DAAN -> {
                MiniMessage.sendMessage(CommunityTutorialMessages.COMMUNITY_TUTORIAL_DAAN_MESSAGE_3.getMessage(player), player);
                Npc daanNpc = Npcs.getNpc("daan" + player.getUniqueId());
                Npcs.removeNpc(daanNpc);
                NpcLogicHandler.getInstance().done(player);
            }
        }
    }
}