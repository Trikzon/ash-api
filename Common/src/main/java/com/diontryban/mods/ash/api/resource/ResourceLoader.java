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

package com.diontryban.mods.ash.api.resource;

import com.diontryban.mods.ash.ImplementationLoader;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;

public abstract class ResourceLoader {
    private static final ResourceLoader IMPL = ImplementationLoader.load(ResourceLoader.class);

    /**
     * Get the resource loader instance for a given resource type. A resource
     * loader instance may be used to register resource reload listeners.
     *
     * <p>Note: type of {@link PackType#SERVER_DATA} has not been tested yet.</p>
     *
     * @param type the given resource type
     * @return the resource loader instance
     */
    public static ResourceLoader get(PackType type) {
        return IMPL.getImpl(type);
    }

    /**
     * Register a resource reload listener.
     *
     * @param reloadListener the resource reload listener
     */
    public abstract void registerReloadListenerImpl(PreparableReloadListener reloadListener);

    protected abstract ResourceLoader getImpl(PackType type);
}
