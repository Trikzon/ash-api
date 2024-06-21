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

@ApiStatus.AvailableSince("21.0.0-beta")
@ApiStatus.NonExtendable
public abstract class ClientTickEvent {
    private static final ClientTickEvent IMPL = ServiceUtil.load(ClientTickEvent.class);

    /**
     * Callback for the before the client's tick.
     *
     * <p><b>Warning: </b>The client tick loop is a very hot code path, so any
     * callback registered should ensure as little time as possible is spent
     * executing.</p>
     */
    @ApiStatus.AvailableSince("21.0.0-beta")
    @FunctionalInterface
    public interface Pre {
        void preClientTick(Minecraft client);

        /**
         * Registers a {@link ClientTickEvent.Pre} to be called before every
         * client tick.
         */
        @ApiStatus.AvailableSince("21.0.0-beta")
        static void register(@NotNull ClientTickEvent.Pre callback) {
            IMPL.registerPreImpl(callback);
        }
    }

    /**
     * Callback for the after the client's tick.
     *
     * <p><b>Warning: </b>The client tick loop is a very hot code path, so any
     * callback registered should ensure as little time as possible is spent
     * executing.</p>
     */
    @ApiStatus.AvailableSince("21.0.0-beta")
    @FunctionalInterface
    public interface Post {
        void postClientTick(Minecraft client);

        /**
         * Registers a {@link ClientTickEvent.Post} to be called after every
         * client tick.
         */
        @ApiStatus.AvailableSince("21.0.0-beta")
        static void register(@NotNull ClientTickEvent.Post callback) {
            IMPL.registerPostImpl(callback);
        }
    }

    protected abstract void registerPreImpl(@NotNull ClientTickEvent.Pre callback);
    protected abstract void registerPostImpl(@NotNull ClientTickEvent.Post callback);
}
