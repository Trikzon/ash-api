package com.diontryban.ash.api.modloader.quilt;

import com.diontryban.ash.api.modloader.CommonModInitializer;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import java.util.function.Supplier;

public abstract class QuiltModInitializer implements ModInitializer {
    protected CommonModInitializer commonModInitializer;

    protected QuiltModInitializer(@Nullable Supplier<CommonModInitializer> commonModInitializer) {
        if (commonModInitializer != null) {
            this.commonModInitializer = commonModInitializer.get();
        }
    }

    @Override
    public void onInitialize(ModContainer mod) {
        if (commonModInitializer != null) {
            commonModInitializer.onInitialize();
        }
    }
}
