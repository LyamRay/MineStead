package me.lyamray.minestead.utils.items;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

@UtilityClass
public class ItemStacks {

    public ItemStack waterFlesje(int amount) {
        String name = "Water-Flesje";
        String lore = "Dit is een gevult water-flesje!";
        return new ItemBuilder(Material.POTION, amount)
                .setName(name)
                .addLoreLine(lore)
                .setPotionType(PotionType.WATER)
                .toItemStack();
    }

    public ItemStack leegWaterFlesje(int amount) {
        String name = "Leeg Water-Flesje";
        String lore = "Dit is een leeg water-flesje!";
        return new ItemBuilder(Material.GLASS_BOTTLE, amount)
                .setName(name)
                .addLoreLine(lore)
                .setPotionType(PotionType.WATER)
                .toItemStack();
    }
}
