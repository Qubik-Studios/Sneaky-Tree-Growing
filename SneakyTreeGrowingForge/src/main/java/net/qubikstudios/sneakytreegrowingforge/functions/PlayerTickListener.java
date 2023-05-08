package net.qubikstudios.sneakytreegrowingforge.functions;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerTickListener {

    public static void start(TickEvent.PlayerTickEvent event) {
        if (event.player == null || event.player.isSpectator() || event.phase != TickEvent.Phase.END) return;
        Entity entity = event.player;
        Core.execute(entity.level, entity);
    }
}