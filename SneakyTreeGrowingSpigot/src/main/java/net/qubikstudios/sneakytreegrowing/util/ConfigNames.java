package net.qubikstudios.sneakytreegrowing.util;

public enum ConfigNames {

    //Tree
    TREE_NEED_PERMISSION("SneakyTreeGrowing.TreeSettings.NeedPermission"),
    TREE_ENABLED("SneakyTreeGrowing.TreeSettings.Enabled"),
    TREE_RADIUS("SneakyTreeGrowing.TreeSettings.Radius"),
    TREE_CHANCE("SneakyTreeGrowing.TreeSettings.Chance"),

    //Crop
    CROP_NEED_PERMISSION("SneakyTreeGrowing.CropSettings.NeedPermission"),
    CROP_ENABLED("SneakyTreeGrowing.CropSettings.Enabled"),
    CROP_RADIUS("SneakyTreeGrowing.CropSettings.Radius"),
    CROP_CHANCE("SneakyTreeGrowing.CropSettings.Chance"),

    //Custom
    CUSTOM_NEED_PERMISSION("SneakyTreeGrowing.CustomTag.NeedPermission"),
    CUSTOM_ENABLED("SneakyTreeGrowing.CustomTag.Enabled"),
    CUSTOM_RADIUS("SneakyTreeGrowing.CustomTag.Radius"),
    CUSTOM_CHANCE("SneakyTreeGrowing.CustomTag.Chance"),
    CUSTOM_TAGS("SneakyTreeGrowing.CustomTag.TagList");

    private final String name;

    ConfigNames(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
