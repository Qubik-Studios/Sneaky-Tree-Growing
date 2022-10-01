package net.qubikstudios.sneakytreegrowing;

import net.qubikstudios.sneakytreegrowing.commands.ToggleModes;
import net.qubikstudios.sneakytreegrowing.events.PlayerSneak;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SneakyTreeGrowing extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("SneakyTreeGrowing").setExecutor(new ToggleModes(this));

        var treeState = getConfig().getBoolean("SneakyTreeGrowing.TreeSettings.EnableTreeMeal");
        var cropsState = getConfig().getBoolean("SneakyTreeGrowing.CropSettings.EnableCropMeal");
        var tagsState = getConfig().getBoolean("SneakyTreeGrowing.CustomTag.EnableCustomTags");

        Bukkit.getConsoleSender().sendMessage("|----------> SneakyTreeGrowing <----------|");
        Bukkit.getConsoleSender().sendMessage("|                                         |");
        Bukkit.getConsoleSender().sendMessage("| Mode status:                            |");
        Bukkit.getConsoleSender().sendMessage(String.format("|%4sTree : %s%22s§f|", " ", (!treeState ? "§cdisabled" : "§aenabled "), " "));
        Bukkit.getConsoleSender().sendMessage(String.format("|%4sCrops: %s%22s§f|", " ", (!cropsState ? "§cdisabled" : "§aenabled "), " "));
        Bukkit.getConsoleSender().sendMessage(String.format("|%4sTags : %s%22s§f|", " ", (!tagsState ? "§cdisabled" : "§aenabled "), " "));
        Bukkit.getConsoleSender().sendMessage("|                                         |");
        Bukkit.getConsoleSender().sendMessage("|-----------------------------------------|");

        getServer().getPluginManager().registerEvents(new PlayerSneak(this), this);
    }

    @Override
    public void onDisable() {
        //Nothing
    }
}
