package me.lyamray.minestead.utils.messages;

import io.papermc.paper.connection.PlayerConfigurationConnection;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

@UtilityClass
@SuppressWarnings("UnstableApiUsage")
public class MiniMessage {
    private static final net.kyori.adventure.text.minimessage.MiniMessage miniMessage = net.kyori.adventure.text.minimessage.MiniMessage.miniMessage();

    public Component deserializeMessage(String input) {
        return miniMessage.deserialize(input);
    }

    public void sendMessage(String input, Player player) {
         player.sendRichMessage(input);
    }

    public String serializeComponent(Component component) {
        return miniMessage.serialize(component);
    }

    public void clearChat(Player player) {
        PlayerConfigurationConnection connection = (PlayerConfigurationConnection) player.getConnection();
        connection.clearChat();
    }
}
