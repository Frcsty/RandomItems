package com.github.frcsty.randomitems.command.impl;

import com.github.frcsty.randomitems.RandomItems;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Completion;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.SplittableRandom;

@Command("randomitem")
public final class GiveItemCommand extends CommandBase {

    private static final SplittableRandom RANDOM = new SplittableRandom();
    private final RandomItems plugin;

    public GiveItemCommand(final RandomItems plugin) {
        this.plugin = plugin;
    }

    @SubCommand("give")
    @Permission("randomitems.command.give")
    public void onGiveCommand(final CommandSender sender, @Completion("#players") final Player target, @Completion("#randomlists") final String identifier) {
        final FileConfiguration configuration = plugin.getConfig();
        final ConfigurationSection section = configuration.getConfigurationSection("command-list");

        if (section == null) {
            sender.sendMessage(Component.text("The Configuration Section 'command-list' does not exist!"));
            return;
        }

        final List<String> commands = section.getStringList(identifier);
        if (commands.isEmpty()) {
            sender.sendMessage(Component.text("The item list for the given identifier '" + identifier + "' is empty!"));
            return;
        }

        final String command = commands.get(RANDOM.nextInt(commands.size() - 1));
        if (command == null) {
            sender.sendMessage(Component.text("The selected command was invalid!"));
            return;
        }

        Bukkit.dispatchCommand(sender, command.replace("<player>", target.getName()));
    }

}
