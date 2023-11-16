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

package com.diontryban.ash_api.client.gui.screens;

import com.diontryban.ash_api.compat.AshApiModMenuEntrypoint;
import com.diontryban.ash_api.modloader.ModLoader;
import com.diontryban.ash_api.options.ModOptions;
import com.diontryban.ash_api.options.ModOptionsManager;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public final class ModOptionsScreenRegistryFabric extends ModOptionsScreenRegistry{
    @Override
    protected <S extends Screen, O extends ModOptions> void registerModOptionsScreenImpl(
            @NotNull ModOptionsManager<O> options,
            @NotNull ModOptionsScreenFactory<S, O> factory
    ) {
        // Guard access to AshApiModMenuEntrypoint only if Mod Menu is
        // installed. If it isn't installed and AshFabricModMenuEntrypoint is
        // somehow class loaded, the game will crash.
        if (ModLoader.isModLoaded("modmenu")) {
            AshApiModMenuEntrypoint.addConfigScreenFactory(
                    options.getModId(),
                    parent -> factory.create(options, parent)
            );
        }
    }
}
