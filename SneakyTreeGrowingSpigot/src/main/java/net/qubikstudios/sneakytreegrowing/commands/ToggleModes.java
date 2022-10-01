package net.qubikstudios.sneakytreegrowing.commands;

import net.qubikstudios.sneakytreegrowing.SneakyTreeGrowing;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToggleModes implements TabExecutor {

    private final SneakyTreeGrowing instance;
    private final FileConfiguration conf;

    public ToggleModes(SneakyTreeGrowing instance) {
        this.instance = instance;
        this.conf = instance.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 2)
            return false;

        if(args[0].equals("toggle")){
            if(args[1].equals("tree")){
                var currentState = conf.getBoolean("SneakyTreeGrowing.TreeSettings.EnableTreeMeal");
                var result = currentState ? "§cdisabled" : "§aenabled";
                conf.set("SneakyTreeGrowing.TreeSettings.EnableTreeMeal", !currentState);
                sender.sendMessage("Growing trees are now set to: " + result);
            }

            if(args[1].equals("crops")){
                var currentState = conf.getBoolean("SneakyTreeGrowing.CropSettings.EnableCropMeal");
                var result = currentState ? "§cdisabled" : "§aenabled";
                conf.set("SneakyTreeGrowing.CropSettings.EnableCropMeal", !currentState);
                sender.sendMessage("Growing crops are now set to: " + result);
            }

            if(args[1].equals("tags")){
                var currentState = conf.getBoolean("SneakyTreeGrowing.CustomTag.EnableCustomTags");
                var result = currentState ? "§cdisabled" : "§aenabled";
                conf.set("SneakyTreeGrowing.CustomTag.EnableCustomTags", !currentState);
                sender.sendMessage("Growing tags are now set to: " + result);
            }
            instance.saveConfig();
            return true;
        }

        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        var list = new ArrayList<String>();

        if(isCommand(label) && args.length == 1){
            list.add("toggle");
        }

        if(isCommand(label) && args.length >= 1 & args[0].equalsIgnoreCase("toggle")){
            list.add("tree");
            list.add("crops");
            list.add("tags");
        }

        return list;
    }

    private boolean isCommand(String label){
        return label.equalsIgnoreCase("stg") || label.equalsIgnoreCase("sneakygrowing") || label.equalsIgnoreCase("SneakyTreeGrowing");
    }
}
