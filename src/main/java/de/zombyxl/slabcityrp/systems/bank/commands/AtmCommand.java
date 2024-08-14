package de.zombyxl.slabcityrp.systems.bank.commands;

import de.zombyxl.slabcityrp.Main;
import de.zombyxl.slabcityrp.MainZ;
import de.zombyxl.slabcityrp.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AtmCommand implements CommandExecutor {
    public AtmCommand(Main plugin) {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§6§lKontokarte")){
                player.openInventory(Utils.atm);
            }else{
                player.sendMessage(Main.prefix+"§cDu musst deine Kontokarte in der Hand halten!");
            }

        } else {
            commandSender.sendMessage(Main.onlyplayer);
        }




        return false;
    }
}
