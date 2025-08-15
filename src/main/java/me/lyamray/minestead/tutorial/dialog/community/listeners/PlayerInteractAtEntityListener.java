package me.lyamray.minestead.tutorial.dialog.community.listeners;

import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import me.lyamray.minestead.tutorial.dialog.community.handlers.NpcLogicHandler;
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

        String npcTexture = npc.getData().getSkinData().getTextureValue();

        NpcData textureEnum = null;
        for (NpcData t : NpcData.values()) {
            if (t.getTexture().equals(npcTexture)) {
                textureEnum = t;
                break;
            }
        }
        Location location = npc.getData().getLocation().toCenterLocation();
        if (textureEnum == null) return;

        switch (textureEnum) {
            case PIETER -> {
                Npcs.createNpc("fleur" + player.getUniqueId(), player.getUniqueId(), location, NpcData.PIETER.getTexture(),
                        NpcData.PIETER.getDisplayName(), true);
                NpcLogicHandler.getInstance().handleFleur(location, event.getPlayer());
            }

            case FLEUR -> {
                Npcs.createNpc("henk" + player.getUniqueId(), player.getUniqueId(), location, NpcData.PIETER.getTexture(),
                        NpcData.PIETER.getDisplayName(), true);
                NpcLogicHandler.getInstance().handleHenk(location, event.getPlayer());
            }

            case HENK -> {
                Npcs.createNpc("hanna" + player.getUniqueId(), player.getUniqueId(), location, NpcData.PIETER.getTexture(),
                        NpcData.PIETER.getDisplayName(), true);
                NpcLogicHandler.getInstance().handleHanna(location, event.getPlayer());
            }

            case HANNA -> {
                Npcs.createNpc("daan" + player.getUniqueId(), player.getUniqueId(), location, NpcData.PIETER.getTexture(),
                        NpcData.PIETER.getDisplayName(), true);
                NpcLogicHandler.getInstance().handleDaan(location, event.getPlayer());
            }

            case DAAN -> {
                NpcLogicHandler.getInstance().done();
            }
        }
    }
}