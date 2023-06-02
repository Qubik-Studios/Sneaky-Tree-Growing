package net.qubikstudios.sneakytreegrowing.commands;

import net.qubikstudios.sneakytreegrowing.SneakyTreeGrowing;
import net.qubikstudios.sneakytreegrowing.util.ConfigNames;
import net.qubikstudios.sneakytreegrowing.util.PluginConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToggleModes implements TabExecutor {

    private final SneakyTreeGrowing instance;
    private final PluginConfig conf;

    public ToggleModes(SneakyTreeGrowing instance) {
        this.instance = instance;
        this.conf = instance.getPluginConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 2 || !sender.isOp())
            return false;

        if(args[0].equals("toggle")){
            if(args[1].equals("tree")){
                var currentState = conf.getBoolean(ConfigNames.TREE_ENABLED);
                var result = currentState ? "§cdisabled" : "§aenabled";
                conf.set(ConfigNames.TREE_ENABLED, !currentState);
                sender.sendMessage("Growing trees are now set to: " + result);
            }

            if(args[1].equals("crops")){
                var currentState = conf.getBoolean(ConfigNames.CROP_ENABLED);
                var result = currentState ? "§cdisabled" : "§aenabled";
                conf.set(ConfigNames.CROP_ENABLED, !currentState);
                sender.sendMessage("Growing crops are now set to: " + result);
            }

            if(args[1].equals("tags")){
                var currentState = conf.getBoolean(ConfigNames.CUSTOM_ENABLED);
                var result = currentState ? "§cdisabled" : "§aenabled";
                conf.set(ConfigNames.CUSTOM_ENABLED, !currentState);
                sender.sendMessage("Growing tags are now set to: " + result);
            }
            try {
                conf.save(new File(instance.getDataFolder(), "config.yml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
