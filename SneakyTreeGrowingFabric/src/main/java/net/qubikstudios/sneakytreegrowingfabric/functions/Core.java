package net.qubikstudios.sneakytreegrowingfabric.functions;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.qubikstudios.sneakytreegrowingfabric.config.AllowedBlockListConfig;
import net.qubikstudios.sneakytreegrowingfabric.config.MainConfig;

import java.util.ArrayList;

public class Core {
    public static void execute(LevelAccessor world, Entity entity) {
        double x, y, z;
        double halfValueRaw = MainConfig.getInt("SneakyTreeGrowing.Tree-Meal-Radius") / 2.0;

        if (!entity.isShiftKeyDown()) {
            entity.removeTag("isSneaked");
            return;
        }

        if (Math.random() < ((float) MainConfig.getInt("SneakyTreeGrowing.Tree-Meal-Chance") / 120) && !entity.getTags().contains("isSneaked")) {
            entity.addTag("isSneaked");
            for (x = -halfValueRaw; x < halfValueRaw; x++) {
                for (y = -halfValueRaw; y < halfValueRaw; y++) {
                    for (z = -halfValueRaw; z < halfValueRaw; z++) {
                        BlockPos pos = new BlockPos(entity.getX() + x, entity.getY() + y + 1, entity.getZ() + z);
                        ArrayList<String> blocks = (ArrayList<String>) AllowedBlockListConfig.getArray("Allowed-Blocks.Block-List");
                        if (!blocks.isEmpty()) {
                            for (String target : blocks) {
                                if (!world.getBlockState(pos).isAir())
                                    mealLocation(world, entity, target, pos, MainConfig.getBoolean("In-Dev.Use-Inventory-Bone-Meal"), false);
                            }
                        }
                        ArrayList<String> tags = (ArrayList<String>) AllowedBlockListConfig.getArray("Allowed-Blocks.Tag-List");
                        if (!tags.isEmpty()) {
                            for (String target : tags) {
                                if (!world.getBlockState(pos).isAir())
                                    mealLocation(world, entity, target, pos, MainConfig.getBoolean("In-Dev.Use-Inventory-Bone-Meal"), true);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void mealLocation(LevelAccessor world, Entity entity, String target, BlockPos pos, boolean removeItem, boolean isTag) {
        if (!world.getBlockState(pos).getBlock().toString().contains(target)) return;
        if (isTag && !world.getBlockState(pos).getTags().toList().toString().contains(target)) return;
        Player player = world.getPlayerByUUID(entity.getUUID());
        if (world instanceof Level _level) {
            if (removeItem) {
                boneMealEffect(_level, pos, player);
                assert player != null;
                Inventory playerInventory = player.getInventory();
                if (!playerInventory.contains(new ItemStack(Items.BONE_MEAL)))
                    player.sendSystemMessage(Component.translatable("§8§l[§7Sneaky §6Tree §7Growing§8§l]§c Not enough Bone Meal in Inventory"));
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
        if (BoneMealItem.growCrop(boneMealStack, _level, pos)) {
            if (!_level.isClientSide()) _level.levelEvent(2005, pos, 0);
        }

        if ((!player.isCreative() || !player.isSpectator()) && MainConfig.getBoolean("In-Dev.Remove-Hunger")) {
            FoodData food = player.getFoodData();
            int remove = (food.getFoodLevel() - ((int) (Math.random() * (1)) + 1));
            if (Math.random() < (Math.random() / 75)) return;
            if (food.getFoodLevel() > remove && !food.needsFood()) food.setFoodLevel(remove);
        }
    }
}
