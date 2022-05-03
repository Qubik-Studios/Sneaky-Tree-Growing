package net.qubikstudios.sneakytreegrowing.functions;

import net.minecraft.commands.arguments.blocks.BlockStateParser;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import net.qubikstudios.sneakytreegrowing.config.MainConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Cropmealer {
    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null) return;
        double na;
        double nb;
        double nc;
        double value_raw;
        double chance_raw;

        if (MainConfig.COMMON.cropMealRadius.get() < 1) {
            value_raw = 2;
        } else {
            value_raw = MainConfig.COMMON.cropMealRadius.get();
        }
        if (MainConfig.COMMON.cropMealRadius.get() > 50) {
            chance_raw = 50;
        } else {
            chance_raw = MainConfig.COMMON.cropMealRadius.get();
        }

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
                                if (world instanceof Level _level && world.getBlockState(pos).getTags().toList().contains(BlockTags.CROPS)) {
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