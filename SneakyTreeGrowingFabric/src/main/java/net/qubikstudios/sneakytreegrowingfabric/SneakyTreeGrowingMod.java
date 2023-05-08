package net.qubikstudios.sneakytreegrowingfabric;

import net.fabricmc.api.ModInitializer;
import net.qubikstudios.sneakytreegrowingfabric.config.AllowedBlockListConfig;
import net.qubikstudios.sneakytreegrowingfabric.config.MainConfig;
import net.qubikstudios.sneakytreegrowingfabric.functions.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SneakyTreeGrowingMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("sneaky_tree_growing");

    @Override
    public void onInitialize() {
        // Init Config
        MainConfig.init();
        AllowedBlockListConfig.init();
        //Init functions
        Trigger.meal();
    }

}
