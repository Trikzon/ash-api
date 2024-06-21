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

import com.diontryban.ash_api.options.ModOptions;
import com.diontryban.ash_api.options.ModOptionsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Convenience abstract class for making a mod options screen that is simply a
 * scrollable list of {@link com.diontryban.ash_api.options.ModOptions}.
 *
 * <p>See also {@link ModOptionsScreenRegistry}.</p>
 */
@ApiStatus.AvailableSince("20.2.0-beta")
public abstract class ModOptionsScreen<T extends ModOptions> extends OptionsSubScreen {
    @ApiStatus.AvailableSince("20.2.0-beta")
    protected final Screen parent;
    @ApiStatus.AvailableSince("20.2.0-beta")
    protected final @NotNull ModOptionsManager<T> options;
    @ApiStatus.AvailableSince("20.2.0-beta")
    protected OptionsList list;

    @ApiStatus.AvailableSince("20.2.0-beta")
    public ModOptionsScreen(@NotNull Component title, @NotNull ModOptionsManager<T> options, Screen parent) {
        super(parent, Minecraft.getInstance().options, title);
        this.options = options;
        this.parent = parent;
    }

    @ApiStatus.AvailableSince("20.2.0-beta")
    protected abstract void addOptions();

    @Override
    protected void init() {
        if (minecraft == null) { return; }

        options.read();
        list = new OptionsList(minecraft, this.width, this);
        addOptions();
        addRenderableWidget(list);

        super.init();
    }

    @Override
    protected void repositionElements() {
        super.repositionElements();
        this.list.updateSize(this.width, this.layout);
    }

    @Override
    public void removed() {
        options.write();
    }

    @Override
    public void onClose() {
        if (minecraft != null) {
            minecraft.setScreen(parent);
        }
    }
}
