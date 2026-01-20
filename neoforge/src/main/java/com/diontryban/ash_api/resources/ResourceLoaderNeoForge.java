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

package com.diontryban.ash_api.resources;

import com.diontryban.ash_api.AshApi;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.event.AddClientReloadListenersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddServerReloadListenersEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@ApiStatus.Internal
public final class ResourceLoaderNeoForge extends ResourceLoader {
    private static final Map<String, Queue<PreparableReloadListener>> CLIENT_RELOAD_LISTENERS = new ConcurrentHashMap<>();
    private static final Queue<PreparableReloadListener> SERVER_RELOAD_LISTENERS = new ConcurrentLinkedQueue<>();

    private final PackType type;

    /**
     * Default constructor only to be used by {@link com.diontryban.ash_api.ServiceUtil}.
     * <p>
     * Use {@link ResourceLoader#get(PackType)} instead.
     */
    public ResourceLoaderNeoForge() {
        type = null;
    }

    private ResourceLoaderNeoForge(PackType type) {
        this.type = type;
    }

    @Override
    public void registerReloadListener(@NotNull PreparableReloadListener reloadListener) {
        if (type == PackType.CLIENT_RESOURCES) {
            var modId = Identifier.parse(reloadListener.getName()).getNamespace();

            if (!CLIENT_RELOAD_LISTENERS.containsKey(modId)) {
                CLIENT_RELOAD_LISTENERS.put(modId, new ConcurrentLinkedQueue<>());

                var eventBus = ModList.get().getModContainerById(modId).orElseThrow(
                        () -> new NullPointerException("Mod with id " + modId + " does not exist. Cannot register reload listener.")
                ).getEventBus();

                if (eventBus != null) {
                    eventBus.addListener((AddClientReloadListenersEvent event) -> onAddClientReloadListener(event, modId));
                }
            }

            CLIENT_RELOAD_LISTENERS.get(modId).add(reloadListener);
        } else if (type == PackType.SERVER_DATA) {
            if (SERVER_RELOAD_LISTENERS.isEmpty()) {
                NeoForge.EVENT_BUS.addListener(this::onAddServerReloadListener);
            }

            SERVER_RELOAD_LISTENERS.add(reloadListener);
        } else {
            AshApi.LOG.error("Attempted to register a resource loader of type {}. This is unsupported. Please file an issue.", type);
        }
    }

    private void onAddClientReloadListener(AddClientReloadListenersEvent event, String modId) {
        for (PreparableReloadListener reloadListener : CLIENT_RELOAD_LISTENERS.get(modId)) {
            event.addListener(Identifier.parse(reloadListener.getName()), reloadListener);
        }
    }

    private void onAddServerReloadListener(AddServerReloadListenersEvent event) {
        for (PreparableReloadListener reloadListener : SERVER_RELOAD_LISTENERS) {
            event.addListener(Identifier.parse(reloadListener.getName()), reloadListener);
        }
    }

    @Override
    protected @NotNull ResourceLoader getImpl(PackType type) {
        return new ResourceLoaderNeoForge(type);
    }
}
