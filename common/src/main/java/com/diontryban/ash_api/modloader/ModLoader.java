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

import com.diontryban.ash_api.ServiceUtil;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

@ApiStatus.AvailableSince("20.2.0-beta")
@ApiStatus.NonExtendable
public abstract class ModLoader {
    private static final ModLoader IMPL = ServiceUtil.load(ModLoader.class);

    /**
     * Get the name of the running mod loader.
     *
     * @return the mod loader's lowercase name. e.g., "fabric", "forge", or "neoforge"
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static @NotNull String getName() {
        return IMPL.getNameImpl();
    }

    /**
     * Check if a mod with the given mod id is loaded by the mod loader.
     *
     * @param modId the mod id of the mod
     * @return whether the mod is loaded
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static boolean isModLoaded(String modId) {
        return IMPL.isModLoadedImpl(modId);
    }

    /**
     * Checks if the game is currently running in a "development" environment.
     * This can be used for enabling a debug mode or additional checks.
     *
     * <p>This should not be used to make assumptions on certain features, such
     * as mappings, but as a toggle for certain functionalities.</p>
     *
     * @return whether the game is running in a "development" environment
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static boolean isDevelopmentEnvironment() {
        return IMPL.isDevelopmentEnvironmentImpl();
    }

    /**
     * Get the current game working directory.
     *
     * @return the working directory
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static Path getGameDir() {
        return IMPL.getGameDirImpl();
    }

    /**
     * Get the current directory for the mod loader's configuration files.
     *
     * @return the configuration directory
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static Path getConfigDir() {
        return IMPL.getConfigDirImpl();
    }

    protected abstract @NotNull String getNameImpl();
    protected abstract boolean isModLoadedImpl(String modId);
    protected abstract boolean isDevelopmentEnvironmentImpl();
    protected abstract Path getGameDirImpl();
    protected abstract Path getConfigDirImpl();
}
