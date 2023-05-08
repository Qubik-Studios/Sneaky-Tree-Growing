package net.qubikstudios.sneakytreegrowingfabric.config;

import net.qubikstudios.sneakytreegrowingfabric.SneakyTreeGrowingMod;
import org.jetbrains.annotations.NotNull;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllowedBlockListConfig {
    private static final File CONF_LOC = new File("config/Qubik Studios Mods/SneakyTreeGrowing/blockList.yaml");
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
            SneakyTreeGrowingMod.LOGGER.error("Config can not be loaded/created.");
        }

        yamlFile.setComment("Allowed-Blocks.Block-List", """
                Here you can add every block you like to get a Bone Meal effect applied. Leave empty to disable.
                """);
        final ArrayList<String> block_list = new ArrayList<>(Arrays.asList(
                "minecraft:bamboo", "minecraft:brown_mushroom", "minecraft:red_mushroom",
                "minecraft:wheat_seeds", "minecraft:carrots", "minecraft:potatoes", "minecraft:beetroots",
                "minecraft:melon_stem", "minecraft:pumpkin_stem", "minecraft:big_dripleaf", "minecraft:small_dripleaf"
        ));
        yamlFile.addDefault("Allowed-Blocks.Block-List", block_list);


        yamlFile.setComment("Allowed-Blocks.Tag-List", """
                Here you can add block Tags for easier use of this mod. Leave empty to disable.
                """);
        final ArrayList<String> defaultTagList = new ArrayList<>(Arrays.asList(
                "minecraft:crops", "minecraft:saplings"
        ));
        yamlFile.addDefault("Allowed-Blocks.Tag-List", defaultTagList);

        try {
            yamlFile.save();
        } catch (final IOException e) {
            SneakyTreeGrowingMod.LOGGER.error("Config creation failed!");
        }
    }


    public static @NotNull ArrayList<?> getArray(String value) {
        try {
            yamlFile.load();
        } catch (final Exception e) {
            SneakyTreeGrowingMod.LOGGER.error("Config entry cant be loaded!");
        }
        List<?> list =yamlFile.getList(value);
        ArrayList<String> array = (ArrayList<String>) new ArrayList<>(list);
        return array;
    }
}
