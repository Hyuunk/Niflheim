package fr.hyu.niflheim.gui;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import java.util.Arrays;
import org.bukkit.inventory.ItemStack;

public class GuiItem
{
    public static ItemStack createGuiItem(final ItemStack item, final String name, final String... lore) {
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore((List)Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createHeadItem(final Material material, final Player playername, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1, (short)3);
        final SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setDisplayName(name);
        meta.setOwningPlayer((OfflinePlayer)playername);
        meta.setLore((List)Arrays.asList(lore));
        item.setItemMeta((ItemMeta)meta);
        return item;
    }
}