package de.zombyxl.slabcityrp.systems.housing.listener;

import de.zombyxl.slabcityrp.Main;
import de.zombyxl.slabcityrp.systems.bank.api.MoneyAPI;
import de.zombyxl.slabcityrp.utils.ItemBuilder;
import de.zombyxl.slabcityrp.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Door;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

import static de.zombyxl.slabcityrp.utils.Utils.bankinv;
import static de.zombyxl.slabcityrp.utils.Utils.buyhome;

public class HousingListener implements Listener {


    public static HashMap<Player, Location> housing = new HashMap<>();


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.IRON_DOOR) {
            int x = event.getClickedBlock().getLocation().getBlockX();
            int y = event.getClickedBlock().getLocation().getBlockY();
            int z = event.getClickedBlock().getLocation().getBlockZ();
            event.getPlayer().sendMessage("Clicked on iron door at coordinates: " + x + ", " + y + ", " + z);
        }
    }




    @EventHandler
    public void onPlayerInteract2(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block != null && block.getType() == Material.DARK_OAK_DOOR && player.isSneaking()) {

            if (block.getRelative(BlockFace.DOWN).getType() == Material.DARK_OAK_DOOR) {
                block = block.getRelative(BlockFace.DOWN);
            }

            Location loc = block.getLocation();

            if (Main.housing.get(getKey(loc)+".isbuy") == null || Main.housing.get(getKey(loc)+".isbuy").equals("false")) {
                event.setCancelled(true);


                ItemStack ja = new ItemBuilder(Material.GREEN_WOOL).setName("§a§lJa").build();
                ItemStack price = new ItemBuilder(Material.PAPER).setName("§6§lPrice").addLoreLine(Main.housing.get(getKey(loc)+".price").toString()).build();
                ItemStack nein = new ItemBuilder(Material.RED_WOOL).setName("§c§lNein").build();

                ItemStack grayGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                ItemMeta meta = grayGlass.getItemMeta();
                meta.setDisplayName("§7Housing");
                grayGlass.setItemMeta(meta);

                buyhome.setItem(4, nein);
                buyhome.setItem(3, grayGlass);
                buyhome.setItem(2, price);
                buyhome.setItem(1, grayGlass);
                buyhome.setItem(0, ja);

                player.openInventory(buyhome);

                housing.put(player, loc);


                Main.housing.setLocation(getKey(loc)+".loc", loc);
                Main.housing.getOrSet(getKey(loc)+".isbuy", "false");
                Main.housing.getOrSet(getKey(loc)+".owner", "null");
                Main.housing.getOrSet(getKey(loc)+".trust", "null");
                Main.housing.getOrSet(getKey(loc)+".price", 0);
                Main.housing.getOrSet(getKey(loc)+".rent", 0);
                Main.housing.save();
            } else if (Main.housing.get(getKey(loc)+".isbuy").equals("true")){
                if (Main.housing.get(getKey(loc)+".owner").equals(player.getUniqueId().toString()) || Main.housing.get(getKey(loc)+".trust").equals(player.getUniqueId().toString())){
                    event.setCancelled(true);
                    player.sendMessage("Open Home Menu");
                } else {
                    player.sendMessage(Main.prefix+"Du hast keine Berechtigung diese Tür zu öffnen");
                    event.setCancelled(true);
                }
            }


        }
    }


    @EventHandler
    public void onInventoryClose(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if ((event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE)) {
            event.setCancelled(true);
        } else if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.GREEN_WOOL &&
                event.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lJa")) {

            event.setCancelled(true);


            Player player = (Player) event.getWhoClicked();
            Location loc = housing.get(player);

            System.out.println(loc);

            int price = (int) Main.housing.get(getKey(loc)+".price");
            int money = MoneyAPI.getMoney((Player) event.getWhoClicked());


            if (money >= price) {
                MoneyAPI.removeMoney((Player) event.getWhoClicked(), price);
                Main.housing.set(getKey(loc)+".isbuy", "true");
                Main.housing.set(getKey(loc)+".owner", event.getWhoClicked().getUniqueId().toString());
                Main.housing.save();
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().sendMessage(Main.prefix+"§a§lDu hast das Haus gekauft");
                housing.remove(player);
            } else {
                event.getWhoClicked().sendMessage(Main.prefix+"§c§lDu hast nicht genug Geld");
                event.getWhoClicked().closeInventory();
                housing.remove(player);
                return;
            }

        } else if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.PAPER &&
                event.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lPrice")) {

            event.setCancelled(true);

        } else if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.RED_WOOL &&
                event.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lNein")) {

            event.setCancelled(true);
            event.getWhoClicked().closeInventory();
            event.getWhoClicked().sendMessage(Main.prefix+"§c§lDu hast den Kauf abgebrochen");
            housing.remove(event.getWhoClicked());

        }
    }


    public String getKey(Location loc) {
        return loc.getWorld().getName() + "_" + loc.getBlockX() + "_" + loc.getBlockY() + "_" + loc.getBlockZ();
    }


    @EventHandler
    public void onPlayerInteract3(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block != null && block.getType() == Material.DARK_OAK_DOOR && !player.isSneaking()) {
            // Normalisiere die Koordinaten, um immer den unteren Teil der Tür zu verwenden
            if (block.getRelative(BlockFace.DOWN).getType() == Material.DARK_OAK_DOOR) {
                block = block.getRelative(BlockFace.DOWN);
            }

            Location loc = block.getLocation();

            if (Main.housing.get(getKey(loc) + ".isbuy") == null || Main.housing.get(getKey(loc) + ".isbuy").equals("false")) {
                event.setCancelled(true);


                Main.housing.setLocation(getKey(loc) + ".loc", loc);
                Main.housing.getOrSet(getKey(loc) + ".isbuy", "false");
                Main.housing.getOrSet(getKey(loc) + ".owner", "null");
                Main.housing.getOrSet(getKey(loc) + ".trust", "null");
                Main.housing.getOrSet(getKey(loc) + ".price", 0);
                Main.housing.getOrSet(getKey(loc) + ".rent", 0);
                Main.housing.save();
                player.sendMessage(Main.prefix + "Dieses Haus ist zu Verkauf");
            } else if (Main.housing.get(getKey(loc) + ".isbuy").equals("true")) {
                if (Main.housing.get(getKey(loc) + ".owner").equals(player.getUniqueId().toString()) || Main.housing.get(getKey(loc) + ".trust").equals(player.getUniqueId().toString())) {
                    player.sendMessage(Main.prefix + "Du hast die Tür geöffnet");
                } else {
                    player.sendMessage(Main.prefix + "Du hast keine Berechtigung diese Tür zu öffnen");
                    event.setCancelled(true);
                }
            }
        }
    }

}