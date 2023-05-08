package net.qubikstudios.sneakytreegrowingfabric.functions;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Objects;

public class Trigger {
    public static void meal() {
        ClientTickEvents.END_CLIENT_TICK.register((event) -> {
            if (event.player == null || event.player.isSpectator() || event.isPaused() || event.level == null) return;
            Entity entity = Objects.requireNonNull(event.getSingleplayerServer()).getPlayerList().getPlayer(event.player.getUUID());
            Core.execute(entity.level, entity);
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (server.getTickCount() % 20 == 0) {
                for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                    Entity entity = server.getPlayerList().getPlayer(player.getUUID());
                    Core.execute(entity.level, entity);
                }
            }
        });
    }
}
