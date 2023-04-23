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

package com.diontryban.ash.api.client.gui.screens;

import com.diontryban.ash.api.options.ModOptions;
import com.diontryban.ash.api.options.ModOptionsManager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Convenience abstract class for making a mod options screen that is simply a
 * scrollable list of mod options.
 *
 * <p>See also {@link ModOptionsScreenRegistry}.</p>
 */
public abstract class ModOptionsScreen<T extends ModOptions> extends Screen {
    protected final Screen parent;
    protected final ModOptionsManager<T> options;
    protected OptionsList list;

    public ModOptionsScreen(Component title, ModOptionsManager<T> options, Screen parent) {
        super(title);
        this.options = options;
        this.parent = parent;
    }

    @Override
    protected void init() {
        if (this.minecraft == null) { return; }

        this.options.read();

        this.list = new OptionsList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        this.addOptions();

        this.addWidget(this.list);

        this.addRenderableWidget(Button.builder(
                CommonComponents.GUI_DONE,
                button -> this.minecraft.setScreen(this.parent)
        ).bounds(this.width / 2 - 100, this.height - 27, 200, 20).build());
    }

    /**
     * Add options to the {@link ModOptionsScreen#list} to be displayed.
     */
    protected abstract void addOptions();

    @Override
    public void removed() {
        this.options.write();
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(this.parent);
        }
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        this.list.render(poseStack, mouseX, mouseY, partialTick);
        drawCenteredString(poseStack, this.font, this.title, this.width / 2, 20, 16777215);
        super.render(poseStack, mouseX, mouseY, partialTick);
    }
}
