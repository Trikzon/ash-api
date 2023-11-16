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

package com.diontryban.ash_api.client.event;

import com.diontryban.ash_api.ServiceUtil;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.AvailableSince("20.2.0-beta")
@ApiStatus.NonExtendable
public abstract class ClientTickEvents {
    private static final ClientTickEvents IMPL = ServiceUtil.load(ClientTickEvents.class);

    /**
     * Registers a {@link StartCallback} to be called at the start of every
     * client tick.
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static void registerStart(@NotNull StartCallback callback) {
        IMPL.registerStartImpl(callback);
    }

    /**
     * Registers a {@link EndCallback} to be called at the end of every client
     * tick.
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static void registerEnd(@NotNull EndCallback callback) {
        IMPL.registerEndImpl(callback);
    }

    /**
     * Callback for the start of the client's tick loop.
     *
     * <p><h2>Warning</h2>The client tick loop is a very hot code path, so any
     * callback registered should ensure as little time as possible is spent
     * executing.</p>
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    @FunctionalInterface
    public interface StartCallback {
        void startClientTick(Minecraft client);
    }

    /**
     * Callback for the end of the client's tick loop.
     *
     * <p>Since there is a time gap before the next tick, this is a great place
     * to run any asynchronous operations for the next tick.</p>
     *
     * <p><h2>Warning</h2>The client tick loop is a very hot code path, so any
     * callback registered should ensure as little time as possible is spent
     * executing.</p>
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    @FunctionalInterface
    public interface EndCallback {
        void endClientTick(Minecraft client);
    }

    protected abstract void registerStartImpl(@NotNull StartCallback callback);
    protected abstract void registerEndImpl(@NotNull EndCallback callback);
}
