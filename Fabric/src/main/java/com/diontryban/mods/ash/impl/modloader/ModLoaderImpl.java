package com.diontryban.mods.ash.impl.modloader;

import com.diontryban.mods.ash.api.modloader.ModLoader;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class ModLoaderImpl extends ModLoader {
    @Override
    protected String getNameImpl() {
        return "fabric";
    }

    @Override
    protected boolean isModLoadedImpl(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    protected boolean isDevelopmentEnvironmentImpl() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    protected Path getGameDirImpl() {
        return FabricLoader.getInstance().getGameDir();
    }

    @Override
    protected Path getConfigDirImpl() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
