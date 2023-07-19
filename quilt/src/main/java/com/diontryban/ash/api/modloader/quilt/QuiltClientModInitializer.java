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

package com.diontryban.ash.api.modloader.quilt;

import com.diontryban.ash.api.modloader.CommonClientModInitializer;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import java.util.function.Supplier;

public abstract class QuiltClientModInitializer implements ClientModInitializer {
    protected CommonClientModInitializer commonClientModInitializer;

    protected QuiltClientModInitializer(@Nullable Supplier<CommonClientModInitializer> commonClientModInitializer) {
        if (commonClientModInitializer != null) {
            this.commonClientModInitializer = commonClientModInitializer.get();
        }
    }

    @Override
    public void onInitializeClient(ModContainer mod) {
        if (commonClientModInitializer != null) {
            commonClientModInitializer.onInitializeClient();
        }
    }
}
