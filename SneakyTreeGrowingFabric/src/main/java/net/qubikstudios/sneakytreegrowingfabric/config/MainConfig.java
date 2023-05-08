package net.qubikstudios.sneakytreegrowingfabric.config;

import net.qubikstudios.sneakytreegrowingfabric.SneakyTreeGrowingMod;
import org.jetbrains.annotations.NotNull;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;
import java.io.IOException;


public class MainConfig {

    private static final File CONF_LOC = new File("config/Qubik Studios Mods/SneakyTreeGrowing/main.yaml");
    private static final YamlFile yamlFile = new YamlFile(CONF_LOC);

    public static void init() {

        try {
            if (!yamlFile.exists()) {
                SneakyTreeGrowingMod.LOGGER.info("Config file not found! Starting creation!");
                yamlFile.createNewFile(true);
            } else {
                SneakyTreeGrowingMod.LOGGER.info("Config already exist! Skipping creation. >> " + yamlFile.getFilePath());
            }
            yamlFile.load();
        } catch (final Exception e) {
            SneakyTreeGrowingMod.LOGGER.info("Config can not be loaded/created.");
        }


        yamlFile.setComment("SneakyTreeGrowing.Tree-Meal-Radius", """
                Increasing this value will change the area-of-effect from the mod
                Default value: 6
                Range: 1 ~ 1000""");
        yamlFile.addDefault("SneakyTreeGrowing.Tree-Meal-Radius", 6);
        yamlFile.setComment("SneakyTreeGrowing.Tree-Meal-Chance", """
                Changing this value will change the chance if a bonemeal effect gets applied or not
                Default value: 15
                Range: 1 ~ 100""");
        yamlFile.addDefault("SneakyTreeGrowing.Tree-Meal-Chance", 15);

        yamlFile.setComment("In-Dev.Remove-Hunger", """
                Removes more Hunger when sneaking and effect gets applied
                Default value: true
                			""");
        yamlFile.addDefault("In-Dev.Remove-Hunger", true);
        yamlFile.setComment("In-Dev.Use-Inventory-Bone-Meal", """
                Removes one Bone Meal from player inventory when Bone Meal gets applied over Sneaky Tree Growing
                Default value: false
                			""");
        yamlFile.addDefault("In-Dev.Use-Inventory-Bone-Meal", false);

        try {
            yamlFile.save();
        } catch (final IOException e) {
            SneakyTreeGrowingMod.LOGGER.error("Config creation failed!");
        }
    }

    public static @NotNull Boolean getBoolean(String value) {
        try {
            yamlFile.load();
        } catch (final Exception e) {
            SneakyTreeGrowingMod.LOGGER.error("Config entry cant be loaded!");
        }
        return yamlFile.getBoolean(value);
    }

    public static @NotNull Integer getInt(String value) {
        try {
            yamlFile.load();
        } catch (final Exception e) {
            SneakyTreeGrowingMod.LOGGER.error("Config entry cant be loaded!");
        }
        return yamlFile.getInt(value, 0);
    }
}
