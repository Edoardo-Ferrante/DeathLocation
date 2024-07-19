package com.eferrante.deathlocation.Listeners;
import com.eferrante.deathlocation.DeathLocation;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import java.text.DecimalFormat;

public class DeathListener implements Listener {
    private final DeathLocation plugin;
   public DeathListener(DeathLocation plugin) {
       this.plugin = plugin;
   }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if(plugin.getConfig().getBoolean("settings.deathLocEnabled")) {
            Player p = event.getPlayer();
            double x = event.getPlayer().getLocation().getX();
            double y = event.getPlayer().getLocation().getY();
            double z = event.getPlayer().getLocation().getZ();
            DecimalFormat df = new DecimalFormat("###.###");
            if (!plugin.getConfig().getBoolean("settings.publicAnnounce")) {
                String msg = plugin.getConfig().getString("messages.deathLocMessage");
                msg = msg.replaceAll("%x_num%", df.format(x));
                msg = msg.replaceAll("%y_num%", df.format(y));
                msg = msg.replaceAll("%z_num%", df.format(z));
                Component parsed = MiniMessage.miniMessage().deserialize(msg);
                p.sendMessage(parsed);
            } else {
                String pubMsg = plugin.getConfig().getString("messages.publicDeathLocMessage");
                pubMsg = pubMsg.replaceAll("%player%", p.getName());
                pubMsg = pubMsg.replaceAll("%x_num%", df.format(x));
                pubMsg = pubMsg.replaceAll("%y_num%", df.format(y));
                pubMsg = pubMsg.replaceAll("%z_num%", df.format(z));
                Component parsed = MiniMessage.miniMessage().deserialize(pubMsg);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(parsed);
                }
                System.out.println("TEST MESSAGE");
            }
        }
    }
}
