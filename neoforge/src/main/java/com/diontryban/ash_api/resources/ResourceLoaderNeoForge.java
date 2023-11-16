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
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@ApiStatus.Internal
public final class ResourceLoaderNeoForge extends ResourceLoader {
    private static final List<PreparableReloadListener> SERVER_RELOAD_LISTENERS = new ArrayList<>();

    private final PackType type;

    /**
     * Default constructor only to be used by {@link com.diontryban.ash_api.ServiceUtil}.
     *
     * @deprecated Use {@link ResourceLoader#get(PackType)}
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
            // TODO: Test if this breaks on a dedicated server.
            var resourceManager = Minecraft.getInstance().getResourceManager();
            if (resourceManager instanceof ReloadableResourceManager) {
                ((ReloadableResourceManager) resourceManager).registerReloadListener(reloadListener);
            }
        } else if (type == PackType.SERVER_DATA) {
            if (SERVER_RELOAD_LISTENERS.isEmpty()) {
                NeoForge.EVENT_BUS.addListener(this::onAddReloadListener);
            }
            SERVER_RELOAD_LISTENERS.add(reloadListener);
        } else {
            AshApi.LOG.error("Attempted to register a resource loader of type {}. This is unsupported. Please file an issue.", type);
        }
    }

    private void onAddReloadListener(AddReloadListenerEvent event) {
        for (PreparableReloadListener reloadListener : SERVER_RELOAD_LISTENERS) {
            event.addListener(reloadListener);
        }
    }

    @Override
    protected @NotNull ResourceLoader getImpl(PackType type) {
        return new ResourceLoaderNeoForge(type);
    }
}
