package net.qubikstudios.sneakytreegrowingforge.dev;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerJoinListener {
    public static void start(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            player.sendSystemMessage(
                    Component.translatable("sneakytreegrowing.message.prefix")
                            .append(Component.translatable("sneakytreegrowing.message.devbuild"))
            );
        }
    }
}
