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

import com.diontryban.ash_api.ServiceUtil;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.AvailableSince("20.2.0-beta")
@ApiStatus.NonExtendable
public abstract class ResourceLoader {
    private static final ResourceLoader IMPL = ServiceUtil.load(ResourceLoader.class);

    /**
     * Get the resource loader instance for a given resource type. A resource
     * loader instance may be used to register resource reload listeners.
     *
     * <p>Note: type of {@link PackType#SERVER_DATA} has not been tested yet.</p>
     *
     * @param type the given resource type
     * @return the resource loader instance
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static @NotNull ResourceLoader get(PackType type) {
        return IMPL.getImpl(type);
    }

    /**
     * Register a resource reload listener.
     *
     * @param reloadListener the resource reload listener
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public abstract void registerReloadListener(@NotNull PreparableReloadListener reloadListener);

    protected abstract @NotNull ResourceLoader getImpl(PackType type);
}
