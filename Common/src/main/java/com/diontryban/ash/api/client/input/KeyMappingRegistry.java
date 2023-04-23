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

package com.diontryban.ash.api.client.input;

import com.diontryban.ash.ImplementationLoader;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;

public abstract class KeyMappingRegistry {
    private static final KeyMappingRegistry IMPL = ImplementationLoader.load(KeyMappingRegistry.class);

    /**
     * Registers a {@link KeyMapping}.
     *
     * <p>On Forge this registers with the Mod Event Bus, so you must register
     * with ModEventBus#register on Forge first.</p>
     *
     * @param modId your mod's mod id
     * @param keyMapping the key mapping
     * @return the key mapping
     */
    public static KeyMapping registerKeyMapping(String modId, KeyMapping keyMapping) {
        return IMPL.registerKeyMappingImpl(modId, keyMapping);
    }

    /**
     * Helper method of {@link KeyMappingRegistry#registerKeyMapping(String, KeyMapping)}.
     * It constructs a {@link KeyMapping} for you with constructor arguments.
     *
     * <p>On Forge this registers with the Mod Event Bus, so you must register
     * with ModEventBus#register on Forge first.</p>
     *
     * @param resLoc your mod's mod id and the key mapping's unique name
     * @param inputType type of input
     * @param key e.g. GLFW.GLFW_KEY_R
     * @param category key category
     * @return the key mapping
     */
    public static KeyMapping registerKeyMapping(
            ResourceLocation resLoc,
            InputConstants.Type inputType,
            int key,
            String category
    ) {
        return registerKeyMapping(resLoc.getNamespace(), new KeyMapping(
                String.format("key.%s.%s", resLoc.getNamespace(), resLoc.getPath()),
                inputType,
                key,
                String.format("key.categories.%s", category)
        ));
    }

    /**
     * Helper method of {@link KeyMappingRegistry#registerKeyMapping(ResourceLocation, InputConstants.Type, int, String)}.
     * It passes the default inputType of {@code InputConstants.Type.KEYSYM}.
     *
     * @param resLoc your mod's mod id and the key mapping's unique name
     * @param key e.g. GLFW.GLFW_KEY_R
     * @param category key category
     * @return the key mapping
     */
    public static KeyMapping registerKeyMapping(ResourceLocation resLoc, int key, String category) {
        return registerKeyMapping(resLoc, InputConstants.Type.KEYSYM, key, category);
    }

    protected abstract KeyMapping registerKeyMappingImpl(String modId, KeyMapping keyMapping);
}
