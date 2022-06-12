package net.qubikstudios.sneakytreegrowingfabric.functions;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.world.entity.Entity;
import net.qubikstudios.sneakytreegrowingfabric.config.MainConfig;

public class Trigger {

    public static void treeMealer() {
        ClientTickEvents.END_CLIENT_TICK.register((event) -> {
            if (!event.isLocalServer() || event.player == null || event.player.isSpectator() || event.isPaused()) return;
            Entity entity = event.player;
            Core.execute(entity.level, entity, MainConfig.getInt("SneakyTreeGrowing.Tree-Meal-Radius"),
                    MainConfig.getInt("SneakyTreeGrowing.Tree-Meal-Chance"), "minecraft:saplings");
        });
    }

    public static void cropMealer() {
        ClientTickEvents.END_CLIENT_TICK.register((event) -> {
            if (!event.isLocalServer() || event.player == null || event.player.isSpectator() || event.isPaused()) return;
            Entity entity = event.player;
            Core.execute(entity.level, entity, MainConfig.getInt("SneakyTreeGrowing.Crop-Meal-Radius"),
                    MainConfig.getInt("SneakyTreeGrowing.Crop-Meal-Chance"), "minecraft:crops");
        });
    }

    public static void customTagMealer() {
        ClientTickEvents.END_CLIENT_TICK.register((event) -> {
            if (!event.isLocalServer() || event.player == null || event.player.isSpectator() || event.isPaused()) return;
            Entity entity = event.player;
            for (Object tag : MainConfig.getArray("SneakyTreeGrowing.Custom-Tag.Tag-List")) {
                Core.execute(entity.level, entity, MainConfig.getInt("SneakyTreeGrowing.Custom-Tag.Custom-Tags-Meal-Radius"),
                        MainConfig.getInt("SneakyTreeGrowing.Custom-Tag.Custom-Tags-Meal-Chance"), tag.toString());
            }
        });
    }
}
