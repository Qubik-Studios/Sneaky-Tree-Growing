package net.qubikstudios.sneakytreegrowingfabric;

import net.fabricmc.api.ModInitializer;
import net.qubikstudios.sneakytreegrowingfabric.config.MainConfig;
import net.qubikstudios.sneakytreegrowingfabric.functions.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SneakyTreeGrowingMod implements ModInitializer {

    public static final String VERSION = "FABRIC-1.19-1.9.1";
    public static final Logger LOGGER = LoggerFactory.getLogger("sneaky_tree_growing");

    @Override
    public void onInitialize() {
        LOGGER.info("\u001b[32mLoaded Version: " + VERSION + "\u001b[0m");

        // Init Config
        MainConfig.init();
        //Init functions
        Trigger.meal();
    }

}
