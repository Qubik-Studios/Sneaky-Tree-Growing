package net.qubikstudios.sneakytreegrowingforge.functions;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import net.qubikstudios.sneakytreegrowingforge.config.MainConfig;

@Mod.EventBusSubscriber
public class Trigger {
    public static void treeMealer(TickEvent.PlayerTickEvent event) {
        if (event.player == null || event.player.isSpectator() || event.phase != TickEvent.Phase.END) return;
        Entity entity = event.player;
        Core.execute(entity.level, entity, MainConfig.COMMON.treeMealRadius.get(),
                MainConfig.COMMON.treeMealChance.get(), "minecraft:saplings");
    }

    public static void cropMealer(TickEvent.PlayerTickEvent event) {
        if (event.player == null || event.player.isSpectator() || event.phase != TickEvent.Phase.END) return;
        Entity entity = event.player;
        Core.execute(entity.level, entity, MainConfig.COMMON.cropMealRadius.get(),
                MainConfig.COMMON.cropMealChance.get(), "minecraft:crops");
    }

    public static void customTagMealer(TickEvent.PlayerTickEvent event) {
        if (event.player == null || event.player.isSpectator() || event.phase != TickEvent.Phase.END) return;
        Entity entity = event.player;
        for (String tag : MainConfig.COMMON.customTag.get()) {
            Core.execute(entity.level, entity, MainConfig.COMMON.customTagMealRadius.get(),
                    MainConfig.COMMON.customTagMealChance.get(), tag);
        }
    }
}