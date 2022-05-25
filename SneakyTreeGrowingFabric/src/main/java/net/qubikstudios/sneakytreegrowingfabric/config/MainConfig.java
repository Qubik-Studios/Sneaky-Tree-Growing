package net.qubikstudios.sneakytreegrowingfabric.config;

import net.qubikstudios.sneakytreegrowingfabric.SneakyTreeGrowingMod;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class MainConfig {

    private static final File CONF_LOC = new File("config/Qubik Studios Mods/sneakytreegrowing.yaml");
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

        yamlFile.setComment("SneakyTreeGrowing.Crop-Settings.Enable-Crop-Meal", """
                Changing this value to true will allow the mod to apply the bonemeal effect to crops like wheat and potato. Only works on Vannila plants
                Default value: false""");
        yamlFile.addDefault("SneakyTreeGrowing.Crop-Settings.Enable-Crop-Meal", false);
        yamlFile.setComment("SneakyTreeGrowing.Crop-Settings.Crop-Meal-Radius", """
                Increasing this value will change the area-of-effect from the crop meal effect
                Default value: 6
                Range: 1 ~ 25""");
        yamlFile.addDefault("SneakyTreeGrowing.Crop-Settings.Crop-Meal-Radius", 6);
        yamlFile.setComment("SneakyTreeGrowing.Crop-Settings.Crop-Meal-Chance", """
                Changing this value will change the chance if a bonemeal effect gets applied to crops or not
                Default value: 5
                Range: 1 ~ 50""");
        yamlFile.addDefault("SneakyTreeGrowing.Crop-Settings.Crop-Meal-Chance", 5);

        yamlFile.setComment("SneakyTreeGrowing.Custom-Tag.Enable-Custom-Tags", """
                Enable Custom tag support for the mod.
                Default value: false""");
        yamlFile.addDefault("SneakyTreeGrowing.Custom-Tag.Enable-Custom-Tags", false);
        yamlFile.setComment("SneakyTreeGrowing.Custom-Tag.Custom-Tags-Meal-Radius", """
                Increasing this value will change the area-of-effect from the custom-tag meal effect
                Default Value: 6
                Range: 1 ~ 1000""");
        yamlFile.addDefault("SneakyTreeGrowing.Custom-Tag.Custom-Tags-Meal-Radius", 6);
        yamlFile.setComment("SneakyTreeGrowing.Custom-Tag.Custom-Tags-Meal-Chance", """
                Changing this value will change the chance if a bonemeal effect gets applied to custom tags or not
                Default Value: 15
                Range: 1 ~ 1000""");
        yamlFile.addDefault("SneakyTreeGrowing.Custom-Tag.Custom-Tags-Meal-Chance", 15);
        yamlFile.setComment("SneakyTreeGrowing.Custom-Tags", """
                All values added in this list will result in support for the bonemeal effect""");
        final java.util.List<String> list = java.util.List.of("example:tag1 example:tag2".split("[\\s]+"));
        yamlFile.addDefault("SneakyTreeGrowing.Custom-Tags", list);

        try {
            yamlFile.save();
        } catch (final IOException e) {
            SneakyTreeGrowingMod.LOGGER.error("Config creation failed!");
        }
    }

    public static boolean getBoolean(String value) {
        try {
            yamlFile.load();
        } catch (final Exception e) {
            SneakyTreeGrowingMod.LOGGER.error("Config entry cant be loaded!");
        }
        return yamlFile.getBoolean(value);
    }

    public static int getInt(String value) {
        try {
            yamlFile.load();
        } catch (final Exception e) {
            SneakyTreeGrowingMod.LOGGER.error("Config entry cant be loaded!");
        }
        return yamlFile.getInt(value, 0);
    }

    public static List<?> getArray(String value) {
        try {
            yamlFile.load();
        } catch (final Exception e) {
            SneakyTreeGrowingMod.LOGGER.error("Config entry cant be loaded!");
        }
        return yamlFile.getList(value);
    }


}
