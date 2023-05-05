package com.diontryban.ash.compat;

import com.diontryban.ash.impl.client.gui.screens.ModOptionsScreenRegistryImpl;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import java.util.HashMap;
import java.util.Map;

public class AshQuiltModMenuEntrypoint implements ModMenuApi {
    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        Map<String,ConfigScreenFactory<?>> result = new HashMap<>();

        ModOptionsScreenRegistryImpl.MOD_OPTIONS_SCREENS.forEach((modId, factory) -> result.put(modId, factory::apply));

        return result;
    }
}
