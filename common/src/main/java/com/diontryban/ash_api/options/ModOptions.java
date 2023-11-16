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
package com.diontryban.ash_api.options;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.AvailableSince("20.2.0-beta")
public abstract class ModOptions {
    @ApiStatus.AvailableSince("20.2.0-beta")
    public int version;

    @ApiStatus.AvailableSince("20.2.0-beta")
    public ModOptions() {
        version = getVersion();
    }

    /**
     * Sets the version of the current mod's options.
     *
     * <p>When using {@link ModOptionsManager}, if the version is higher than
     * the saved json file's version, the file will be overwritten with the
     * default options.</p>
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    protected int getVersion() {
        return 1;
    }
}
