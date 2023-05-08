package net.qubikstudios.sneakytreegrowingforge.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public final class AllowedBlockListConfig {
    public static final Common COMMON_BLOCKS;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_BLOCKS = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }

    public static class Common {
        private static final ArrayList<String> defaultBlockList = new ArrayList<>(Arrays.asList(
                "minecraft:bamboo", "minecraft:brown_mushroom", "minecraft:red_mushroom",
                "minecraft:wheat_seeds", "minecraft:carrots", "minecraft:potatoes", "minecraft:beetroots",
                "minecraft:melon_stem", "minecraft:pumpkin_stem", "minecraft:big_dripleaf", "minecraft:small_dripleaf"
        ));
        private static final ArrayList<String> defaultTagList = new ArrayList<>(Arrays.asList(
                "minecraft:crops", "minecraft:saplings"
        ));
        public final ForgeConfigSpec.ConfigValue<ArrayList<String>> blockList;
        public final ForgeConfigSpec.ConfigValue<ArrayList<String>> tagList;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("Allowed-Blocks");
            builder.comment("Here you can add every block you like to get a Bone Meal effect applied.");
            this.blockList = builder.define("Block-List", defaultBlockList);
            builder.comment("Here you can add block Tags for easier use of this mod.");
            this.tagList = builder.define("Tag-List", defaultTagList);
            builder.pop();
        }
    }
}
