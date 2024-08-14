package de.zombyxl.slabcityrp.events;

import de.zombyxl.slabcityrp.Main;
import de.zombyxl.slabcityrp.utils.ItemBuilder;
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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static de.zombyxl.slabcityrp.utils.Utils.bankinv;

public class BankManagerListener implements Listener {


    List<Player> menu = new ArrayList<>();

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (entity.getType() == EntityType.VILLAGER) {
            Villager villager = (Villager) entity;
            if ("Dario".equals(villager.getCustomName())) {
                if (Main.user.get(player.getUniqueId() + ".iban") != null) {

                    ItemStack gotoatm = new ItemBuilder(Material.GOLD_INGOT).setName("§6§lZum ATM").build();
                    ItemStack bankservice = new ItemBuilder(Material.NETHER_STAR).setName("§6§lBank Service").build();
                    ItemStack gotoüberfall = new ItemBuilder(Material.DIAMOND).setName("§6§lÜberfallen").build();

                    ItemStack grayGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    ItemMeta meta = grayGlass.getItemMeta();
                    meta.setDisplayName("§7Bank");
                    grayGlass.setItemMeta(meta);

                    bankinv.setItem(4, gotoüberfall);
                    bankinv.setItem(3, grayGlass);
                    bankinv.setItem(2, bankservice);
                    bankinv.setItem(1, grayGlass);
                    bankinv.setItem(0, gotoatm);
                    player.openInventory(bankinv);
                } else {
                    player.sendMessage(Main.prefix + "§7Hallo, ich bin Dario. Du bist neu hier oder?\n §7Ich kann dir ein Konto erstellen, möchtest du das? §aja §7oder §cnein");
                    menu.add(player);
                }
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (menu.contains(player)){
            if (message.equalsIgnoreCase("ja")) {

                String iban = "SC" + (int) (Math.random() * 10000);

                player.sendMessage(Main.prefix + "§7Okay, ich habe dir ein Konto erstellt hier hast du deine Konto Karte.\n §7Du hast 2500$ auf deinem Konto.\n §7Deine IBAN lautet: "+iban);
                Main.user.set(player.getUniqueId() + ".iban", iban);
                Main.user.set(player.getUniqueId() + ".bank", 2500);
                Main.user.save();
                menu.remove(player);
                ItemStack bankkarte = new ItemBuilder(Material.PAPER).setName("§6§lKontokarte").addLoreLine("Iban: "+Main.user.get(player.getUniqueId()+".iban")).setEnchantment(Enchantment.UNBREAKING).build();
                player.getInventory().setItem(24,bankkarte);
                event.setCancelled(true);
            }else if (message.equalsIgnoreCase("nein")){
                player.sendMessage(Main.prefix + "§7Okay, schade. Vielleicht ein anderes Mal.");
                menu.remove(player);
                event.setCancelled(true);
            }else{
                player.sendMessage(Main.prefix + "§7Bitte schreibe §aja §7oder §cnein");
                event.setCancelled(true);
            }
        }


    }



}
