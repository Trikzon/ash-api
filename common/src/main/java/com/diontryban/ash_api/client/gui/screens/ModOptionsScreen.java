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
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
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
public abstract class ModOptionsScreen<T extends ModOptions> extends Screen {
    @ApiStatus.AvailableSince("20.2.0-beta")
    protected final Screen parent;
    @ApiStatus.AvailableSince("20.2.0-beta")
    protected final @NotNull ModOptionsManager<T> options;
    @ApiStatus.AvailableSince("20.2.0-beta")
    protected OptionsList list;

    @ApiStatus.AvailableSince("20.2.0-beta")
    public ModOptionsScreen(@NotNull Component title, @NotNull ModOptionsManager<T> options, Screen parent) {
        super(title);
        this.options = options;
        this.parent = parent;
    }

    @Override
    protected void init() {
        if (minecraft == null) { return; }

        options.read();

        list = new OptionsList(minecraft, width, height - 64, 32, 25);
        addOptions();

        addWidget(list);

        addRenderableWidget(Button.builder(
                CommonComponents.GUI_DONE,
                button -> minecraft.setScreen(parent)
        ).bounds(width / 2 - 100, height - 27, 200, 20).build());
    }

    @ApiStatus.AvailableSince("20.2.0-beta")
    protected abstract void addOptions();

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

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        list.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 16777215);
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderDirtBackground(graphics);
    }
}
