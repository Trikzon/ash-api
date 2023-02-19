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

package com.diontryban.mods.ash.api.client.event;

import com.diontryban.mods.ash.ImplementationLoader;
import net.minecraft.client.Minecraft;

public abstract class ClientTickEvents {
    private static final ClientTickEvents IMPL = ImplementationLoader.load(ClientTickEvents.class);

    /**
     * Registers a {@link StartCallback} to be called at the start of every
     * client tick.
     */
    public static void registerStart(StartCallback callback) {
        IMPL.registerStartImpl(callback);
    }

    /**
     * Registers a {@link EndCallback} to be called at the end of every client
     * tick.
     */
    public static void registerEnd(EndCallback callback) {
        IMPL.registerEndImpl(callback);
    }

    /**
     * Callback for the start of the client's tick loop.
     *
     * <h2>Warning</h2>
     * <p>The client tick loop is a very hot code path, so any callback
     * registered should ensure as little time as possible is spent
     * executing.</p>
     */
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
     * <h2>Warning</h2>
     * <p>The client tick loop is a very hot code path, so any callback
     * registered should ensure as little time as possible is spent
     * executing.</p>
     */
    @FunctionalInterface
    public interface EndCallback {
        void endClientTick(Minecraft client);
    }

    protected abstract void registerStartImpl(StartCallback callback);
    protected abstract void registerEndImpl(EndCallback callback);
}
