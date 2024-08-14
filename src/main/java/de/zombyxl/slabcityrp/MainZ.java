package de.zombyxl.slabcityrp;

import de.zombyxl.slabcityrp.events.PlayerInvListener;
import de.zombyxl.slabcityrp.systems.bank.commands.AtmCommand;
import de.zombyxl.slabcityrp.systems.bank.commands.MoneyCommand;
import de.zombyxl.slabcityrp.utils.NPCManager;
import org.bukkit.Bukkit;

import static de.zombyxl.slabcityrp.utils.Utils.bank;
import static de.zombyxl.slabcityrp.utils.Utils.registeramt;

public class MainZ {

    public Main plugin;

    public MainZ(Main plugin) {
        this.plugin = plugin;
        onEnable();
    }


    public void onEnable() {


        NPCManager.createNPC(registeramt, "Klaus");
        NPCManager.createNPC(bank, "Dario");

        plugin.getCommand("money").setExecutor(new MoneyCommand(plugin));
        plugin.getCommand("atm").setExecutor(new AtmCommand(plugin));

        Bukkit.getPluginManager().registerEvents(new PlayerInvListener(plugin), plugin);

    }

}
