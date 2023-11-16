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

import com.diontryban.ash_api.ServiceUtil;
import com.diontryban.ash_api.options.ModOptions;
import com.diontryban.ash_api.options.ModOptionsManager;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.AvailableSince("20.2.0-beta")
@ApiStatus.NonExtendable
public abstract class ModOptionsScreenRegistry {
    private static final ModOptionsScreenRegistry IMPL = ServiceUtil.load(ModOptionsScreenRegistry.class);

    /**
     * Registers a {@link Screen} as the mod options screen to be accessible
     * in-game in the mod menu.
     *
     * <p>On Fabric, this requires the Mod Menu mod to be installed. The game
     * can still be run without Mod Menu installed, but no mod options screen
     * will be available to the player.</p>
     *
     * <p>If there are other mods that provide mod menu functionality, please
     * create an issue or pull request so that Ash API can support it.</p>
     *
     * <p>See also {@link ModOptionsScreen} for a screen class built
     * specifically for rendering a {@link ModOptions} list.</p>
     *
     * @param options the mod's global instance of mod options
     * @param factory the screen's constructor function
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static <S extends Screen, O extends ModOptions> void registerModOptionsScreen(
            @NotNull ModOptionsManager<O> options,
            @NotNull ModOptionsScreenFactory<S, O> factory
    ) {
        IMPL.registerModOptionsScreenImpl(options, factory);
    }

    protected abstract <S extends Screen, O extends ModOptions> void registerModOptionsScreenImpl(
            @NotNull ModOptionsManager<O> options,
            @NotNull ModOptionsScreenFactory<S, O> factory
    );
}
