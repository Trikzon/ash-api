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
package com.diontryban.ash_api.client.input;

import com.diontryban.ash_api.ServiceUtil;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.AvailableSince("20.2.0-beta")
@ApiStatus.NonExtendable
public abstract class KeyMappingRegistry {
    private static final KeyMappingRegistry IMPL = ServiceUtil.load(KeyMappingRegistry.class);

    /**
     * Registers a {@link KeyMapping}.
     *
     * @param modId      your mod's mod id
     * @param keyMapping the key mapping
     * @return the key mapping
     */
    @ApiStatus.AvailableSince("21.0.0-beta")
    public static @NotNull KeyMapping register(@NotNull String modId, @NotNull KeyMapping keyMapping) {
        return IMPL.registerImpl(modId, keyMapping);
    }

    /**
     * Helper method of {@link KeyMappingRegistry#register(String, KeyMapping)}.
     * It constructs a {@link KeyMapping} for you with constructor arguments.
     *
     * @param resLoc    your mod's mod id and the key mapping's unique name
     * @param inputType type of input
     * @param key       e.g., GLFW.GLFW_KEY_R
     * @param category  key category
     * @return the key mapping
     */
    @ApiStatus.AvailableSince("21.0.0-beta")
    public static @NotNull KeyMapping register(
            @NotNull Identifier resLoc,
            @NotNull InputConstants.Type inputType,
            int key,
            @NotNull String category
    ) {
        //TODO throws exception if category already exists; add catch
        var cat = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(resLoc.getNamespace(), category));

        return register(resLoc.getNamespace(), new KeyMapping(
                String.format("key.%s.%s", resLoc.getNamespace(), resLoc.getPath()),
                inputType,
                key,
                cat
        ));
    }

    /**
     * Helper method of {@link KeyMappingRegistry#register(Identifier, InputConstants.Type, int, String)}.
     * It passes the default inputType of {@code InputConstants.Type.KEYSYM}.
     *
     * @param resLoc   your mod's id and the key mapping's unique name
     * @param key      e.g., GLFW.GLFW_KEY_R
     * @param category key category
     * @return the key mapping
     */
    @ApiStatus.AvailableSince("21.0.0-beta")
    public static @NotNull KeyMapping register(
            @NotNull Identifier resLoc,
            int key,
            @NotNull String category
    ) {
        return register(resLoc, InputConstants.Type.KEYSYM, key, category);
    }

    @ApiStatus.Internal
    protected abstract @NotNull KeyMapping registerImpl(@NotNull String modId, @NotNull KeyMapping keyMapping);
}
