package net.qubikstudios.sneakytreegrowing.functions;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.qubikstudios.sneakytreegrowing.config.MainConfig;

import java.util.Objects;


public class Cropmealer {

    public static void execute(World world, Entity entity) {
        if (entity == null) return;
        double sx;
        double sy;
        double sz;
        double value_neg;
        double value_pos;
        double value_raw = 0;
        double chance_raw = 0;
        double chance;
        if (MainConfig.COMMON.cropMealRadius.get() < 1) {
            if (world != null) {
                value_raw = 2;
            }
        } else {
            value_raw = MainConfig.COMMON.cropMealRadius.get();
        }
        if (MainConfig.COMMON.cropMealChance.get() > 50) {
            if (world != null) {
                chance_raw = 50;
            }
        } else {
            chance_raw = MainConfig.COMMON.cropMealChance.get();
        }
        chance = chance_raw / 150;
        value_pos = value_raw;
        value_neg = value_raw / (-2);
        if (!(new Object() {
            public boolean checkGamemode(Entity _ent) {
                if (_ent instanceof ServerPlayerEntity _serverPlayer) {
                    return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
                } else if (_ent.level.isClientSide() && _ent instanceof PlayerEntity _player) {
                    return Objects.requireNonNull(Minecraft.getInstance().getConnection()).getPlayerInfo(_player.getGameProfile().getId()) != null && Objects.requireNonNull(Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId())).getGameMode() == GameType.SPECTATOR;
                }
                return false;
            }
        }.checkGamemode(entity))) {
            if (entity.isShiftKeyDown()) {
                if (Math.random() < chance && entity.getPersistentData().getDouble("isSneaked") == 0) {
                    entity.getPersistentData().putDouble("isSneaked", 1);
                    sx = value_neg;
                    for (int index0 = 0; index0 < (int) (value_pos); index0++) {
                        sy = value_neg;
                        for (int index1 = 0; index1 < (int) (value_pos); index1++) {
                            sz = value_neg;
                            for (int index2 = 0; index2 < (int) (value_pos); index2++) {
                                if (BlockTags.getAllTags().getTagOrEmpty(new ResourceLocation("minecraft:crops")).contains((world.getBlockState(new BlockPos((int) (entity.getX() + sx), (int) (entity.getY() + sy), (int) (entity.getZ() + sz)))).getBlock()) || BlockTags.getAllTags().getTagOrEmpty(new ResourceLocation("forge:sneakytreegrowingallowed")).contains((world.getBlockState(new BlockPos((int) (entity.getX() + sx), (int) (entity.getY() + sy), (int) (entity.getZ() + sz)))).getBlock())) {
                                    if (world instanceof ServerWorld _level) {
                                        if (BoneMealItem.growCrop(new ItemStack(Items.BONE_MEAL), _level, new BlockPos((int) (entity.getX() + sx), (int) (entity.getY() + sy), (int) (entity.getZ() + sz))) || BoneMealItem.growWaterPlant(new ItemStack(Items.BONE_MEAL), _level, new BlockPos((int) (entity.getX() + sx), (int) (entity.getY() + sy), (int) (entity.getZ() + sz)), null)) {
                                            if (!_level.isClientSide())
                                                _level.levelEvent(2005, new BlockPos((int) (entity.getX() + sx), (int) (entity.getY() + sy), (int) (entity.getZ() + sz)), 0);
                                        }
                                    }
                                }
                                sz = sz + 1;
                            }
                            sy = sy + 1;
                        }
                        sx = sx + 1;
                    }
                }
            } else if (!entity.isShiftKeyDown()) {
                entity.getPersistentData().putDouble("isSneaked", 0);
            }
        }
    }
}
