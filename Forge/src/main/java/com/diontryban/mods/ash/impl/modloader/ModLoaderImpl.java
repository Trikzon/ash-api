package com.diontryban.mods.ash.impl.modloader;

import com.diontryban.mods.ash.api.modloader.ModLoader;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ModLoaderImpl extends ModLoader {
    @Override
    protected String getNameImpl() {
        return "forge";
    }

    @Override
    protected boolean isModLoadedImpl(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    protected boolean isDevelopmentEnvironmentImpl() {
        return !FMLLoader.isProduction();
    }

    @Override
    protected Path getGameDirImpl() {
        return FMLPaths.GAMEDIR.get();
    }

    @Override
    protected Path getConfigDirImpl() {
        return FMLPaths.CONFIGDIR.get();
    }
}
