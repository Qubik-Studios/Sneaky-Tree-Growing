package net.qubikstudios.sneakytreegrowing.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;


public final class MainConfig {


    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }

    public static class Common {
        private static final int defaultTreeMealRadius = 6;
        private static final int defaultTreeMealChance = 15;
        private static final int defaultCropMealRadius = 6;
        private static final int defaultCropMealChance = 5;
        private static final int defaultCustomTagMealRadius = 6;
        private static final int defaultCustomTagMealChance = 15;
        private static final boolean defaultCropMealRule = false;
        private static final boolean defaultEnableCustomTag = false;
        private static final ArrayList<String> defaultTagList = new ArrayList<>();

        public final ForgeConfigSpec.IntValue treeMealRadius;
        public final ForgeConfigSpec.IntValue treeMealChance;
        public final ForgeConfigSpec.IntValue cropMealRadius;
        public final ForgeConfigSpec.IntValue cropMealChance;
        public final ForgeConfigSpec.IntValue customTagMealRadius;
        public final ForgeConfigSpec.IntValue customTagMealChance;
        public final ForgeConfigSpec.BooleanValue cropMealRule;

        public final ForgeConfigSpec.BooleanValue enableCustomTag;
        public final ForgeConfigSpec.ConfigValue<ArrayList<String>> customTag;

        public Common(ForgeConfigSpec.Builder builder) {

            builder.push("SneakyTreeGrowing");
            this.treeMealRadius = builder.comment("Increasing this value will change the area-of-effect from the mod\nDefault value: " + defaultTreeMealRadius)
                    .defineInRange("Tree-Meal-Radius", defaultTreeMealRadius, 1, 1000);
            this.treeMealChance = builder.comment("Changing this value will change the chance if a bonemeal effect gets applied or not\nDefault value: " + defaultTreeMealChance)
                    .defineInRange("Tree-Meal-Chance", defaultTreeMealChance, 1, 100);

            builder.push("Crop-Settings");
            builder.comment("These settings are only used when \"Crop-Meal-Allowed\" is set to true");

            this.cropMealRule = builder.comment("Changing this value to true will allow the mod to apply the bonemeal effect to crops like wheat and potato. Only works on Vannila plants\nDefault value: " + defaultCropMealRule)
                    .define("Crop-Meal-Allowed", defaultCropMealRule);
            this.cropMealRadius = builder.comment("Increasing this value will change the area-of-effect from the crop meal effect\nDefault value: " + defaultCropMealRadius)
                    .defineInRange("Crop-Meal-Radius", defaultCropMealRadius, 1, 25);
            this.cropMealChance = builder.comment("Changing this value will change the chance if a bonemeal effect gets applied to crops or not\nDefault value: " + defaultCropMealChance)
                    .defineInRange("Crop-Meal-Chance", defaultCropMealChance, 1, 50);
            builder.pop();
            builder.push("Custom-Tag");
            builder.comment("Add Custom Tag support for all mods you wish.");

            this.enableCustomTag = builder.comment("Enable Custom tag support for the mod.\nDefault value: " + defaultEnableCustomTag)
                    .define("Enable-Custom-Tags", defaultEnableCustomTag);

            this.customTag = builder.comment("All values added in this list will result in support for the bonemeal effect. \nExample: [\"forge:seeds\", \"minecraft:crops\"]")
                    .define("Custom-Tags", defaultTagList);

            this.customTagMealRadius = builder.comment("Increasing this value will change the area-of-effect from the custom-tag meal effect\nDefault Value: " + defaultCustomTagMealRadius)
                    .defineInRange("Custom-Tags-Meal-Radius", defaultCustomTagMealRadius, 1, 1000);

            this.customTagMealChance = builder.comment("Changing this value will change the chance if a bonemeal effect gets applied to custom tags or not\nDefault Value: " + defaultCustomTagMealChance)
                    .defineInRange("Custom-Tags-Meal-Chance", defaultCustomTagMealChance, 1, 1000);

            builder.pop();
            builder.pop();
        }
    }
}
