package me.lyamray.minestead.tutorial.dialog.community.messages;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.function.Function;

@Getter
public enum CommunityTutorialMessages {

    // PIETER MESSAGES
    COMMUNITY_TUTORIAL_PIETER_MESSAGE_1(player -> """
            <gray> • </gray><gradient:#ff9500:#a6ff00>Laten we beginnen met Buur Pieter!\
             Je zal Pieter kunnen aanspreken als jij iets aan jouw HomeStead wilt veranderen!</gradient>
            """),

    COMMUNITY_TUTORIAL_PIETER_MESSAGE_2(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Begin met Buur Pieter te praten door met hem te interacten!\
             (Klik met linker of rechter muisknop op Pieter)</gradient>
            """),

    COMMUNITY_TUTORIAL_PIETER_MESSAGE_3(player -> "\n<gray> • </gray><gradient:#ff9500:#a6ff00>Heel goed! Maak nu kennis met Boerin Fleur.</gradient>\n"),

    // FLEUR MESSAGES

    COMMUNITY_TUTORIAL_FLEUR_MESSAGE_1(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Maak kennis met Boerin Fleur!\
             Je zal Boerin Fleur kunnen aanspreken als jij nieuwe dieren wilt kopen!</gradient>
            """),

    COMMUNITY_TUTORIAL_FLEUR_MESSAGE_2(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Begin met Boerin Fleur te praten door met haar te interacten!</gradient>
            """),

    COMMUNITY_TUTORIAL_FLEUR_MESSAGE_3(player -> "\n<gray> • </gray><gradient:#ff9500:#a6ff00>Mooizo! Maak nu kennis met Buur Henk.</gradient>\n"),

    // HENK MESSAGES

    COMMUNITY_TUTORIAL_HENK_MESSAGE_1(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Maak kennis met Henk!\
             Je zal Henk nodig hebben mocht je bepaalde materialen wilt kopen of verkopen!</gradient>
            """),

    COMMUNITY_TUTORIAL_HENK_MESSAGE_2(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Begin met Henk te praten door met haar te interacten!</gradient>
            """),

    COMMUNITY_TUTORIAL_HENK_MESSAGE_3(player -> "\n<gray> • </gray><gradient:#ff9500:#a6ff00>Heel goed! Ontdek nu wie de volgende buur is.</gradient>\n"),

    // HANNA MESSAGES

    COMMUNITY_TUTORIAL_HANNA_MESSAGE_1(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Maak kennis met Buur Hanna!\
             Je zal Buur Hanna kunnen aanspreken als jij bepaalde klusjes wilt doen. Deze zullen goed beloond worden!</gradient>
            """),

    COMMUNITY_TUTORIAL_HANNA_MESSAGE_2(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Begin met Buur Hanna te praten door met haar te interacten!</gradient>
            """),

    COMMUNITY_TUTORIAL_HANNA_MESSAGE_3(player -> "\n<gray> • </gray><gradient:#ff9500:#a6ff00>Perfect! Maak nu kennis met de laatste buur.</gradient>\n"),

    // DAAN MESSAGES

    COMMUNITY_TUTORIAL_DAAN_MESSAGE_1(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Dit is Buur Daan!\
             Bij Daan zul je vooral opdrachten en taken kunnen voltooien. Deze zullen je bits en reputatie verdienen!</gradient>
            """),

    COMMUNITY_TUTORIAL_DAAN_MESSAGE_2(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Begin met Buur Daan te praten door met haar te interacten!</gradient>
            """),

    COMMUNITY_TUTORIAL_DAAN_MESSAGE_3(player -> """
            
            <gray> • </gray><gradient:#ff9500:#a6ff00>Super! Je hebt dit hoofdstuk van de tutorial voltooid.</gradient>
            
            """);

    private final Function<Player, String> messageFunction;

    CommunityTutorialMessages(Function<Player, String> messageFunction) {
        this.messageFunction = messageFunction;
    }

    public String getMessage(Player player) {
        return messageFunction.apply(player);
    }
}