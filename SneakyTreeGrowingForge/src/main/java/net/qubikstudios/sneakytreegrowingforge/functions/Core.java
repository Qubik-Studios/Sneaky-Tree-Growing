package net.qubikstudios.sneakytreegrowingforge.functions;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.qubikstudios.sneakytreegrowingforge.config.AllowedBlockListConfig;
import net.qubikstudios.sneakytreegrowingforge.config.MainConfig;

import java.util.ArrayList;

import static net.qubikstudios.sneakytreegrowingforge.SneakyTreeGrowingMod.CONFIG;

public class Core {
    public static void execute(LevelAccessor world, Entity entity) {
        double x, y, z;
        double halfValueRaw = CONFIG.treeMealRadius.get() / 2.0;

        if (!entity.isShiftKeyDown()) {
            entity.getPersistentData().putBoolean("isSneaked", false);
            return;
        }

        if (Math.random() < ((float) CONFIG.treeMealChance.get() / 120) && !entity.getPersistentData().getBoolean("isSneaked")) {
            entity.getPersistentData().putBoolean("isSneaked", true);
            for (x = -halfValueRaw; x < halfValueRaw; x++) {
                for (y = -halfValueRaw; y < halfValueRaw; y++) {
                    for (z = -halfValueRaw; z < halfValueRaw; z++) {
                        BlockPos pos = new BlockPos(entity.getX() + x, entity.getY() + y + 1, entity.getZ() + z);
                        ArrayList<String> blocks = AllowedBlockListConfig.COMMON_BLOCKS.blockList.get();
                        for (String target : blocks) {
                            if (!world.getBlockState(pos).isAir())
                                mealLocation(world, entity, target, pos, CONFIG.usePlayerBoneMeal.get(), false);
                        }
                        ArrayList<String> tags = AllowedBlockListConfig.COMMON_BLOCKS.tagList.get();
                        if (!tags.isEmpty()) {
                            for (String target : tags) {
                                if (!world.getBlockState(pos).isAir())
                                    mealLocation(world, entity, target, pos, CONFIG.usePlayerBoneMeal.get(), true);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void mealLocation(LevelAccessor world, Entity entity, String target, BlockPos pos, boolean removeItem, boolean isTag) {
        Component message = new TranslatableComponent("sneakytreegrowing.message.prefix").append(new TranslatableComponent("sneakytreegrowing.message.insufficient.bone_meal"));

        if (!isTag && !world.getBlockState(pos).getBlock().toString().contains(target)) return;
        if (isTag && !BlockTags.getAllTags().getTagOrEmpty(new ResourceLocation(target)).contains((world.getBlockState(pos).getBlock())))
            return;
        Player player = world.getPlayerByUUID(entity.getUUID());
        if (world instanceof Level _level) {
            if (removeItem) {
                boneMealEffect(_level, pos, player);
                assert player != null;
                Inventory playerInventory = player.getInventory();
                if (!playerInventory.contains(new ItemStack(Items.BONE_MEAL))) {
                    player.sendMessage(message, player.getUUID());
                    return;
                }
                for (int i = 0; i < playerInventory.getContainerSize(); i++) {
                    ItemStack stack = playerInventory.getItem(i);
                    if (stack.is(Items.BONE_MEAL)) {
                        stack.shrink(1);
                        break;
                    }
                }
            } else {
                boneMealEffect(_level, pos, player);
            }
        }
    }

    private static void boneMealEffect(Level _level, BlockPos pos, Player player) {
        ItemStack boneMealStack = Items.BONE_MEAL.getDefaultInstance();
        if (BoneMealItem.applyBonemeal(boneMealStack, _level, pos, player)) {
            if (!_level.isClientSide()) _level.levelEvent(2005, pos, 0);
        }
        if ((!player.isCreative() || !player.isSpectator()) && MainConfig.COMMON.removeHunger.get()) {
            FoodData food = player.getFoodData();
            int remove = (food.getFoodLevel() - ((int) (Math.random() * (1)) + 1));
            if (Math.random() < (Math.random() / 75)) return;
            if (food.getFoodLevel() > remove && !food.needsFood()) food.setFoodLevel(remove);
        }
    }
}
