package com.eferrante.deathlocation.Commands;

import com.eferrante.deathlocation.DeathLocation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    private final DeathLocation plugin;

    public MainCommand(DeathLocation plugin) {
        this.plugin = plugin;
    }

    private void sendHelpMessage(Player player) {
        List<String> helpMsgLines = plugin.getConfig().getStringList("messages.helpMessage");
        for (String line : helpMsgLines) {
            player.sendMessage(MiniMessage.miniMessage().deserialize(line));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("deathlocation.admin")) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        plugin.reloadConfig();
                        p.sendMessage(MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("messages.reloadConfig")));
                        return true;
                    } else if (args[0].equalsIgnoreCase("help")) {
                        sendHelpMessage(p);
                        return true;
                    } else {
                        p.sendMessage(MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("messages.unknownCommand")));
                    }
                } else {
                    sendHelpMessage(p);
                }
            } else {
                p.sendMessage(MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("messages.noPermission")));
            }
        }
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> subcommands = Arrays.asList("reload", "help");
            List<String> completions = new ArrayList<>();
            for (String subcommand : subcommands) {
                if (subcommand.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(subcommand);
                }
            }
            Collections.sort(completions);
            return completions;
        }
        return Collections.emptyList();
    }
}

