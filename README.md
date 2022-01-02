<p><br /><img src="https://i.imgur.com/KoTKu8x.png" alt="Ore Duplicators 2 Logo" width="174" height="174" /></p>
#Sneaky Tree Growing
<p>Sneak to grow Trees!</p>

#####Like the project? Leave a 💖Follow on this Mod!
<hr>
##Downloads
You can find downloads here on CurseForge.
There is no alternative download links/pages that are offical maintained by Qubik Studios when they are not shown here.
<hr>
##Features
Sneak in a 6x6 area to apply Saplings a Bonemeal effect.
You can change the radius and chance of this effect in the Config

You can also enable certain functions like a mode where it also apply the Bonemeal effect to seeds/crops. (Build in only works with Vanilla Minecraft seeds)

#####Custom Tags
In the newest version you can now add tags from mods that are not supportet. As example you can add manually support for Dynamic tree's.
Just go for that in the config and add your faverite mod tag in the section: "Custom-Tags = []"
An example how you use the custom tag system is in the Config.
#####Config
This is the unconfigured Config you will find for this mod.
```toml

[SneakyTreeGrowing]
	#Increasing this value will change the area-of-effect from the mod
	#Default value: 6
	#Range: 1 ~ 1000
	Tree-Meal-Radius = 6
	#Changing this value will change the chance if a bonemeal effect gets applied or not
	#Default value: 15
	#Range: 1 ~ 100
	Tree-Meal-Chance = 15

	[SneakyTreeGrowing.Crop-Settings]
		#Changing this value to true will allow the mod to apply the bonemeal effect to crops like wheat and potato. Only works on Vannila plants
		#Default value: false
		Crop-Meal-Allowed = false
		#Increasing this value will change the area-of-effect from the crop meal effect
		#Default value: 6
		#Range: 1 ~ 25
		Crop-Meal-Radius = 6
		#Changing this value will change the chance if a bonemeal effect gets applied to crops or not
		#Default value: 5
		#Range: 1 ~ 50
		Crop-Meal-Chance = 5

	[SneakyTreeGrowing.Custom-Tag]
		#Enable Custom tag support for the mod. (WIP)
		#Default value: false
		Enable-Custom-Tags = false
		#All values added in this list will result in support for the bonemeal effect.
		#Example: ["forge:seeds", "minecraft:crops"]
		Custom-Tags = []
		#Increasing this value will change the area-of-effect from the custom-tag meal effect
		#Default Value: 6
		#Range: 1 ~ 1000
		Custom-Tags-Meal-Radius = 6
		#Changing this value will change the chance if a bonemeal effect gets applied to custom tags or not
		#Default Value: 15
		#Range: 1 ~ 1000
		Custom-Tags-Meal-Chance = 15
```
<hr>
##Note on Modpack creators and Disseminators
You can use this mod for free in your Modpack. Just make sure to credit this mod.
If you want to republish this mod on a other platform you have to join our Discord and ask the Developer.

Reselling of this mod is prohibited! If you bought this mod please join our Discord and report the person who was selling this to you.
<hr>
##Need help? Join our Discord!
<p><br /><a href="http://discord.qubik-studios.net" target="_blank" rel="noopener noreferrer"><img src="https://discordapp.com/api/guilds/759767022916599808/embed.png?style=banner3" alt="Discord" width="320" height="140" /></a></p>

######Have fun!<br>A project maintained by
<p><img src="https://qubik-studios.net/wp-content/uploads/2021/10/QUBIK-STUDIOS-BANNER-DARKMODE.png" alt="Qubik Studios" width="294" height="114" /></p>
