package com.github.frcsty.randomitems.command;

import com.github.frcsty.randomitems.RandomItems;
import com.github.frcsty.randomitems.command.impl.GiveItemCommand;
import com.github.frcsty.randomitems.command.impl.ReloadCommand;
import com.github.frcsty.randomitems.registry.Registerable;
import me.mattstudios.mf.base.CommandManager;
import org.bukkit.configuration.ConfigurationSection;

public final class CommandRegisterable implements Registerable {

    @Override
    public void enable(final RandomItems plugin) {
        final CommandManager manager = new CommandManager(plugin);

        manager.getCompletionHandler().register("#randomlists", input -> {
            final ConfigurationSection section = plugin.getConfig().getConfigurationSection("command-list");

            if (section == null) {
                throw new RuntimeException("Failed to create chat completion for random items list, as none could be found!");
            }

            return section.getKeys(false).stream().toList();
        });

        manager.register(
                new ReloadCommand(plugin),
                new GiveItemCommand(plugin)
        );
    }

}


