package net.qubikstudios.sneakytreegrowing;

import net.qubikstudios.sneakytreegrowing.commands.ToggleModes;
import net.qubikstudios.sneakytreegrowing.events.PlayerSneakEvent;
import net.qubikstudios.sneakytreegrowing.util.ConfigNames;
import net.qubikstudios.sneakytreegrowing.util.PluginConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SneakyTreeGrowing extends JavaPlugin {

    private PluginConfig pluginConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        if(pluginConfig == null)
            pluginConfig = PluginConfig.loadConfiguration(new File(getDataFolder(), "config.yml"));

        getCommand("SneakyTreeGrowing").setExecutor(new ToggleModes(this));

        var treeState = getPluginConfig().getBoolean(ConfigNames.TREE_ENABLED);
        var cropsState = getPluginConfig().getBoolean(ConfigNames.CROP_ENABLED);
        var tagsState = getPluginConfig().getBoolean(ConfigNames.CUSTOM_ENABLED);

        Bukkit.getConsoleSender().sendMessage("|----------> SneakyTreeGrowing <----------|");
        Bukkit.getConsoleSender().sendMessage("|                                         |");
        Bukkit.getConsoleSender().sendMessage("| Mode status:                            |");
        Bukkit.getConsoleSender().sendMessage(String.format("|%4sTree : %s%22s§f|", " ", (!treeState ? "§cdisabled" : "§aenabled "), " "));
        Bukkit.getConsoleSender().sendMessage(String.format("|%4sCrops: %s%22s§f|", " ", (!cropsState ? "§cdisabled" : "§aenabled "), " "));
        Bukkit.getConsoleSender().sendMessage(String.format("|%4sTags : %s%22s§f|", " ", (!tagsState ? "§cdisabled" : "§aenabled "), " "));
        Bukkit.getConsoleSender().sendMessage("|                                         |");
        Bukkit.getConsoleSender().sendMessage("|-----------------------------------------|");

        getServer().getPluginManager().registerEvents(new PlayerSneakEvent(this), this);
    }

    @Override
    public void onDisable() {
        //Nothing
    }

    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }
}
