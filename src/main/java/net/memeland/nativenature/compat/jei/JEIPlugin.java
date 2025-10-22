package net.memeland.nativenature.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.memeland.nativenature.NativeNatureMod;
import net.memeland.nativenature.recipe.TintedBlockRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(NativeNatureMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return;

        List<RecipeHolder<CraftingRecipe>> tintedRecipes = level.getRecipeManager()
                .getAllRecipesFor(net.minecraft.world.item.crafting.RecipeType.CRAFTING)
                .stream()
                .filter(holder -> holder.value() instanceof TintedBlockRecipe)
                .map(holder -> (RecipeHolder<CraftingRecipe>) holder)
                .toList();

        registration.addRecipes(RecipeTypes.CRAFTING, tintedRecipes);
    }
}