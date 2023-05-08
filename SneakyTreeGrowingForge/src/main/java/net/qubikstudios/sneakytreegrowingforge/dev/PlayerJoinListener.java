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
                    Component.translatable(
                            "§8§l[§7Sneaky §6Tree §7Growing§8§l]§a This is a unstable Developer Build. Report any bugs you find." +
                                    " http://discord.qubik-studios.net"
                    )
            );
        }
    }
}
