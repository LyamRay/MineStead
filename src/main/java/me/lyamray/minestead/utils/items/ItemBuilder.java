package me.lyamray.minestead.utils.items;

import me.lyamray.minestead.utils.messages.MiniMessage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            Component displayName = MiniMessage.deserializeMessage(name).decoration(TextDecoration.ITALIC, false);
            meta.displayName(displayName);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder clearLore() {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.lore(new ArrayList<>());
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        Component lineComponent = MiniMessage.deserializeMessage(line).decoration(TextDecoration.ITALIC, false);
        return addLoreLine(lineComponent);
    }

    @SuppressWarnings("ConstantConditions")
    public ItemBuilder addLoreLine(Component line) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            List<Component> lore = meta.lore() != null ? new ArrayList<>(meta.lore()) : new ArrayList<>();
            lore.add(line);
            meta.lore(lore);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setDurability(int durability) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta instanceof Damageable damageable) {
            damageable.setDamage(durability);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemStack toItemStack() {
        return itemStack.clone();
    }
}