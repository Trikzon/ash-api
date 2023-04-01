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

package com.diontryban.mods.ash.api.options;

public abstract class ModOptions {
    public int version;

    public ModOptions() {
        this.version = this.getVersion();
    }

    /**
     * Sets the version of the current mod's options.
     *
     * <p>If the version is higher than the saved json file's version, the file
     * will be overwritten with the default options.</p>
     */
    protected int getVersion() {
        return 1;
    }
}
