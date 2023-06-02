package net.qubikstudios.sneakytreegrowingforge.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;


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
        private static final boolean defaultUsePlayerBoneMeal = false;
        private static final boolean defaultRemoveHunger = true;
        public final ForgeConfigSpec.IntValue treeMealRadius;
        public final ForgeConfigSpec.IntValue treeMealChance;
        public final ForgeConfigSpec.BooleanValue usePlayerBoneMeal;
        public final ForgeConfigSpec.BooleanValue removeHunger;

        public Common(ForgeConfigSpec.Builder builder) {

            builder.push("SneakyTreeGrowing");
            this.treeMealRadius = builder.comment("Increasing this value will change the area-of-effect from the mod\nDefault value: " + defaultTreeMealRadius)
                    .defineInRange("Tree-Meal-Radius", defaultTreeMealRadius, 1, 1000);
            this.treeMealChance = builder.comment("Changing this value will change the chance if a Bone Meal effect gets applied or not\nDefault value: " + defaultTreeMealChance)
                    .defineInRange("Tree-Meal-Chance", defaultTreeMealChance, 1, 100);
            builder.comment("Experimental In-Dev Features. Use with caution!");
            this.usePlayerBoneMeal = builder.comment("Removes one Bone Meal from player inventory when Bone Meal gets applied over Sneaky Tree Growing\nDefault value: " + defaultUsePlayerBoneMeal)
                    .define("Use-Inventory-Bone-Meal", defaultUsePlayerBoneMeal);
            this.removeHunger = builder.comment("Removes more Hunger when sneaking and effect gets applied\nDefault value: " + defaultRemoveHunger)
                    .define("Remove-Hunger", defaultRemoveHunger);
            builder.pop();
        }
    }
}
