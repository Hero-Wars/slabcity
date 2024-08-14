package de.zombyxl.slabcityrp.events;

import de.zombyxl.slabcityrp.Main;
import de.zombyxl.slabcityrp.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInvListener implements Listener {
    public PlayerInvListener(Main plugin) {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        fillInventory(event.getPlayer());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        fillInventory(event.getPlayer());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if ((event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) ||
                (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.PAPER)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if ((event.getOldCursor() != null && event.getOldCursor().getType() == Material.GRAY_STAINED_GLASS_PANE) ||
                (event.getOldCursor() != null && event.getOldCursor().getType() == Material.PAPER)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.GRAY_STAINED_GLASS_PANE ||
                event.getItemDrop().getItemStack().getType() == Material.PAPER) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.getDrops().removeIf(item -> item.getType() == Material.GRAY_STAINED_GLASS_PANE ||
                item.getType() == Material.PAPER);
    }


    private void fillInventory(Player player) {
        Inventory inventory = player.getInventory();
        ItemStack grayGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = grayGlass.getItemMeta();
        meta.setDisplayName("§7Dokumente");
        grayGlass.setItemMeta(meta);


        ItemStack ausweis = new ItemBuilder(Material.PAPER).setName("§6§lPersonalausweis").addLoreLine("Name: "+Main.user.get(player.getUniqueId()+".vorname")+" "+Main.user.get(player.getUniqueId()+".nachname")).addLoreLine("Geb.: "+Main.user.get(player.getUniqueId()+".datum")).setEnchantment(Enchantment.UNBREAKING).build();

        inventory.setItem(9, grayGlass);
        inventory.setItem(10, grayGlass);
        inventory.setItem(11, grayGlass);
        inventory.setItem(12, grayGlass);
        inventory.setItem(13, grayGlass);
        inventory.setItem(14, grayGlass);
        inventory.setItem(15, grayGlass);
        inventory.setItem(16, grayGlass);
        inventory.setItem(17, grayGlass);
        inventory.setItem(18, grayGlass); //Slot 1
        inventory.setItem(19, grayGlass);
        inventory.setItem(20, grayGlass); //Slot 2
        inventory.setItem(21, grayGlass);
        if (Main.user.get(player.getUniqueId()+".ausweis") == "false" || Main.user.get(player.getUniqueId()+".ausweis") == null){
            inventory.setItem(22, grayGlass);
        }else{
            inventory.setItem(22, ausweis);
        }
        inventory.setItem(23, grayGlass);
        inventory.setItem(24, grayGlass); //Slot 4
        inventory.setItem(25, grayGlass);
        inventory.setItem(26, grayGlass); //Slot 5
        inventory.setItem(27, grayGlass);
        inventory.setItem(28, grayGlass);
        inventory.setItem(29, grayGlass);
        inventory.setItem(30, grayGlass);
        inventory.setItem(31, grayGlass);
        inventory.setItem(32, grayGlass);
        inventory.setItem(33, grayGlass);
        inventory.setItem(34, grayGlass);
        inventory.setItem(35, grayGlass);
    }


}