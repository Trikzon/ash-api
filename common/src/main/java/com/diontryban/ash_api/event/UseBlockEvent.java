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

package com.diontryban.ash_api.event;

import com.diontryban.ash_api.ServiceUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.AvailableSince("20.2.0-beta")
@ApiStatus.NonExtendable
public abstract class UseBlockEvent {
    private static final UseBlockEvent IMPL = ServiceUtil.load(UseBlockEvent.class);

    /**
     * Registers a {@link UseBlockCallback} to be called when a block is "used."
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    public static void register(UseBlockCallback callback) {
        IMPL.registerImpl(callback);
    }

    /**
     * Callback for right-clicking ("using") a block. It is called before the
     * spectator check, so check for the player's game mode.
     *
     * <p>Returns an {@link InteractionResult}. If
     * {@link InteractionResult#consumesAction()} is true, it will prevent
     * further {@link UseBlockCallback} events from being processed for that
     * event. Returning {@link InteractionResult#SUCCESS} will cause the player
     * to swing its hand.</p>
     *
     * <p>It is fired on both the client and the server. Note that handling
     * things differently on either side may cause de-sync!</p>
     */
    @ApiStatus.AvailableSince("20.2.0-beta")
    @FunctionalInterface
    public interface UseBlockCallback {
        InteractionResult useBlock(Player player, Level level, InteractionHand hand, BlockHitResult hitResult);
    }

    protected abstract void registerImpl(UseBlockCallback callback);
}
