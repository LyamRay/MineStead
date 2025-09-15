package me.lyamray.minestead.utils.npc;

import lombok.Getter;

@Getter
public enum NpcData {

    PIETER("https://textures.minecraft.net/texture/551eb539e94814e8742dfa81c70420087e0f59c5910c87f59a968a346d62b534",
            "<gradient:#8dddd4:#b9c6c5>Pieter</gradient>"),
    FLEUR("https://textures.minecraft.net/texture/47428f9d3225f9e11614b6b4ef175954e8fb89e9608e4fa542c075a6508521",
            "<gradient:#9981b6:#b3acbe>Fleur</gradient>"),
    HENK("https://textures.minecraft.net/texture/7a8ab2267ae443e83b05e3a8d67b8b760adfaad335c9047338c0e1774a65c372",
            "<gradient:#8fc193:#91a697>Henk</gradient>"),
    HANNA("https://textures.minecraft.net/texture/13ec5f03557a7edaf67cc802d1f81d24e2fd534cbc5870cc3eb655764e7c3743",
            "<gradient:#c18f8f:#a69191>Hanna</gradient>"),
    DAAN("https://textures.minecraft.net/texture/5fd27a00d8b504f932a15c25e14be39f8856a554d585f1f727b0b79c2ba6b92b",
            "<gradient:#8f99c1:#8a8a99>Daan</gradient>");

    private final String texture;
    private final String displayName;

    NpcData(String texture, String displayName) {
        this.texture = texture;
        this.displayName = displayName;
    }
}