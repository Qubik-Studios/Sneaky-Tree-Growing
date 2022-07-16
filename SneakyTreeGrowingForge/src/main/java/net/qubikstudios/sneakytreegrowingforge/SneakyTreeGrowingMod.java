package net.qubikstudios.sneakytreegrowingforge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.qubikstudios.sneakytreegrowingforge.config.MainConfig;
import net.qubikstudios.sneakytreegrowingforge.functions.Trigger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("sneaky_tree_growing")
public class SneakyTreeGrowingMod {

    public static final String VERSION = "FORGE-1.19-1.9.1";
    public static final Logger LOGGER = LogManager.getLogger();

    public SneakyTreeGrowingMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);


        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MainConfig.COMMON_SPEC, "Qubik Studios Mods/sneakytreegrowing.toml");
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("\u001b[32m[Sneaky Tree Growing] Found Version: " + VERSION + "\u001b[0m");
        initEvents();
    }

    private void initEvents() {

        //Register Events/Listener
        MinecraftForge.EVENT_BUS.addListener(Trigger::start);
    }

}
