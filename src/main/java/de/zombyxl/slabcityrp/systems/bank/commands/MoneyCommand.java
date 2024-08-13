package de.zombyxl.slabcityrp.systems.bank.commands;

import de.zombyxl.slabcityrp.Main;
import de.zombyxl.slabcityrp.systems.bank.api.MoneyAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCommand implements CommandExecutor {
    public MoneyCommand(Main plugin) {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length >= 2) {
                Player target = Main.getPlugin().getServer().getPlayer(strings[1]);
                if (target == null) {
                    player.sendMessage(Main.prefix + "§cPlayer not found.");
                    return true;
                }
                if (strings[0].equalsIgnoreCase("get")) {
                    int Money = MoneyAPI.getMoney(target);
                    player.sendMessage(Main.prefix + "§7" + target.getName() + "§7 hat §e" + Money + "§7$");
                } else if (strings[0].equalsIgnoreCase("set") && strings.length == 3) {
                    MoneyAPI.setMoney(target, Integer.parseInt(strings[2]));
                    player.sendMessage(Main.prefix + "§7Du hast das Geld von §e" + target.getName() + "§7 auf §e" + strings[2] + "§7$ gesetzt");
                } else if (strings[0].equalsIgnoreCase("add") && strings.length == 3) {
                    MoneyAPI.addMoney(target, Integer.parseInt(strings[2]));
                    player.sendMessage(Main.prefix + "§7Du hast §e" + strings[2] + "§7$ zu §e" + target.getName() + "§7 hinzugefügt");
                } else if (strings[0].equalsIgnoreCase("remove") && strings.length == 3) {
                    MoneyAPI.removeMoney(target, Integer.parseInt(strings[2]));
                    player.sendMessage(Main.prefix + "§7Du hast §e" + strings[2] + "§7$ von §e" + target.getName() + "§7 entfernt");
                } else {
                    commandSender.sendMessage(Main.prefix + "§cUse: /money <set|get|add|remove> <player> <amount>");
                }
            } else {
                commandSender.sendMessage(Main.prefix + "§cUse: /money <set|get|add|remove> <player> <amount>");
            }
        } else {
            commandSender.sendMessage(Main.onlyplayer);
        }

        return true;
    }
}