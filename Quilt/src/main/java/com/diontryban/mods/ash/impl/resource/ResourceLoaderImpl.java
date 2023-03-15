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

package com.diontryban.mods.ash.impl.resource;

import com.diontryban.mods.ash.api.resource.ResourceLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.qsl.resource.loader.api.reloader.IdentifiableResourceReloader;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ResourceLoaderImpl extends ResourceLoader {
    private final org.quiltmc.qsl.resource.loader.api.ResourceLoader resourceLoader;

    /**
     * Empty constructor only to be used by ImplementationLoader.
     *
     * @deprecated Use {@link ResourceLoader#get(PackType)}
     */
    public ResourceLoaderImpl() {
        this.resourceLoader = null;
    }

    private ResourceLoaderImpl(PackType type) {
        this.resourceLoader = org.quiltmc.qsl.resource.loader.api.ResourceLoader.get(type);
    }

    @Override
    public void registerReloadListenerImpl(PreparableReloadListener reloadListener) {
        resourceLoader.registerReloader(
                new IdentifiableResourceReloader() {
                    @Override
                    public @NotNull ResourceLocation getQuiltId() {
                        return new ResourceLocation(reloadListener.getName());
                    }

                    @Override
                    public CompletableFuture<Void> reload(
                            PreparationBarrier preparationBarrier,
                            ResourceManager resourceManager,
                            ProfilerFiller preparationsProfiler,
                            ProfilerFiller reloadProfiler,
                            Executor backgroundExecutor,
                            Executor gameExecutor
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
    protected ResourceLoader getImpl(PackType type) {
        return new ResourceLoaderImpl(type);
    }
}
