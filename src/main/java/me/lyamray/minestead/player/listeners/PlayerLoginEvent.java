package me.lyamray.minestead.player.listeners;

import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.database.Database;
import me.lyamray.minestead.utils.messages.Messages;
import me.lyamray.minestead.utils.messages.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class PlayerLoginEvent implements Listener {

    @EventHandler
    public void playerJoins(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        String message = "";

        try {
            boolean exists = Database.getInstance().checkIfExists(
                    "players",
                    "uuid = ?",
                    uuid.toString()
            );

            if (!exists) {
                Map<String, Object> values = new HashMap<>();
                values.put("uuid", uuid.toString());
                values.put("money", 0);
                values.put("playtime", 0);
                Database.getInstance().add("players", values);

                message = Messages.PLAYER_FIRST_TIME_JOIN_MESSAGE.getMessage(player);
                log.info("Added the UUID: {} in the database!", uuid);
            } else {
                message = Messages.PLAYER_JOIN_MESSAGE.getMessage(player);
            }

        } catch (SQLException e) {
            log.warn("Error while checking/adding the player in the database! UUID: {}", uuid, e);
        }

        player.sendMessage(MiniMessage.deserializeMessage(message));
    }
}