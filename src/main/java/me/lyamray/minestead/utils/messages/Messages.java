package me.lyamray.minestead.utils.messages;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.function.Function;

@Getter
public enum Messages {

    PLAYER_FIRST_TIME_JOIN_MESSAGE(player -> ("""
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Hey, {playername}!\
             Fijn dat je voor de eerste keer joined! Volg de tutorial om je eigen Home-Stead te creëren. Veel plezier!</gradient>
            """)
            .replace("{playername}", player.getName())),

    PLAYER_JOIN_MESSAGE(player -> ("""
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Hey, {playername}!\
             Welkom terug op MineStead. Veel plezier!</gradient>
            """)
            .replace("{playername}", player.getName()));

    private final Function<Player, String> messageFunction;

    Messages(Function<Player, String> messageFunction) {
        this.messageFunction = messageFunction;
    }

    public String getMessage(Player player) {
        return messageFunction.apply(player);
    }
}