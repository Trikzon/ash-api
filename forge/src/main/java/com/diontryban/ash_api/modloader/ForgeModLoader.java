/*
 * This file is part of Ash API.
 * A copy of this program can be found at https://github.com/Trikzon/ash-api.
 * Copyright (C) 2023 Dion Tryban
 *
 * Ash API is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * Ash API is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Ash API. If not, see <https://www.gnu.org/licenses/>.
 */

package com.diontryban.ash_api.modloader;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Optional;

@ApiStatus.AvailableSince("20.2.0-beta")
public final class ForgeModLoader {
    private static final HashMap<String, ModLoadingContext> MOD_CONTEXTS = new HashMap<>();
    private static final HashMap<String, FMLJavaModLoadingContext> FML_MOD_CONTEXTS = new HashMap<>();

    /**
     * Registers a mod's Forge mod loadign context and FML mod loading context
     * instance for Ash to use when registering Forge events and other Forge
     * registries that require it.
     *
     * <p>You can get the mod loading context to pass to this method by calling
     * {@link ModLoadingContext#get()} and
     * {@link FMLJavaModLoadingContext#get()}</p>
     *
     * @param modId the mod's mod id
     * @param context the mod's Forge mod loading context
     * @param fmlContext the mod's FML mod loading context
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static void registerMod(String modId, ModLoadingContext context, FMLJavaModLoadingContext fmlContext) {
        MOD_CONTEXTS.put(modId, context);
        FML_MOD_CONTEXTS.put(modId, fmlContext);
    }

    /**
     * Get the Forge mod loading context for the given mod id.
     *
     * @param modId the mod id
     * @return the Forge mod loading context for the given mod id
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static Optional<ModLoadingContext> getContext(String modId) {
        return Optional.ofNullable(MOD_CONTEXTS.get(modId));
    }

    /**
     * Convenience method of {@link ForgeModLoader#getContext(String)}.
     *
     * <p>Instead of returning Optional, simply returns the mod loading context
     * if it exists, or throws an exception if it doesn't.</p>
     *
     * @param modId the mod id
     * @return the Forge mod loading context for the given mod id
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static @NotNull ModLoadingContext getContextOrThrow(String modId) {
        return getContext(modId).orElseThrow(
                () -> new NullPointerException("Forge Mod " + modId + " has not been registered to Ash API.")
        );
    }

    /**
     * Get the Forge mod event bus for the given mod id.
     *
     * @param modId the mod id
     * @return the Forge mod event bus for the given mod id
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static Optional<IEventBus> getEventBus(String modId) {
        return Optional.ofNullable(FML_MOD_CONTEXTS.get(modId)).map(FMLJavaModLoadingContext::getModEventBus);
    }

    /**
     * Convenience method of {@link ForgeModLoader#getEventBus(String)}.
     *
     * <p>Instead of returning Optional, simply returns the mod event bus if it
     * exists, or throws an exception if it doesn't.</p>
     *
     * @param modId the mod id
     * @return the Forge mod event bus for the given mod id
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static @NotNull IEventBus getEventBusOrThrow(String modId) {
        return getEventBus(modId).orElseThrow(
                () -> new NullPointerException("Forge Mod " + modId + " has not been registered to Ash API.")
        );
    }
}
