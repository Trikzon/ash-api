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

package com.diontryban.mods.ash.api.client.gui.screens;

import com.diontryban.mods.ash.ImplementationLoader;
import com.diontryban.mods.ash.api.options.ModOptions;
import com.diontryban.mods.ash.api.options.ModOptionsManager;
import net.minecraft.client.gui.screens.Screen;

public abstract class ModOptionsScreenRegistry {
    private static final ModOptionsScreenRegistry IMPL = ImplementationLoader.load(ModOptionsScreenRegistry.class);

    /**
     * Registers a {@link Screen} as the mod options screen to be accessible
     * in-game in the mod menu.
     *
     * <p>On Fabric and Quilt, this requires the Mod Menu mod to be installed.
     * The game can still run without Mod Menu installed, but no mod options
     * screen will be available.</p>
     *
     * <p>If there are other mods that provide mod menu functionality, please
     * create an issue or pull request so that Ash can support it.</p>
     *
     * <p>See also {@link ModOptionsScreen} for a screen class built
     * specifically for rendering a list of mod options.</p>
     *
     * @param options the mod's global instance of mod options
     * @param factory the screen's constructor function
     */
    public static <S extends Screen, O extends ModOptions> void registerModOptionsScreen(
            ModOptionsManager<O> options,
            ModOptionsScreenFactory<S, O> factory
    ) {
        IMPL.registerModOptionsScreenImpl(options, factory);
    }

    protected abstract <S extends Screen, O extends ModOptions> void registerModOptionsScreenImpl(
            ModOptionsManager<O> options,
            ModOptionsScreenFactory<S, O> factory
    );
}
