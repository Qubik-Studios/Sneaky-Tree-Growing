package net.qubikstudios.sneakytreegrowing.functions;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.qubikstudios.sneakytreegrowing.config.MainConfig;

@Mod.EventBusSubscriber
public class Main {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (!event.player.isSpectator()) {
            if (event.phase == TickEvent.Phase.END) {
                Entity entity = event.player;

                if (MainConfig.COMMON.cropMealRule.get()) Cropmealer.execute(entity.level, entity);

                if (MainConfig.COMMON.enableCustomTag.get()) CustomtagMealer.execute(entity.level, entity);

                Treemealer.execute(entity.level, entity);
            }
        }
    }
}
