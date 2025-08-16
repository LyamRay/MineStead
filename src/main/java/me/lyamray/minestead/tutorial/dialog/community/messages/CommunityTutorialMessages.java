package me.lyamray.minestead.tutorial.dialog.community.messages;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.function.Function;

@Getter
public enum CommunityTutorialMessages {

    // PIETER MESSAGES
    COMMUNITY_TUTORIAL_PIETER_MESSAGE_1(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Laten we beginnen met Pieter!\
             Je zal Pieter kunnen aanspreken als jij iets aan jouw HomeStead wilt veranderen!</gradient>
            """),

    COMMUNITY_TUTORIAL_PIETER_MESSAGE_2(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Begin met Pieter te praten door met hem te interacten!\
             (Klik met linker of rechter muisknop op Pieter)</gradient>
            """),

    COMMUNITY_TUTORIAL_PIETER_MESSAGE_3(player -> "\n<gray> • </gray><gradient:#ff9500:#a6ff00>Heel goed! Maak nu kennis met Fleur.</gradient>\n"),

    // FLEUR MESSAGES

    COMMUNITY_TUTORIAL_FLEUR_MESSAGE_1(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Maak kennis met Boerin Fleur!\
             Je zal Boerin Fleur kunnen aanspreken als jij nieuwe dieren wilt kopen!</gradient>
            """),

    COMMUNITY_TUTORIAL_FLEUR_MESSAGE_2(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Begin met Boerin Fleur te praten door met haar te interacten!\
             (Klik met linker of rechter muisknop op Boerin Fleur)</gradient>
            """),

    COMMUNITY_TUTORIAL_FLEUR_MESSAGE_3(player -> "\n<gray> • </gray><gradient:#ff9500:#a6ff00>Heel goed! Maak nu kennis met Henk.</gradient>\n");


    private final Function<Player, String> messageFunction;

    CommunityTutorialMessages(Function<Player, String> messageFunction) {
        this.messageFunction = messageFunction;
    }

    public String getMessage(Player player) {
        return messageFunction.apply(player);
    }
}