package com.github.frcsty.randomitems;

import com.github.frcsty.randomitems.command.CommandRegisterable;
import com.github.frcsty.randomitems.registry.Registerable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class RandomItems extends JavaPlugin {

    private static final Set<Registerable> REGISTRIES = Set.of(
            new CommandRegisterable()
    );

    @Override
    public void onEnable() {
        saveDefaultConfig();

        REGISTRIES.forEach(it -> it.enable(this));
    }

    @Override
    public void onDisable() {
        REGISTRIES.forEach(it -> it.disable(this));

        reloadConfig();
    }

    public Set<Registerable> registries() { return REGISTRIES; }

}
