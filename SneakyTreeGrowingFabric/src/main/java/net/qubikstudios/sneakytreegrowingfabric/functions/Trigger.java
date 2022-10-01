package net.qubikstudios.sneakytreegrowingfabric.functions;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.Game;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.qubikstudios.sneakytreegrowingfabric.config.MainConfig;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class Trigger {
    public static void meal() {
        ClientTickEvents.END_CLIENT_TICK.register((event) -> {
            if (!event.isLocalServer() || event.isPaused())
                return;
            assert event.player != null;
            Entity entity = Objects.requireNonNull(event.getSingleplayerServer()).getPlayerList().getPlayer(event.player.getUUID());
            run(entity);
        });

        ServerTickEvents.END_SERVER_TICK.register((event) -> {
            if (!event.isDedicatedServer())
                return;
            Entity entity = event.getPlayerList().getPlayer(event.getPlayer);
            run(entity);
        });


    }

    private static void run(Entity entity) {
        if (entity == null || entity.isSpectator())
            return;

        List<?> array;
        if (MainConfig.getBoolean("SneakyTreeGrowing.Custom-Tag.Enable-Custom-Tags")) {
            array = MainConfig.getArray("SneakyTreeGrowing.Custom-Tag.Tag-List");
        } else {
            array = List.of("minecraft:saplings minecraft:crops".split("\s"));
        }
        for (Object tag : array) {
            Core.execute(entity.level, entity, getRadius(tag), getChance(tag), tag.toString());
        }
    }

    private static int getRadius(Object tag) {
        if (tag.toString().contains("minecraft:saplings"))
            return MainConfig.getInt("SneakyTreeGrowing.Tree-Meal-Radius");
        if (tag.toString().contains("minecraft:crops") && MainConfig.getBoolean("SneakyTreeGrowing.Crop-Settings.Enable-Crop-Meal"))
            return MainConfig.getInt("SneakyTreeGrowing.Crop-Settings.Crop-Meal-Radius");
        return 0;
    }

    private static int getChance(Object tag) {
        if (tag.toString().contains("minecraft:saplings"))
            return MainConfig.getInt("SneakyTreeGrowing.Tree-Meal-Chance");
        if (tag.toString().contains("minecraft:crops") && MainConfig.getBoolean("SneakyTreeGrowing.Crop-Settings.Enable-Crop-Meal"))
            return MainConfig.getInt("SneakyTreeGrowing.Crop-Settings.Crop-Meal-Chance");
        return 0;
    }
}
