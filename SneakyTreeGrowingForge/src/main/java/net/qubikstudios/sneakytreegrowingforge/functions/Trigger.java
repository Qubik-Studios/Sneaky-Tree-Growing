package net.qubikstudios.sneakytreegrowingforge.functions;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import net.qubikstudios.sneakytreegrowingforge.config.MainConfig;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber
public class Trigger {

    public static void start(TickEvent.PlayerTickEvent event) {
        if (event.player == null || event.player.isSpectator() || event.phase != TickEvent.Phase.END) return;
        Entity entity = event.player;
        List<?> array;
        if (MainConfig.COMMON.enableCustomTag.get()) {
            array = MainConfig.COMMON.customTag.get();
        } else {
            array = java.util.List.of("minecraft:saplings minecraft:crops".split("\s"));
        }
        for (Object tag : array) {
            Core.execute(entity.level, entity, getRadius(tag), getChance(tag), tag.toString());
        }
    }

    private static int getRadius(Object tag) {
        if (tag.toString().contains("minecraft:saplings")) return MainConfig.COMMON.treeMealRadius.get();
        if (tag.toString().contains("minecraft:crops") && MainConfig.COMMON.enableCropMeal.get())
            return MainConfig.COMMON.cropMealRadius.get();
        return 0;
    }

    private static int getChance(Object tag) {
        if (tag.toString().contains("minecraft:saplings")) return MainConfig.COMMON.treeMealChance.get();
        if (tag.toString().contains("minecraft:crops") && MainConfig.COMMON.enableCropMeal.get())
            return MainConfig.COMMON.cropMealChance.get();
        return 0;
    }

}