package de.zombyxl.slabcityrp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Utils {


    public static Location flugzeug = new Location(Bukkit.getWorld("world"), 508, 145, -314, 90, 0);
    public static Location registeramt = new Location(Bukkit.getWorld("world"), 525, 145, -314, 90, 0);
    public static Location bank = new Location(Bukkit.getWorld("world"), 396, 74, -337, 0, 3);


    public static Inventory atm = Bukkit.createInventory(null, InventoryType.HOPPER, "§6§lATM");
    public static Inventory bankinv = Bukkit.createInventory(null, InventoryType.HOPPER, "§6§lBank");
    public static Inventory bankservice = Bukkit.createInventory(null, InventoryType.HOPPER, "§6§lBank Service");

    public static Inventory buyhome = Bukkit.createInventory(null, InventoryType.HOPPER, "§6§lBuy Home");

}
