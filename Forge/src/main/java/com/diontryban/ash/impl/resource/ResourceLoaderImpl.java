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

package com.diontryban.ash.impl.resource;

import com.diontryban.ash.api.resource.ResourceLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;

import java.util.ArrayList;
import java.util.List;

public class ResourceLoaderImpl extends ResourceLoader {
    private static final List<PreparableReloadListener> SERVER_RELOAD_LISTENERS = new ArrayList<>();

    private final PackType type;

    /**
     * Empty constructor only to be used by ImplementationLoader.
     *
     * @deprecated Use {@link ResourceLoader#get(PackType)}
     */
    public ResourceLoaderImpl() {
        this.type = null;
    }

    private ResourceLoaderImpl(PackType type) {
        this.type = type;
    }

    @Override
    public void registerReloadListenerImpl(PreparableReloadListener reloadListener) {
        if (type == PackType.CLIENT_RESOURCES) {
            var resourceManager = Minecraft.getInstance().getResourceManager();
            if (resourceManager instanceof ReloadableResourceManager) {
                ((ReloadableResourceManager) resourceManager).registerReloadListener(reloadListener);
            }
        } else if (type == PackType.SERVER_DATA) {
            if (SERVER_RELOAD_LISTENERS.isEmpty()) {
                MinecraftForge.EVENT_BUS.addListener(this::onAddReloadListener);
            }
            SERVER_RELOAD_LISTENERS.add(reloadListener);
        }
    }

    private void onAddReloadListener(AddReloadListenerEvent event) {
        for (PreparableReloadListener reloadListener : SERVER_RELOAD_LISTENERS) {
            event.addListener(reloadListener);
        }
    }

    @Override
    protected ResourceLoader getImpl(PackType type) {
        return new ResourceLoaderImpl(type);
    }
}
