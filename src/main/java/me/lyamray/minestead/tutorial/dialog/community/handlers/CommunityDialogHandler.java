package me.lyamray.minestead.tutorial.dialog.community.handlers;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import lombok.Getter;
import lombok.Setter;
import me.lyamray.minestead.utils.messages.MiniMessage;
import me.lyamray.minestead.utils.npc.Npcs;
import me.lyamray.minestead.utils.timers.TimerUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class CommunityDialogHandler {

    @Getter
    private static CommunityDialogHandler instance = new CommunityDialogHandler();

    private HashMap<UUID, Npc> playerNpcs = new HashMap<>();

    public void handleCommunityDialog(Player player) {

        Location location = player.getLocation().clone();
        location.add(player.getLocation().getDirection().setY(0).normalize().multiply(2));
        location.toCenterLocation();

        NpcLogicHandler.getInstance().handlePieterNpc(location, player);
    }

    public void completed(Player player) {
        TimerUtil.runTaskLater(() -> {

        }, 20L);
        TimerUtil.runTaskLater(() -> {
            player.getInventory().clear();
            MiniMessage.clearChat(player);

        }, 60L);
    }

    public void cleanUpNpc(Player player)  {
        //Npc npc = Npcs.getNpc("" + player.getUniqueId());
        Npcs.removeNpc(playerNpcs.get(player.getUniqueId()));
        playerNpcs.remove(player.getUniqueId());
    }

    public void cleanUpAllNpcs() {
        for (Npc npc : playerNpcs.values()) {
            npc.removeForAll();

            FancyNpcsPlugin.get().getNpcManager().removeNpc(npc);
        }
        playerNpcs.clear();
    }
}
