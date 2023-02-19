/*
 * This file is part of Ash.
 * A copy of this program can be found at https://github.com/Trikzon/ash.
 * Copyright (C) 2023 Dion Tryban
 *
 * Ash is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * Ash is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Ash. If not, see <https://www.gnu.org/licenses/>.
 */

package com.diontryban.mods.ash.api.forge;

import net.minecraftforge.eventbus.api.IEventBus;

import java.util.HashMap;
import java.util.Optional;

public class ModEventBus {
    private static final HashMap<String, IEventBus> MOD_EVENT_BUSES = new HashMap<>();

    /**
     * Registers a mod's Forge mod event bus for Ash to use when registering
     * Forge events that require it.
     *
     * <p>You can get the mod event bus to pass to this method by calling
     * {@code FMLJavaModLoadingContext.get().getModEventBus()}</p>
     *
     * @param modId the mod's mod id
     * @param bus the mod's Forge mod event bus
     */
    public static void register(String modId, IEventBus bus) {
        MOD_EVENT_BUSES.put(modId, bus);
    }

    /**
     * Get the Forge mod event bus for the given mod id.
     *
     * @param modId the mod id
     * @return the Forge mod event bus for the given mod id
     */
    public static Optional<IEventBus> getOptional(String modId) {
        return Optional.ofNullable(MOD_EVENT_BUSES.get(modId));
    }

    /**
     * Convenience method of {@link ModEventBus#getOptional(String)}.
     *
     * <p>Instead of returning Optional, simply returns the event bus if it
     * exists, or throws an exception if it doesn't.</p>
     *
     * @param modId the mod id
     * @return the Forge mod event bus for the given mod id
     */
    public static IEventBus getOrThrow(String modId) {
        return getOptional(modId).orElseThrow(
                () -> new NullPointerException("Mod Event Bus for " + modId + " has not been registered.")
        );
    }
}
