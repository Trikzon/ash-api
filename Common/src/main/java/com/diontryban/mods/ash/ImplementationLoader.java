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

package com.diontryban.mods.ash;

import java.util.ServiceLoader;

public class ImplementationLoader {
    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException(
                        "failed to load service for " + clazz.getName()
                ));
        Ash.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
