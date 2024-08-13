package de.zombyxl.slabcityrp;

import de.zombyxl.slabcityrp.systems.bank.commands.AtmCommand;
import de.zombyxl.slabcityrp.systems.bank.commands.MoneyCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class MainZ {

    public Main plugin;

    public MainZ(Main plugin) {
        this.plugin = plugin;
        onEnable();
    }

    public static Inventory atm = Bukkit.createInventory(null, InventoryType.HOPPER, "§6§lATM");

    public void onEnable() {

        plugin.getCommand("money").setExecutor(new MoneyCommand(plugin));
        plugin.getCommand("atm").setExecutor(new AtmCommand(plugin));

    }

}
