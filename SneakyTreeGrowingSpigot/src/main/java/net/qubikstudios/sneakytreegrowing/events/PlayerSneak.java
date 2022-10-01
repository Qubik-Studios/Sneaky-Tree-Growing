package net.qubikstudios.sneakytreegrowing.events;

import net.qubikstudios.sneakytreegrowing.SneakyTreeGrowing;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerSneak implements Listener {

    private final SneakyTreeGrowing instance;
    private final FileConfiguration conf;

    public PlayerSneak(SneakyTreeGrowing instance) {
        this.instance = instance;
        this.conf = instance.getConfig();
    }
    
    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        if(!event.isSneaking())
            return;

        if (conf.getBoolean("SneakyTreeGrowing.TreeSettings.EnableTreeMeal")) {
            var saplings = getSaplingsInRange(event.getPlayer().getLocation());

            for (var sapling : saplings){
                if (hasChance(conf.getDouble("SneakyTreeGrowing.TreeSettings.TreeMealChance")))
                    sapling.applyBoneMeal(BlockFace.UP);
            }
        }

        if (conf.getBoolean("SneakyTreeGrowing.CropSettings.EnableCropMeal")) {
            var crops = getCropsInRange(event.getPlayer().getLocation());

            for (var crop : crops){
                if (hasChance(conf.getDouble("SneakyTreeGrowing.CropSettings.CropMealChance")))
                    crop.applyBoneMeal(BlockFace.UP);
            }
        }

        if (conf.getBoolean("SneakyTreeGrowing.CustomTag.EnableCustomTags")) {
            var tags = getTagBlocksInRange(event.getPlayer().getLocation());

            for (var tag : tags){
                if (hasChance(conf.getDouble("SneakyTreeGrowing.CustomTag.CustomTagsMealChance")))
                    tag.applyBoneMeal(BlockFace.UP);
            }
        }
    }

    private List<Block> getSaplingsInRange(Location center) {
        var range = conf.getInt("SneakyTreeGrowing.TreeSettings.TreeMealRadius");
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
        var range = conf.getInt("SneakyTreeGrowing.CropSettings.CropMealRadius");
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
        var range = conf.getInt("SneakyTreeGrowing.CustomTag.CustomTagsMealRadius");
        List<String> tags = conf.getStringList("SneakyTreeGrowing.CustomTag.TagList");
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

    private boolean hasChance(double chance){
        var random = Math.random() * 101;
        return random <= chance;
    }
}
