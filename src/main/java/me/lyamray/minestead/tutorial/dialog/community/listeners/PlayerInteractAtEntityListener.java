package me.lyamray.minestead.tutorial.dialog.community.listeners;

import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import me.lyamray.minestead.tutorial.dialog.community.handlers.NpcLogicHandler;
import me.lyamray.minestead.utils.npc.NpcData;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerInteractAtEntityListener implements Listener {

    @EventHandler
    public void onNpcInteract(NpcInteractEvent event) {
        Npc npc = event.getNpc();

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
            case PIETER -> NpcLogicHandler.getInstance().handleFleur(location, event.getPlayer());
            case FLEUR -> NpcLogicHandler.getInstance().handleHenk(location, event.getPlayer());
            case HENK -> NpcLogicHandler.getInstance().handleHanna(location, event.getPlayer());
            case HANNA -> NpcLogicHandler.getInstance().handleDaan(location, event.getPlayer());
            case DAAN -> NpcLogicHandler.getInstance().done();
        }
    }
}