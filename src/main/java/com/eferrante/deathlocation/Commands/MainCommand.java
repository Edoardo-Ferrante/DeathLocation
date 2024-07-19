package com.eferrante.deathlocation.Commands;

import com.eferrante.deathlocation.DeathLocation;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MainCommand implements CommandExecutor {
    private final DeathLocation plugin;
    public MainCommand(DeathLocation plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();

                    p.sendMessage(MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("messages.reloadConfig")));
                }
            }
        }
        return false;
    }
}
