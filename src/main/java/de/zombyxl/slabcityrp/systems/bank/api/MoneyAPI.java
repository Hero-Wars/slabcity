package de.zombyxl.slabcityrp.systems.bank.api;

import de.zombyxl.slabcityrp.Main;
import org.bukkit.entity.Player;

public class MoneyAPI {

    public static int getMoney(Player player) {
        return (int) Main.user.get(player.getUniqueId() + ".money");
    }

    public static void setMoney(Player player, int amount) {
        Main.user.set(player.getUniqueId() + ".money", amount);
        Main.user.save();
    }

    public static void addMoney(Player player, int amount) {
        int Money = getMoney(player);
        Money = Money + amount;
        Main.user.set(player.getUniqueId() + ".money", Money);
        Main.user.save();
    }

    public static void removeMoney(Player player, int amount) {
        int Money = getMoney(player);
        Money = Money - amount;
        Main.user.set(player.getUniqueId() + ".money", Money);
        Main.user.save();
    }

    public static boolean hasEnoughMoney(Player player, int amount) {
        int Money = getMoney(player);
        return Money >= amount;
    }
}
