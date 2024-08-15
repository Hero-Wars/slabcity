package de.zombyxl.slabcityrp;

import de.zombyxl.slabcityrp.events.RegisterEvent;
import de.zombyxl.slabcityrp.events.RegisterNPCListener;
import de.zombyxl.slabcityrp.systems.housing.listener.HousingListener;
import de.zombyxl.slabcityrp.utils.ConfigurationBuilder;
import de.zombyxl.slabcityrp.utils.NPCListener;
import de.zombyxl.slabcityrp.utils.NPCManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static de.zombyxl.slabcityrp.utils.Utils.registeramt;

public final class Main extends JavaPlugin {


    public static String prefix = "§8[§6SlabCityRP§8] §7";
    public static String noperm = prefix + "§cDazu hast du keine Rechte!";
    public static String onlyplayer = prefix + "§cNur Spieler können diesen Befehl ausführen!";
    public static ConfigurationBuilder user = new ConfigurationBuilder("plugins/SlabCityRP", "user");
    public static ConfigurationBuilder housing = new ConfigurationBuilder("plugins/SlabCityRP", "housing");

    private static Plugin plugin;

    @Override
    public void onEnable() {


        new MainZ(this);
        new MainC(this);

        plugin = this;

        if (user.loadDefault()){
            user.set("user", "test");
            user.save();
        }

        if (housing.loadDefault()){
            housing.set("housing", "test");
            housing.save();
        }

        Bukkit.getPluginManager().registerEvents(new RegisterEvent(), this);
        Bukkit.getPluginManager().registerEvents(new NPCListener(), this);
        Bukkit.getPluginManager().registerEvents(new RegisterNPCListener(), this);


        Bukkit.getPluginManager().registerEvents(new HousingListener(), this);


    }

    @Override
    public void onDisable() {

    }

    public static Plugin getPlugin() {
        return plugin;
    }
}