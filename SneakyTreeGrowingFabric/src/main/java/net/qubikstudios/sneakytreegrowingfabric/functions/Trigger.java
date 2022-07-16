package net.qubikstudios.sneakytreegrowingfabric.functions;

import com.mojang.datafixers.kinds.IdF;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.world.entity.Entity;
import net.qubikstudios.sneakytreegrowingfabric.config.MainConfig;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Trigger {
    private static List<?> array;
    public static void meal() {
        ClientTickEvents.END_CLIENT_TICK.register((event) -> {
            if (!event.isLocalServer() || event.player == null || event.player.isSpectator() || event.isPaused()) return;
            Entity entity = Objects.requireNonNull(event.getSingleplayerServer()).getPlayerList().getPlayer(event.player.getUUID());
            if (MainConfig.getBoolean("SneakyTreeGrowing.Custom-Tag.Enable-Custom-Tags")) {
                array = MainConfig.getArray("SneakyTreeGrowing.Custom-Tag.Tag-List");
            } else {
                array = java.util.List.of("minecraft:saplings minecraft:crops".split("\s"));
            }
            for (Object tag : array) {
                assert entity != null;
                Core.execute(entity.level, entity, getRadius(tag), getChance(tag), tag.toString());
            }
        });
    }

    private static int getRadius(Object tag) {
        if (tag.toString().contains("minecraft:saplings")) return MainConfig.getInt("SneakyTreeGrowing.Tree-Meal-Radius");
        if (tag.toString().contains("minecraft:crops") && MainConfig.getBoolean("SneakyTreeGrowing.Crop-Settings.Enable-Crop-Meal")) return MainConfig.getInt("SneakyTreeGrowing.Crop-Settings.Crop-Meal-Radius");
        return 0;
    }

    private static int getChance(Object tag) {
        if (tag.toString().contains("minecraft:saplings")) return MainConfig.getInt("SneakyTreeGrowing.Tree-Meal-Chance");
        if (tag.toString().contains("minecraft:crops") && MainConfig.getBoolean("SneakyTreeGrowing.Crop-Settings.Enable-Crop-Meal")) return MainConfig.getInt("SneakyTreeGrowing.Crop-Settings.Crop-Meal-Chance");
        return 0;
    }
}
