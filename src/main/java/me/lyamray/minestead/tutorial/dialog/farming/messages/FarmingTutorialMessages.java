package me.lyamray.minestead.tutorial.dialog.farming.messages;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.function.Function;

@Getter
public enum FarmingTutorialMessages {

    FARMING_TUTORIAL_MESSAGE_1(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Laten we beginnen met het water.\
             Elke plant heeft natuurlijk water nodig!</gradient>
            """),

    FARMING_TUTORIAL_MESSAGE_2(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Je hebt een leeg water flesje gekregen.\
             Vul deze op in het water!</gradient>
            """),

    FARMING_TUTORIAL_MESSAGE_3(player -> "\n<gray> • </gray><gradient:#ff9500:#a6ff00>Heel goed! We gaan nu door naar het volgende hoofdstuk.</gradient>\n");

    private final Function<Player, String> messageFunction;

    FarmingTutorialMessages(Function<Player, String> messageFunction) {
        this.messageFunction = messageFunction;
    }

    public String getMessage(Player player) {
        return messageFunction.apply(player);
    }
}