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

package com.diontryban.ash.api.options;

import com.diontryban.ash.api.modloader.ModLoader;
import com.diontryban.ash.Ash;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ModOptionsManager<O extends ModOptions> {
    private final String modId;
    private final Class<O> optionsClass;
    private final File file;
    private O options;

    /**
     * Creates a {@link ModOptionsManager} for a given {@link ModOptions}.
     *
     * <p>Handles the reading and writing of the mod option's file. This file
     * will be saved as mod_id.json in the game's configuration directory.</p>
     *
     * @param modId the mod's mod id
     * @param optionsClass the class of the mod's {@link ModOptions}
     */
    public ModOptionsManager(String modId, Class<O> optionsClass) {
        this.modId = modId;
        this.optionsClass = optionsClass;
        this.file = ModLoader.getConfigDir().resolve(modId + ".json").toFile();
        read();
    }

    private O defaultOptions() {
        try {
            return optionsClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Config for mod " + modId + " does not have an empty constructor.");
        }
    }

    /**
     * Reads the mod's options file. If it does not exist or can not be read,
     * it creates a new file using the default options.
     */
    public void read() {
        if (!file.exists()) {
            options = defaultOptions();
            write();
        }

        try (FileReader reader = new FileReader(file)) {
            Gson gson = new Gson();
            options = gson.fromJson(reader, optionsClass);

            if (options.version < defaultOptions().getVersion()) {
                Ash.LOG.info("Found deprecated config file for mod " + modId + ". Updating.");
                options = defaultOptions();
                write();
            }

        } catch (IOException e) {
            Ash.LOG.error("Failed to read " + modId + "'s config file.");
            options = defaultOptions();
            write();
        }
    }

    /**
     * Writes the mod's options class's data to the mod's options file.
     */
    public void write() {
        try (FileWriter writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(options));
            writer.flush();
        } catch (IOException e) {
            Ash.LOG.error("Failed to write to config file for mod " + modId + ".");
        }
    }

    /**
     * @return the current instance of the mod's options class.
     */
    public O get() {
        return this.options;
    }

    /**
     * @return the mod's mod id
     */
    public String getModId() {
        return modId;
    }
}
