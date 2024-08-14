package de.zombyxl.slabcityrp.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;


public class NPCListener implements Listener {


    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Villager) {
            Villager npc = (Villager) event.getEntity();
            if (npc.getCustomName() != null) {
                if (event.getDamager() instanceof Player) {
                    Player player = (Player) event.getDamager();
                    ItemStack itemInHand = player.getInventory().getItemInMainHand();
                    if (itemInHand != null && itemInHand.getType() == Material.END_ROD) {
                        npc.setInvulnerable(false); // Macht den NPC verwundbar
                    } else {
                        event.setCancelled(true); // Verhindert den Schaden
                    }
                } else {
                    event.setCancelled(true); // Verhindert den Schaden
                }
            }
        }
    }
}