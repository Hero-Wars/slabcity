package de.zombyxl.slabcityrp.systems.housing.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class HousingListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.IRON_DOOR) {
            int x = event.getClickedBlock().getLocation().getBlockX();
            int y = event.getClickedBlock().getLocation().getBlockY();
            int z = event.getClickedBlock().getLocation().getBlockZ();
            event.getPlayer().sendMessage("Clicked on iron door at coordinates: " + x + ", " + y + ", " + z);
        }
    }
}