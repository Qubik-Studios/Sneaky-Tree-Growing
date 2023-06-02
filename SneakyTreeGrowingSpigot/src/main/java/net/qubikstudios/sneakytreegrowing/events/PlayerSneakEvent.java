package net.qubikstudios.sneakytreegrowing.events;

import net.qubikstudios.sneakytreegrowing.SneakyTreeGrowing;
import net.qubikstudios.sneakytreegrowing.util.ConfigNames;
import net.qubikstudios.sneakytreegrowing.util.PluginConfig;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayerSneakEvent implements Listener {

    private final SneakyTreeGrowing instance;
    private final PluginConfig conf;

    public PlayerSneakEvent(SneakyTreeGrowing instance) {
        this.instance = instance;
        this.conf = PluginConfig.loadConfiguration(new File(instance.getDataFolder(), "config.yml"));
    }

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        if(!event.isSneaking())
            return;

        if (conf.getBoolean(ConfigNames.TREE_ENABLED) && hasPlayerPermission(event.getPlayer(), ConfigNames.TREE_NEED_PERMISSION, "stg.tree")) {
            var saplings = getSaplingsInRange(event.getPlayer().getLocation());

            for (var sapling : saplings){
                var range = inRange(0, 100, conf.getDouble(ConfigNames.TREE_CHANCE));
                if (hasChance(range))
                    sapling.applyBoneMeal(BlockFace.UP);
            }
        }

        if (conf.getBoolean(ConfigNames.CROP_ENABLED) && hasPlayerPermission(event.getPlayer(), ConfigNames.CROP_NEED_PERMISSION, "stg.crop")) {
            var crops = getCropsInRange(event.getPlayer().getLocation());

            for (var crop : crops){
                var range = inRange(0, 100, conf.getDouble(ConfigNames.CROP_CHANCE));
                if (hasChance(range))
                    crop.applyBoneMeal(BlockFace.UP);
            }
        }

        if (conf.getBoolean(ConfigNames.CUSTOM_ENABLED) && hasPlayerPermission(event.getPlayer(), ConfigNames.CROP_NEED_PERMISSION, "stg.customtag")) {
            var tags = getTagBlocksInRange(event.getPlayer().getLocation());

            for (var tag : tags){
                var range = inRange(0, 100, conf.getDouble(ConfigNames.CUSTOM_CHANCE));
                if (hasChance(range))
                    tag.applyBoneMeal(BlockFace.UP);
            }
        }
    }

    private boolean hasPlayerPermission(Player player, ConfigNames configName, String permission){
        if(!conf.getBoolean(configName))
            return true;

        return player.hasPermission(permission);
    }

    private List<Block> getSaplingsInRange(Location center) {
        var range = inRange(0, 1000, conf.getInt(ConfigNames.TREE_RADIUS));
        var blocks = new ArrayList<Block>();
        for (int x = center.getBlockX() - range; x < center.getBlockX() + range; x++) {
            for (int y = center.getBlockY() - range; y < center.getBlockY() + range; y++) {
                for (int z = center.getBlockZ() - range; z < center.getBlockZ() + range; z++) {
                    if(center.getWorld().getBlockAt(x, y, z).getBlockData() instanceof Sapling){
                        blocks.add(center.getWorld().getBlockAt(x, y, z));
                    }
                }
            }
        }

        return blocks;
    }

    private List<Block> getCropsInRange(Location center) {
        var range = inRange(0, 1000, conf.getInt(ConfigNames.CROP_RADIUS));
        var blocks = new ArrayList<Block>();
        for (int x = center.getBlockX() - range; x < center.getBlockX() + range; x++) {
            for (int y = center.getBlockY() - range; y < center.getBlockY() + range; y++) {
                for (int z = center.getBlockZ() - range; z < center.getBlockZ() + range; z++) {
                    if(center.getWorld().getBlockAt(x, y, z).getBlockData() instanceof Ageable){
                        blocks.add(center.getWorld().getBlockAt(x, y, z));
                    }
                }
            }
        }

        return blocks;
    }

    private List<Block> getTagBlocksInRange(Location center) {
        var range = inRange(0, 1000, conf.getInt(ConfigNames.CUSTOM_RADIUS));
        List<String> tags = conf.getStringList(ConfigNames.CUSTOM_TAGS);
        var blocks = new ArrayList<Block>();
        for (int x = center.getBlockX() - range; x < center.getBlockX() + range; x++) {
            for (int y = center.getBlockY() - range; y < center.getBlockY() + range; y++) {
                for (int z = center.getBlockZ() - range; z < center.getBlockZ() + range; z++) {
                    if(tags.contains(center.getWorld().getBlockAt(x, y, z).getBlockData().getMaterial().getKey().toString())){
                        blocks.add(center.getWorld().getBlockAt(x, y, z));
                    }
                }
            }
        }

        return blocks;
    }

    private int inRange(int min, int max, int value){
        if(value >= min & value <= max)
            return value;

        if(value < min)
            return min;

        if(value > max)
            return max;

        return 0;
    }

    private double inRange(double min, double max, double value){
        if(value >= min & value <= max)
            return value;

        if(value < min)
            return min;

        if(value > max)
            return max;

        return 0;
    }

    private boolean hasChance(double chance){
        var random = Math.random() * 101;
        return random <= chance;
    }
}
