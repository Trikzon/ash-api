/*
 * This file is part of MultiLoader Template. A copy of this program can be
 * found at https://github.com/Trikzon/MultiLoader-Template.
 * Copyright (C) 2023 Dion Tryban
 *
 * MultiLoader Template is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * MultiLoader Template is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MultiLoader Template. If not, see <https://www.gnu.org/licenses/>.
 */
package com.diontryban.mods.example.mixin.client;

import com.diontryban.mods.example.ExampleMod;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ExampleMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        ExampleMod.LOG.info("This line is printed by an example mod mixin from Common!");
    }
}
