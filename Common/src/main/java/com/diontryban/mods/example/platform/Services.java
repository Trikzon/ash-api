/*
 * This file is part of MultiLoader Template. A copy of this program can be
 * found at https://github.com/Trikzon/MultiLoader-Template.
 * Copyright (C) 2023 Dion Tryban
 *
 * MultiLoader Template is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * MultiLoader Template is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MultiLoader Template. If not, see <https://www.gnu.org/licenses/>.
 */
package com.diontryban.mods.example.platform;

import com.diontryban.mods.example.ExampleMod;
import com.diontryban.mods.example.platform.api.Platform;

import java.util.ServiceLoader;

public class Services {
    public static final Platform PLATFORM = load(Platform.class);
    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        ExampleMod.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
