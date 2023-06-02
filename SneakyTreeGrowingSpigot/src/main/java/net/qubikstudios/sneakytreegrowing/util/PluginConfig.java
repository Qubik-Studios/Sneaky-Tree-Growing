package net.qubikstudios.sneakytreegrowing.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class PluginConfig extends YamlConfiguration {

    public boolean getBoolean(ConfigNames configName) {
        return super.getBoolean(configName.getName());
    }

    public int getInt(ConfigNames configName) {
        return super.getInt(configName.getName());
    }

    public double getDouble(ConfigNames configName) {
        return super.getDouble(configName.getName());
    }

    public List<String> getStringList(ConfigNames configName) {
        return super.getStringList(configName.getName());
    }

    public void set(ConfigNames configName, Object value) {
        super.set(configName.getName(), value);
    }

    public static PluginConfig loadConfiguration(File file){
        Objects.requireNonNull(file, "File can not be null");

        PluginConfig config = new PluginConfig();

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, e);
        }

        return config;
    }
}
