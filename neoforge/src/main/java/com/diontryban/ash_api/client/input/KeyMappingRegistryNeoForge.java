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

import net.minecraft.client.KeyMapping;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ApiStatus.Internal
public final class KeyMappingRegistryNeoForge extends KeyMappingRegistry {
    private static final ConcurrentMap<String, List<KeyMapping>> MOD_KEY_MAPPINGS = new ConcurrentHashMap<>();

    @Override
    protected @NotNull KeyMapping registerImpl(@NotNull String modId, @NotNull KeyMapping keyMapping) {
        if (!MOD_KEY_MAPPINGS.containsKey(modId)) {
            MOD_KEY_MAPPINGS.put(modId, new ArrayList<>());

            var eventBus = ModList.get().getModContainerById(modId).orElseThrow(
                    () -> new NullPointerException("Mod with id " + modId + " does not exist. Cannot register key mapping.")
            ).getEventBus();

            if (eventBus != null) {
                eventBus.<RegisterKeyMappingsEvent>addListener(event -> {
                    for (KeyMapping key : MOD_KEY_MAPPINGS.get(modId)) {
                        event.register(key);
                    }
                });
            }
        }

        MOD_KEY_MAPPINGS.get(modId).add(keyMapping);

        return keyMapping;
    }
}
