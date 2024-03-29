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

import com.diontryban.ash_api.modloader.ForgeModLoader;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiStatus.Internal
public final class KeyMappingRegistryForge extends KeyMappingRegistry {
    private static final Map<String, List<KeyMapping>> MOD_KEY_MAPPINGS = new HashMap<>();

    @Override
    protected @NotNull KeyMapping registerKeyMappingImpl(@NotNull String modId, @NotNull KeyMapping keyMapping) {
        if (!MOD_KEY_MAPPINGS.containsKey(modId)) {
            MOD_KEY_MAPPINGS.put(modId, new ArrayList<>());

            ForgeModLoader.getEventBusOrThrow(modId).<RegisterKeyMappingsEvent>addListener(event -> {
                for (KeyMapping key : MOD_KEY_MAPPINGS.get(modId)) {
                    event.register(key);
                }
            });
        }

        MOD_KEY_MAPPINGS.get(modId).add(keyMapping);

        return keyMapping;
    }
}
