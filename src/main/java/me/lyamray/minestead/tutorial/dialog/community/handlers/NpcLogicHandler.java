package me.lyamray.minestead.tutorial.dialog.community.handlers;

import lombok.Getter;
import me.lyamray.minestead.utils.npc.NpcData;
import me.lyamray.minestead.utils.npc.Npcs;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NpcLogicHandler {

    @Getter
    private static NpcLogicHandler instance = new NpcLogicHandler();

    public void handlePieter(Location location, Player player) {
        Npcs.createNpc("pieter" + player.getUniqueId(), player.getUniqueId(), location, NpcData.PIETER.getTexture(),
                NpcData.PIETER.getDisplayName(), true);
    }

    public void handleFleur(Location location, Player player) {
    }

    public void handleHenk(Location location, Player player) {

    }

    public void handleHanna(Location location, Player player) {

    }

    public void handleDaan(Location location, Player player) {

    }

    public void done() {

    }
}
