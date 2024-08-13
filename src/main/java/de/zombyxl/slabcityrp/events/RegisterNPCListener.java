package de.zombyxl.slabcityrp.events;

import de.zombyxl.slabcityrp.Main;
import de.zombyxl.slabcityrp.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class  RegisterNPCListener implements Listener {

    List vorname = new ArrayList();
    List nachname = new ArrayList();
    List datum = new ArrayList();

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (entity.getType() == EntityType.VILLAGER) {
            Villager villager = (Villager) entity;
            if ("Klaus".equals(villager.getCustomName())) {
                if (Main.user.get(player.getUniqueId() + ".register").equals("true")) {
                    player.sendMessage(Main.prefix + "§7Du bist bereits registriert!");
                }else{
                    player.sendMessage(Main.prefix + "§7Hallo, ich bin Klaus. Wie ist dein Vorname?");
                    vorname.add(player);
                }
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String vornamevar = null;
        String nachnamevar = null;
        String datumvar = null;

        if (vorname.contains(player)) {
            event.setCancelled(true);
            player.sendMessage(Main.prefix + "§7Danke, " + message + ". Wie ist dein Nachname?");
            vorname.remove(player);
            nachname.add(player);
            vornamevar = message;
            Main.user.set(player.getUniqueId() + ".vorname", vornamevar);
            Main.user.save();
        } else if (nachname.contains(player)) {
            event.setCancelled(true);
            player.sendMessage(Main.prefix + "§7Danke, " + Main.user.get(player.getUniqueId()+".vorname") + ". Wann hast du Geburtstag?");
            nachname.remove(player);
            datum.add(player);
            nachnamevar = message;
            Main.user.set(player.getUniqueId() + ".nachname", nachnamevar);
            Main.user.save();
        } else if (datum.contains(player)) {
            event.setCancelled(true);
            player.sendMessage(Main.prefix + "§7Danke, Du hast dich erfolgreich registriert!");
            datum.remove(player);
            datumvar = message;
            Main.user.set(player.getUniqueId() + ".datum", datumvar);
            Main.user.set(player.getUniqueId() + ".register", "true");
            Main.user.set(player.getUniqueId() + ".money", 5000);
            Main.user.set(player.getUniqueId() + ".job", "Arbeitslos");
            Main.user.save();
            ItemStack ausweis = new ItemBuilder(Material.PAPER).setName("Personalausweis").addLoreLine("Name: "+Main.user.get(player.getUniqueId()+".vorname")+" "+Main.user.get(player.getUniqueId()+".nachname")).addLoreLine("Geb.: "+Main.user.get(player.getUniqueId()+".datum")).setEnchantment(Enchantment.UNBREAKING).build();
            player.getInventory().addItem(ausweis);
            player.sendMessage(Main.prefix + "§7Du hast deinen Personalausweis erhalten und 5000$ Bargeld!");
        }
    }



}
