package de.zombyxl.slabcityrp.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static de.zombyxl.slabcityrp.Main.prefix;
import static de.zombyxl.slabcityrp.Main.user;
import static de.zombyxl.slabcityrp.utils.Utils.flugzeug;

public class RegisterEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (user.get(player.getUniqueId()+".uuid") == null){
            user.set(player.getUniqueId()+".uuid", player.getUniqueId().toString());
            user.set(player.getUniqueId()+".displayname", player.getDisplayName());
            user.set(player.getUniqueId()+".register", "false");
            user.set(player.getUniqueId()+".ausweis", "false");
            user.set(player.getUniqueId()+".driverl", "false");
            user.set(player.getUniqueId()+".waffenl", "false");
            user.save();
            player.teleport(flugzeug);
            player.sendMessage(prefix + "§7Willkommen in §6SlabCityRP§7!");
            player.sendMessage(prefix + "§aBitte gehe nun zum Einreiseamt und registriere dich!");
        }

    }

}
