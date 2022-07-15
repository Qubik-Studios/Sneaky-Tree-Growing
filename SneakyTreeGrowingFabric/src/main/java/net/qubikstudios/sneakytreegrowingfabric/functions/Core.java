package net.qubikstudios.sneakytreegrowingfabric.functions;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.qubikstudios.sneakytreegrowingfabric.SneakyTreeGrowingMod;

public class Core {

    private static final ItemStack BONE_MEAL = new ItemStack(Items.BONE_MEAL);


    public static void execute(LevelAccessor world, Entity entity, Integer radius, double chance, String tag) {
        double x;
        double y;
        double z;
        double value_raw;
        value_raw = radius;



        if (!entity.isShiftKeyDown()) {
            entity.removeTag("isSneaking");
            return;
        }

        if (Math.random() < (chance / 100) && !entity.getTags().contains("isSneaking")) {
            entity.addTag("isSneaking");
            x = value_raw / (-2);
            for (int a = 0; a < (int) (value_raw); a++) {
                y = value_raw / (-2);
                for (int b = 0; b < (int) (value_raw); b++) {
                    z = value_raw / (-2);
                    for (int c = 0; c < (int) (value_raw); c++) {
                        BlockPos pos = new BlockPos((int) (entity.getX() + x), (int) (entity.getY() + y), (int) (entity.getZ() + z));
                        if (world.getBlockState(pos).getBlock() != Blocks.AIR) {
                            if (world instanceof Level _level && world.getBlockState(pos).getTags().toList().toString().contains(tag)) {
                                if (BoneMealItem.growCrop(new ItemStack(Items.BONE_MEAL), _level, pos)) {
                                    if (!_level.isClientSide()) _level.levelEvent(2005, pos, 0);
                                }
                            }
                        }
                        z = z + 1;
                    }
                    y = y + 1;
                }
                x = x + 1;
            }
        }
    }
}
