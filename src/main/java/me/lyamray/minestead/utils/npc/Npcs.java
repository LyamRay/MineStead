package me.lyamray.minestead.utils.npc;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;
import de.oliver.fancynpcs.api.NpcManager;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.UUID;

@UtilityClass
public class Npcs {

    public Npc createNpc(String name, UUID creatorId, Location location, boolean turnToPlayer, int visibleDistance,
                         EntityType entityType, boolean glowing, NamedTextColor color, String skin, String displayName, boolean saveToFile) {

        NpcData data = new NpcData(name, creatorId, location);

        data.setSkin(skin);
        data.setDisplayName(displayName);
        data.setTurnToPlayer(turnToPlayer);
        data.setVisibilityDistance(visibleDistance);
        data.setType(entityType);
        data.setGlowing(glowing);

        if (color != null) {
            data.setGlowingColor(color);
        }

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

    public void removeAllNpcsForPlayer(UUID playerUuid) {
        NpcManager manager = FancyNpcsPlugin.get().getNpcManager();
        manager.getAllNpcs().stream()
                .filter(npc -> npc.getData().getName().endsWith(playerUuid.toString()))
                .forEach(Npcs::removeNpc);
    }

    public Npc getNpc(UUID uuid) {
        NpcManager manager = FancyNpcsPlugin.get().getNpcManager();
        return manager.getNpc(String.valueOf(uuid));
    }

    public Npc getNpc(String name) {
        NpcManager manager = FancyNpcsPlugin.get().getNpcManager();
        return manager.getAllNpcs().stream()
                .filter(npc -> npc.getData().getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
