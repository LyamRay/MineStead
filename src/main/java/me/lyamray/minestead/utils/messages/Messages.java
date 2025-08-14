package me.lyamray.minestead.utils.messages;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.function.Function;

@Getter
public enum Messages {

    //PlayerJoin Messages

    PLAYER_FIRST_TIME_JOIN_MESSAGE(player -> ("<gray> • </gray><gradient:#ff9500:#a6ff00>Hey, {playername}!" +
            " Fijn dat je voor de eerste keer joined! Volg de tutorial om je eigen Home-Stead te creëren. Veel plezier!</gradient>")
            .replace("{playername}", player.getName())),

    PLAYER_JOIN_MESSAGE(player -> ("<gray> • </gray><gradient:#ff9500:#a6ff00>Hey, {playername}!" +
            " Welkom terug op MineStead. Veel plezier!</gradient>")
            .replace("{playername}", player.getName())),


    //Tutorial Messages

    TUTORIAL_SKIPPED(player -> ("<gray> • </gray><gradient:#ff9500:#a6ff00>Jammer, {playername}!" +
            " Je hebt de tutorial geskipped, maar wel je geld automatisch gekregen. Veel plezier!</gradient>")
            .replace("{playername}", player.getName())),

    TUTORIAL_COMPLETED(player -> ("<gray> • </gray><gradient:#ff9500:#a6ff00>Goedzo, {playername}!" +
            " Je hebt de tutorial voltooid, je hebt je geld automatisch gekregen. Veel plezier!</gradient>")
            .replace("{playername}", player.getName())),

    TUTORIAL_USER_DOES_NOT_EXIST(player -> ("<gray> • </gray><gradient:#ff9500:#a6ff00>Jammer, {playername}!" +
            " Je hebt de tutorial voltooid, alleen je account blijkt niet te bestaan! Neem contact op met een server-admin.</gradient>")
            .replace("{playername}", player.getName())),


    // FarmingTutorial Messages

    FARMING_TUTORIAL_MESSAGE_1(player -> "<gray> • </gray><gradient:#ff9500:#a6ff00>Laten we beginnen met het water." +
            " Elke plant heeft natuurlijk water nodig!</gradient>"),
    FARMING_TUTORIAL_MESSAGE_2(player -> "<gray> • </gray><gradient:#ff9500:#a6ff00>Je hebt een leeg water flesje gekregen." +
            " Vul deze op in het water!</gradient>"),
    FARMING_TUTORIAL_MESSAGE_3(player -> "<gray> • </gray><gradient:#ff9500:#a6ff00>Heel goed! We gaan nu door naar het volgende hoofstuk.</gradient>");




    private final Function<Player, String> messageFunction;

    Messages(Function<Player, String> messageFunction) {
        this.messageFunction = messageFunction;
    }

    public String getMessage(Player player) {
        return messageFunction.apply(player);
    }
}