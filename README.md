<center>
    <img src="https://qubik-studios.net/wp-content/uploads/2022/07/Sneaky-Tree-Growing-Banner.png">
    <br>
    <img src="https://img.shields.io/github/v/release/Qubik-Studios/Sneaky-Tree-Growing?include_prereleases&style=flat-square" alt="Current Release">
    <img src="https://img.shields.io/discord/759767022916599808?label=Discord&style=flat-square" alt="Discord">
    <img src="https://img.shields.io/github/languages/code-size/Qubik-Studios/Sneaky-Tree-Growing?style=flat-square" alt="Code size">
    <img alt="GitHub" src="https://img.shields.io/github/license/Qubik-Studios/Sneaky-Tree-Growing?style=flat-square">
    <br>
    <p>Like the project? Leave a Star🌟 on this Mod!</p>
</center>

## Downloads

You can find downloads in the list below or by Compiling from source. There is no alternative download links/pages that
are official maintained by Qubik Studios when they are not shown here.
<br>

| Minecraft Version   | Mod Loader | Version | State                                                                                                                                                                                                                |
|---------------------|------------|---------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Legacy (No Support) |            |         |                                                                                                                                                                                                                      |
| 1.16.5              | Forge      | 1.6.2   | ✅Download: <a href="https://www.curseforge.com/minecraft/mc-mods/sneaky-tree-growing-forge/files/3627338">CurseForge</a>                                                                                             |
| 1.17.1              | Forge      | 1.7.2   | ✅Download: <a href="https://www.curseforge.com/minecraft/mc-mods/sneaky-tree-growing-forge/files/3568661">CurseForge</a>                                                                                             |
| 1.18.1              | Forge      | 1.7.2   | ✅Download: <a href="https://www.curseforge.com/minecraft/mc-mods/sneaky-tree-growing-forge/files/3627340">CurseForge</a>                                                                                             |
| 1.18.2              | Forge      | 1.8.0   | ✅Download: <a href="https://www.curseforge.com/minecraft/mc-mods/sneaky-tree-growing-forge/files/3789160">CurseForge</a> - <a href="https://modrinth.com/mod/sneaky-tree-growing/version/1.8.0">Modrinth</a>         |
| Update 1.10.0       |            |         |                                                                                                                                                                                                                      |
| 1.16.x              | Forge      | 1.10.0  | ❌in-Dev                                                                                                                                                                                                              |
| 1.17.x              | Forge      | 1.10.0  | ❌in-Dev                                                                                                                                                                                                              |
| 1.18.x              | Forge      | 1.10.0  | ⚠️in-Dev                                                                                                                                                                                                             |
| 1.19.x              | Forge      | 1.10.0  | ✅Download: <a href="https://www.curseforge.com/minecraft/mc-mods/sneaky-tree-growing-forge/files/4540858">CurseForge</a> - <a href="https://modrinth.com/mod/sneaky-tree-growing/version/1.19.x-1.10.0">Modrinth</a> |
| 1.20.x              | Forge      | 1.10.0  | ⚠️in-Dev                                                                                                                                                                                                             |

`✅ = Released |⚠️ = Dev-Build available | ❌ = No Dev-Build available`

## Features

This mod allows you to sneak within a 6x6 area to apply the Bone-meal effect to saplings/seeds/other blocks.
You have the flexibility to adjust the radius and probability of this effect through the Config. Additionally, you can
activate specific features, such as a mode that extends the Bone-meal effect to seeds and crops.
Using this mod will gradually deplete your food level, which can be customized in the Config.
Furthermore, you have the option to remove Bone-meal from your inventory with ease.

## Custom Tags and Blocks

In the newest version you can now add tags from mods that are not supported. As example, you can add manually support
for
Dynamic tree's.
Just go for that in the config and add your favorite mod tag in the section: "Custom-Tags = []"
An example how you use the custom tag system is in the Config.

Version 1.10 and up:<br>
There is a new Config for Normal Blocks. You can add every Single block you like to get the Effect applied.

## Config

Starting with Version 1.10 of SneakyTreeGrowing the config is split into 2 parts making it incompatible for older
Versions.
We want to reduce the complicated overview of the file and with the Block-Tags.

#### Forge Defaults

Location: `/<YourMCFolder>/config/Qubik Studios Mods/SneakyTreeGrowing/main.toml`

```toml
[SneakyTreeGrowing]
#Increasing this value will change the area-of-effect from the mod
#Default value: 6
#Range: 1 ~ 1000
Tree-Meal-Radius = 6
#Changing this value will change the chance if a Bone Meal effect gets applied or not
#Default value: 15
#Range: 1 ~ 100
Tree-Meal-Chance = 15
#Removes one Bone Meal from player inventory when Bone Meal gets applied over Sneaky Tree Growing
#Default value: false
Use-Inventory-Bone-Meal = false
#Removes more Hunger when sneaking and effect gets applied
#Default value: true
Remove-Hunger = true
```

Location: `/<YourMCFolder>/config/Qubik Studios Mods/SneakyTreeGrowing/blockList.toml`

```toml
[Allowed-Blocks]
#Here you can add every block you like to get a Bone Meal effect applied.
Block-List = ["minecraft:bamboo", "minecraft:brown_mushroom", "minecraft:red_mushroom", "minecraft:wheat_seeds", "minecraft:carrots", "minecraft:potatoes", "minecraft:beetroots", "minecraft:melon_stem", "minecraft:pumpkin_stem", "minecraft:big_dripleaf", "minecraft:small_dripleaf"]
#Here you can add block Tags for easier use of this mod.
Tag-List = ["minecraft:crops", "minecraft:saplings"]
```

## Note on Mod-pack creators and Disseminator

You can use this mod for free in your Mod-pack. Just make sure to credit this mod.
If you want to republish this mod on another platform you have to join our Discord and ask the Developer.
Reselling of this mod is prohibited! If you bought this mod please join our Discord and report the person who was
selling this to you.

## Need Help? Join our Discord!

<center>
    <a href="http://discord.qubik-studios.net" target="_blank" rel="noopener noreferrer"><img src="https://discordapp.com/api/guilds/759767022916599808/embed.png?style=banner3" alt="Discord" width="320" height="140" /></a>
    <br>
</center>
    <h4><strong><span style="color: #808080;">A Project maintained and created by</span></strong></h4>
    <a href="https://Qubik-Studios.net" target="_blank"><img src="https://qubik-studios.net/wp-content/uploads/2021/10/QUBIK-STUDIOS-BANNER-DARKMODE.png" alt="Qubik Studios" width="194"/></a>
    <img src="https://qubik-studios.net/wp-content/uploads/2022/07/Divider-Small.png">
