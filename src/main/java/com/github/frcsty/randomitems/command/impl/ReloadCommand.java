package com.github.frcsty.randomitems.command.impl;

import com.github.frcsty.randomitems.RandomItems;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;

@Command("randomitem")
public final class ReloadCommand extends CommandBase {

    private final RandomItems plugin;

    public ReloadCommand(final RandomItems plugin) {
        this.plugin = plugin;
    }

    @SubCommand("reload")
    @Permission("randomitems.command.reload")
    public void onReloadCommand(final CommandSender sender) {
        this.plugin.reloadConfig();

        this.plugin.registries().forEach(it -> {
            it.disable(plugin);

            it.enable(plugin);
        });
    }

}
