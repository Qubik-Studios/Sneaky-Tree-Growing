package net.qubikstudios.sneakytreegrowing.functions;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.qubikstudios.sneakytreegrowing.config.MainConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomtagMealer {

    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null) return;
        double na;
        double nb;
        double nc;
        double value_raw = 0;
        double chance_raw = 0;

        if (MainConfig.COMMON.customTagMealRadius.get() < 1) {
            value_raw = 2;
        } else {
            value_raw = MainConfig.COMMON.customTagMealRadius.get();
        }
        if (MainConfig.COMMON.customTagMealChance.get() > 50) {
            chance_raw = 50;
        } else {
            chance_raw = MainConfig.COMMON.customTagMealChance.get();
        }
        for (String s : MainConfig.COMMON.customTag.get()) {
            if (entity.isShiftKeyDown()) {
                if (Math.random() < (chance_raw / 100) && entity.getPersistentData().getDouble("isSneaked") == 0) {
                    entity.getPersistentData().putDouble("isSneaked", 1);
                    na = value_raw / (-2);
                    for (int a = 0; a < (int) (value_raw); a++) {
                        nb = value_raw / (-2);
                        for (int b = 0; b < (int) (value_raw); b++) {
                            nc = value_raw / (-2);
                            for (int c = 0; c < (int) (value_raw); c++) {
                                BlockPos pos = new BlockPos((int) (entity.getX() + na), (int) (entity.getY() + nb), (int) (entity.getZ() + nc));
                                if (world.getBlockState(pos).getBlock() != Blocks.AIR) {
                                    if (world instanceof Level _level && world.getBlockState(pos).getTags().toList().toString().contains(s)) {
                                        if (BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), _level, pos, null)) {
                                            if (!_level.isClientSide())
                                                _level.levelEvent(2005, pos, 0);
                                        }
                                    }
                                }
                                nc = nc + 1;
                            }
                            nb = nb + 1;
                        }
                        na = na + 1;
                    }
                }
            } else if (!entity.isShiftKeyDown()) {
                entity.getPersistentData().putDouble("isSneaked", 0);
            }
        }
    }
}