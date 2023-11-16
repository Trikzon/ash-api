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

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

@ApiStatus.AvailableSince("20.2.0-beta")
public abstract class ForgeClientModInitializer {
    @ApiStatus.AvailableSince("20.2.0-beta")
    protected @Nullable CommonClientModInitializer commonClientModInitializer;

    @ApiStatus.AvailableSince("20.2.0-beta")
    protected ForgeClientModInitializer(
            @NotNull String modId,
            @Nullable Supplier<CommonClientModInitializer> commonClientModInitializer
    ) {
        if (commonClientModInitializer != null) {
            this.commonClientModInitializer = commonClientModInitializer.get();
        }

        ForgeModLoader.getEventBusOrThrow(modId).addListener(this::onInitializeClient);
    }

    @ApiStatus.AvailableSince("20.2.0-beta")
    protected void onInitializeClient(FMLClientSetupEvent event) {
        if (commonClientModInitializer != null) {
            commonClientModInitializer.onInitializeClient();
        }
    }
}
