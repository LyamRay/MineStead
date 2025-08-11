package me.lyamray.minestead.utils.other;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;

@UtilityClass
public class MiniMessage {
    private static final net.kyori.adventure.text.minimessage.MiniMessage miniMessage = net.kyori.adventure.text.minimessage.MiniMessage.miniMessage();

    public Component deserializeMessage(String input) {
        return miniMessage.deserialize(input);
    }

    public String serializeComponent(Component component) {
        return miniMessage.serialize(component);
    }
}
