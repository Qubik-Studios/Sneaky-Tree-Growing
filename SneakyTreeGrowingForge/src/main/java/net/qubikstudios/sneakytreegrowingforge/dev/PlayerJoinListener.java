package net.qubikstudios.sneakytreegrowingforge.dev;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerJoinListener {
    public static void start(EntityJoinWorldEvent event) {
        Component message = new TranslatableComponent("sneakytreegrowing.message.prefix").append(new TranslatableComponent("sneakytreegrowing.message.devbuild"));
        if (event.getEntity() instanceof Player player) {
            player.sendMessage(message, player.getUUID());
        }
    }
}