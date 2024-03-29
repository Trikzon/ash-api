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

package com.diontryban.ash_api.modloader;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

@ApiStatus.Internal
public final class ModLoaderFabric extends ModLoader {
    @Override
    protected @NotNull String getNameImpl() {
        return "fabric";
    }

    @Override
    protected boolean isModLoadedImpl(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    protected boolean isDevelopmentEnvironmentImpl() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    protected Path getGameDirImpl() {
        return FabricLoader.getInstance().getGameDir();
    }

    @Override
    protected Path getConfigDirImpl() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
