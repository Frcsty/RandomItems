package com.github.frcsty.randomitems.registry;

import com.github.frcsty.randomitems.RandomItems;

public interface Registerable {

    default void enable(final RandomItems plugin) { }

    default void disable(final RandomItems plugin) { }

}
