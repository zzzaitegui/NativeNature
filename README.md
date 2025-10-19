# NativeNature
This mod lets you create vegetation blocks (grass, leaves, plants, etc.) that keep specific biome colors no matter where you place them. Normally in vanilla Minecraft, if you place jungle leaves in a desert, they turn yellowish-brown. With this mod, you can craft "tinted" versions that stay their original color anywhere.
The mod purpose is provide static vegetation blocks to decorate builds or build zoos without the worry of ugly or mismatched colors cause by the biome you are at.
How it works:
The mod has "Essences" - special items that represent different biome color palettes. There are 17 essences total, most extracted directly from vanilla biomes (Forest, Desert, Swamp, etc.) and some use custom hex colors (like Stygian essence with a deep blueish-green).
To craft tinted blocks, you surround an essence with 8 of the vanilla block in a crafting table. You get 8 tinted blocks back, and the essence isn't consumed - you can reuse it infinitely.
Available blocks for all essence except Arctic:

Oak Leaves, Jungle Leaves, Acacia Leaves, Dark Oak Leaves, Mangrov Leavese, Grass Block, Grass, Tall Grass, Fern, Large Fern, Sugar Cane, Vines.

Extra available blocks for Arctic Essence: 
Birch Leaves, Spruce Leaves, Azalea Leaves, Flowering Azalea Leaves, Cherry Leaves.

Special features:

Tinted leaves don't decay
Tinted grass blocks don't spread to dirt
Tinted plants can be placed on any block (not just dirt/grass)
Tinted sugar cane doesn't grow
Tinted vines don't spread

The colors work by extracting RGB values from vanilla's biome color maps (which should respect resource packs) or using hardcoded hex values for custom essences, then applying those fixed colors through custom color handlers.
There will be some classes attached along side this summary so you can see the mod's code.

More info here: https://www.curseforge.com/minecraft/mc-mods/native-nature
