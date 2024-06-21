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

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@ApiStatus.Internal
public final class ResourceLoaderFabric extends ResourceLoader {
    private final @Nullable ResourceManagerHelper resourceManagerHelper;

    /**
     * Default constructor only to be used by {@link com.diontryban.ash_api.ServiceUtil}.
     *
     * @deprecated Use {@link ResourceLoader#get(PackType)}
     */
    @ApiStatus.Internal
    public ResourceLoaderFabric() {
        resourceManagerHelper = null;
    }

    private ResourceLoaderFabric(PackType type) {
        resourceManagerHelper = ResourceManagerHelper.get(type);
    }

    @Override
    public void registerReloadListener(@NotNull PreparableReloadListener reloadListener) {
        // Would only be null if improperly created with the deprecated default constructor.
        assert resourceManagerHelper != null;
        resourceManagerHelper.registerReloadListener(new IdentifiableResourceReloadListener() {
                @Override
                public ResourceLocation getFabricId() {
                    return ResourceLocation.parse(reloadListener.getName());
                }

                @Override
                public @NotNull CompletableFuture<Void> reload(
                        @NotNull PreparationBarrier preparationBarrier,
                        @NotNull ResourceManager resourceManager,
                        @NotNull ProfilerFiller preparationsProfiler,
                        @NotNull ProfilerFiller reloadProfiler,
                        @NotNull Executor backgroundExecutor,
                        @NotNull Executor gameExecutor
                ) {
                    return reloadListener.reload(
                            preparationBarrier,
                            resourceManager,
                            preparationsProfiler,
                            reloadProfiler,
                            backgroundExecutor,
                            gameExecutor
                    );
                }
            }
        );
    }

    @Override
    protected @NotNull ResourceLoader getImpl(PackType type) {
        return new ResourceLoaderFabric(type);
    }
}
