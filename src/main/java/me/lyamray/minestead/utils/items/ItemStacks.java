package me.lyamray.minestead.utils.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

public class ItemStacks {

    public ItemStack waterFlesje() {
        String name = "";
        String lore = "";
        return new ItemBuilder(Material.POTION, 1)
                .setName(name)
                .addLoreLine(lore)
                .setPotionType(PotionType.WATER)
                .toItemStack();
    }

}
