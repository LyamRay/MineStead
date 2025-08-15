package me.lyamray.minestead.utils.npc;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;
import org.bukkit.Location;

import java.util.UUID;

public class Npcs {

    public Npc createNpc(String name, UUID creatorId, Location location, String skin, String displayName, boolean saveToFile) {
        NpcData data = new NpcData(name, creatorId, location);
        data.setSkin(skin);
        data.setDisplayName(displayName);

        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(data);
        npc.setSaveToFile(saveToFile);

        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);

        npc.create();
        npc.spawnForAll();

        return npc;
    }

    public void updateNpc(Npc npc, String skin, String displayName) {
        NpcData data = npc.getData();
        data.setSkin(skin);
        data.setDisplayName(displayName);

        npc.updateForAll();
    }

    public void removeNpc(Npc npc) {
        FancyNpcsPlugin.get().getNpcManager().removeNpc(npc);
        npc.removeForAll();
    }

    public void respawnNpc(Npc npc) {
        npc.removeForAll();
        npc.spawnForAll();
    }
}
