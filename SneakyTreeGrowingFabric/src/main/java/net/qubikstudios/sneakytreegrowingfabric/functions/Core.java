package net.qubikstudios.sneakytreegrowingfabric.functions;

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
import net.qubikstudios.sneakytreegrowingfabric.SneakyTreeGrowingMod;

public class Core {

    // Custom implementation of Forges applBonemeal function
    private static boolean applyBonemealEffect(LevelAccessor world, BlockPos pos) {
        if (world instanceof Level _level) {
            SneakyTreeGrowingMod.LOGGER.info("Trying... \nTargeted Block:");
            BlockState blockstate = _level.getBlockState(pos);
            SneakyTreeGrowingMod.LOGGER.info(blockstate.getBlock().toString() + "| X:" + pos.getX() + " Y:" + pos.getY() + " Z:" + pos.getZ());
            if (blockstate.getBlock() instanceof BonemealableBlock bonemealableblock) {
                if (bonemealableblock.isValidBonemealTarget(_level, pos, blockstate, _level.isClientSide)) {
                    if (bonemealableblock.isBonemealSuccess(_level, RandomSource.create(), pos, blockstate)) {
                        BoneMealItem.growCrop(null, _level, pos);
                        BoneMealItem.growWaterPlant(null, _level, pos, Direction.UP);
                        BoneMealItem.addGrowthParticles(_level, pos, 0);
                    }
                    SneakyTreeGrowingMod.LOGGER.info("Success!");
                    return true;
                }
            }
            SneakyTreeGrowingMod.LOGGER.info("Failed!");
            return false;
        }
        return false;
    }

    public static void execute(LevelAccessor world, Entity entity, Integer radius, double chance, String tag) {
        double x;
        double y;
        double z;
        double value_raw;
        value_raw = radius;

        ItemStack BONE_MEAL = new ItemStack(Items.BONE_MEAL);

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
                                if (applyBonemealEffect(world, pos)) {
                                    if (!_level.isClientSide) _level.levelEvent(2005, pos, 0);
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
