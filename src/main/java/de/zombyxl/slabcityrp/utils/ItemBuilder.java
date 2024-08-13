package de.zombyxl.slabcityrp.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;
    private final List<String> lore;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
    }

    public ItemBuilder setName(String name) {
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        lore.add(line);
        meta.setLore(lore);
        return this;
    }

    public ItemBuilder setEnchantment(Enchantment enchantment) {
        item.addUnsafeEnchantment(enchantment, 1);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        item.removeEnchantment(enchantment);
        return this;
    }

    public ItemBuilder clearEnchantments() {
        item.getEnchantments().keySet().forEach(item::removeEnchantment);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}
