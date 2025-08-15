package me.lyamray.minestead.utils.npc;

import lombok.Getter;

@Getter
public enum NpcData {

    PIETER("551eb539e94814e8742dfa81c70420087e0f59c5910c87f59a968a346d62b534",
            "<gradient:#ff0000:#ffff00>Pieter</gradient>"),
    FLEUR("47428f9d3225f9e11614b6b4ef175954e8fb89e9608e4fa542c075a6508521",
            "<gradient:#ff00ff:#00ffff>Fleur</gradient>"),
    HENK("7a8ab2267ae443e83b05e3a8d67b8b760adfaad335c9047338c0e1774a65c372",
            "<gradient:#00ff00:#0000ff>Henk</gradient>"),
    HANNA("13ec5f03557a7edaf67cc802d1f81d24e2fd534cbc5870cc3eb655764e7c3743",
            "<gradient:#ff8800:#88ff00>Hanna</gradient>"),
    DAAN("5fd27a00d8b504f932a15c25e14be39f8856a554d585f1f727b0b79c2ba6b92b",
            "<gradient:#ff0088:#00ff88>Daan</gradient>");

    private final String texture;
    private final String displayName;

    NpcData(String texture, String displayName) {
        this.texture = texture;
        this.displayName = displayName;
    }
}