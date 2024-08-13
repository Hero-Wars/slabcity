package de.zombyxl.slabcityrp.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class NPCListener implements Listener {



    @EventHandler
    public void onNPCDamage(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        if (event.getEntity() instanceof Villager) {
            if (player.getInventory().getItemInMainHand().getType() != Material.END_ROD) {
                event.setCancelled(true);
            }
        }
    }
}
