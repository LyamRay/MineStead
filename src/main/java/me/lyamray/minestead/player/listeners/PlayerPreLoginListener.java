package me.lyamray.minestead.player.listeners;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.event.connection.configuration.AsyncPlayerConnectionConfigureEvent;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.database.load.LoadFromDatabase;
import me.lyamray.minestead.player.data.PlayerData;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.jspecify.annotations.NullMarked;

import java.util.UUID;

@Slf4j
@SuppressWarnings("UnstableApiUsage")
@NullMarked
public class PlayerPreLoginListener implements Listener {

    @Getter
    private static final PlayerPreLoginListener instance = new PlayerPreLoginListener();


    @EventHandler
    public void playerLoginEvent(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();

        PlayerData playerData = LoadFromDatabase.getInstance().loadPlayerData(uuid);
        if (playerData == null) return;

        UUID dataUuid = playerData.getUuid();

        PlayerData.getInstance().getPlayerDataCache().putIfAbsent(dataUuid, playerData);
    }

    @EventHandler
    public void onPlayerConfigure(AsyncPlayerConnectionConfigureEvent event) {
        Dialog dialog = RegistryAccess.registryAccess().getRegistry(RegistryKey.DIALOG).get(Key.key("homestead:tutorial_confirm"));
        if (dialog == null) {
            event.getConnection().disconnect(Component.text("Tutorial dialog not found!", NamedTextColor.RED));
            return;
        }

        PlayerData playerData = PlayerData.getInstance().getPlayerDataCache().get(event.getConnection().getProfile().getId());

        if (playerData == null || playerData.isTutorialFinished()) return;

        event.getConnection().getAudience().showDialog(dialog);

    }
}
