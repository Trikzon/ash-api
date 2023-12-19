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

import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

@ApiStatus.AvailableSince("20.2.0-beta")
public class FabricModInitializer implements ModInitializer {
    @ApiStatus.AvailableSince("20.2.0-beta")
    protected @Nullable CommonModInitializer commonModInitializer;

    @ApiStatus.AvailableSince("20.2.0-beta")
    protected FabricModInitializer(@Nullable Supplier<CommonModInitializer> commonModInitializer) {
        if (commonModInitializer != null) {
            this.commonModInitializer = commonModInitializer.get();
        }
    }

    @ApiStatus.AvailableSince("20.2.0-beta")
    @Override
    public void onInitialize() {
        if (commonModInitializer != null) {
            commonModInitializer.onInitialize();
        }
    }
}