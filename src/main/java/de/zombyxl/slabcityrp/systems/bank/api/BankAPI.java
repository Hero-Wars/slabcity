package de.zombyxl.slabcityrp.systems.bank.api;

import de.zombyxl.slabcityrp.Main;
import org.bukkit.entity.Player;

public class BankAPI {
    public static int getBank(Player player) {
        return (int) Main.user.get(player.getUniqueId() + ".bank");
    }

    public static void setBank(Player player, int amount) {
        Main.user.set(player.getUniqueId() + ".bank", amount);
        Main.user.save();
    }

    public static void addBank(Player player, int amount) {
        int Bank = getBank(player);
        Bank = Bank + amount;
        Main.user.set(player.getUniqueId() + ".bank", Bank);
        Main.user.save();
    }

    public static void removeBank(Player player, int amount) {
        int Bank = getBank(player);
        Bank = Bank - amount;
        Main.user.set(player.getUniqueId() + ".bank", Bank);
        Main.user.save();
    }

    public static boolean hasEnoughBank(Player player, int amount) {
        int Bank = getBank(player);
        return Bank >= amount;
    }
}
