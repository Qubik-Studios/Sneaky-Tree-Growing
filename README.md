<p align="center">
    <img width="300px" src="https://qubik-studios.net/wp-content/uploads/2021/08/Sneaky-Tree-Growing-logo.png">
</p>

<h1 align="center">Sneaky Tree Growing</h1>
<p align="center">Sneak to grow Trees</p>
<p align="center">Like the project? Leave a 💖Follow on this Mod!</p>

<p align="center">
    <img src="https://img.shields.io/github/v/release/Qubik-Studios/Sneaky-Tree-Growing?include_prereleases&style=flat-square" alt="Current Release">
    <img src="https://img.shields.io/discord/759767022916599808?label=Discord&style=flat-square" alt="Discord">
    <img src="https://img.shields.io/github/languages/code-size/Qubik-Studios/Sneaky-Tree-Growing?style=flat-square" alt="Code size">
    <img alt="GitHub" src="https://img.shields.io/github/license/Qubik-Studios/Sneaky-Tree-Growing?style=flat-square">
</p>

## Downloads
You can find downloads here on CurseForge.
There is no alternative download links/pages that are official maintained by Qubik Studios when they are not shown here.

## Features
Sneak in a 6x6 area to apply Saplings a Bonemeal effect.
You can change the radius and chance of this effect in the Config
*You can also enable certain functions like a mode where it also apply the Bonemeal effect to seeds/crops. (Build in only works with Vanilla Minecraft seeds)

## Custom Tags
In the newest version you can now add tags from mods that are not supportet. As example you can add manually support for Dynamic tree's.
Just go for that in the config and add your favorite mod tag in the section: "Custom-Tags = []"
An example how you use the custom tag system is in the Config.

## Config
```toml
[SneakyTreeGrowing]
  #Increasing this value will change the area-of-effect from the mod
  #Default value: 6
  #Range: 1 ~ 1000
  Tree-Meal-Radius = 6
  #Changing this value will change the chance if a bonemeal effect gets applied or not
  #Default value: 15
  #Range: 1 ~ 100
  Tree-Meal-Chance = 100

  [SneakyTreeGrowing.Crop-Settings]
  #Increasing this value will change the area-of-effect from the crop meal effect
  #Default value: 6
  #Range: 1 ~ 25
  Crop-Meal-Radius = 6
  #Changing this value to true will allow the mod to apply the bonemeal effect to crops like wheat and potato. Only works on Vannila plants
  #Default value: false
  Crop-Meal-Allowed = false
  #Changing this value will change the chance if a bonemeal effect gets applied to crops or not
  #Default value: 5
  #Range: 1 ~ 50
  Crop-Meal-Chance = 5

  [SneakyTreeGrowing.Custom-Tag]
  #All values added in this list will result in support for the bonemeal effect. 
  #Example: ["forge:seeds", "minecraft:crops"]
  Custom-Tags = ["minecraft:saplings"]
  #Changing this value will change the chance if a bonemeal effect gets applied to custom tags or not
  #Default Value: 15
  #Range: 1 ~ 1000
  Custom-Tags-Meal-Chance = 15
  #Increasing this value will change the area-of-effect from the custom-tag meal effect
  #Default Value: 6
  #Range: 1 ~ 1000
  Custom-Tags-Meal-Radius = 6
  #Enable Custom tag support for the mod.
  #Default value: false
  Enable-Custom-Tags = true
```

## Note on Modpack creators and Disseminator
You can use this mod for free in your Modpack. Just make sure to credit this mod.
If you want to republish this mod on a other platform you have to join our Discord and ask the Developer.
Reselling of this mod is prohibited! If you bought this mod please join our Discord and report the person who was selling this to you.

## Need Help? Join our Discord!
<div align="center">
    <a href="http://discord.qubik-studios.net" target="_blank" rel="noopener noreferrer"><img src="https://discordapp.com/api/guilds/759767022916599808/embed.png?style=banner3" alt="Discord" width="320" height="140" /></a>
    <br>
    <h4><strong><span style="color: #808080;">A Project maintained and created by</span></strong></h4>
    <a href="https://Qubik-Studios.net" target="_blank"><img src="https://qubik-studios.net/wp-content/uploads/2021/10/QUBIK-STUDIOS-BANNER-DARKMODE.png" alt="Qubik Studios" width="294" height="114" /></a>
</div>
