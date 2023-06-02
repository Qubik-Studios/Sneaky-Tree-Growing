package net.qubikstudios.sneakytreegrowingforge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.qubikstudios.sneakytreegrowingforge.config.AllowedBlockListConfig;
import net.qubikstudios.sneakytreegrowingforge.config.MainConfig;
import net.qubikstudios.sneakytreegrowingforge.functions.PlayerTickListener;

@Mod("sneaky_tree_growing")
public class SneakyTreeGrowingMod {
    public static final MainConfig.Common CONFIG = MainConfig.COMMON;

    public SneakyTreeGrowingMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MainConfig.COMMON_SPEC, "Qubik Studios Mods/SneakyTreeGrowing/main.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AllowedBlockListConfig.COMMON_SPEC, "Qubik Studios Mods/SneakyTreeGrowing/blockList.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(PlayerTickListener::start);
    }
}
