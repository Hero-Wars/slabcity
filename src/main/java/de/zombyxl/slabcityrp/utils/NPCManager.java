package de.zombyxl.slabcityrp.utils;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class NPCManager {

    public static void createNPC(Location location, String name) {
        // Überprüfen, ob ein NPC mit demselben Namen bereits existiert
        for (Villager villager : location.getWorld().getEntitiesByClass(Villager.class)) {
            if (name.equals(villager.getCustomName())) {
                System.out.println("NPC mit dem Namen " + name + " existiert bereits!");
                return; // NPC existiert bereits, also nicht erneut spawnen
            }
        }

        Villager npc = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        npc.setCustomName(name);
        npc.setCustomNameVisible(true);
        npc.setAI(false); // Deaktiviert die KI des NPCs
        npc.setInvulnerable(true); // Macht den NPC unzerstörbar
    }
}