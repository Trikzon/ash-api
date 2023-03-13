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

package com.diontryban.mods.ash.api.modloader;

import com.diontryban.mods.ash.ImplementationLoader;

import java.nio.file.Path;

public abstract class ModLoader {
    private static final ModLoader IMPL = ImplementationLoader.load(ModLoader.class);

    /**
     * Get the name of the running mod loader.
     *
     * @return either "fabric", "forge", or "quilt".
     */
    public static String getName() {
        return IMPL.getNameImpl();
    }

    /**
     * Checks if a mod with the given mod id is loaded by the mod loader.
     *
     * @param modId the mod id of the mod
     * @return whether the mod is loaded
     */
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
    public static boolean isDevelopmentEnvironment() {
        return IMPL.isDevelopmentEnvironmentImpl();
    }

    /**
     * Get the current game working directory.
     *
     * @return the working directory
     */
    public static Path getGameDir() {
        return IMPL.getGameDirImpl();
    }

    /**
     * Get the current directory for the game's configuration files.
     *
     * @return the configuration directory
     */
    public static Path getConfigDir() {
        return IMPL.getConfigDirImpl();
    }

    protected abstract String getNameImpl();
    protected abstract boolean isModLoadedImpl(String modId);
    protected abstract boolean isDevelopmentEnvironmentImpl();
    protected abstract Path getGameDirImpl();
    protected abstract Path getConfigDirImpl();
}
