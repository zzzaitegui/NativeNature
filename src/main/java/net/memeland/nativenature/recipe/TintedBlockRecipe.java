package net.memeland.nativenature.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class TintedBlockRecipe implements CraftingRecipe {
    private final ItemStack output;
    private final Ingredient essence;
    private final Ingredient input;

    public TintedBlockRecipe(Ingredient input, Ingredient essence, ItemStack output) {
        this.input = input;
        this.essence = essence;
        this.output = output;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.width() != 3 || input.height() != 3) {
            return false;
        }

        ItemStack centerStack = input.getItem(4);
        if (!essence.test(centerStack)) {
            return false;
        }

        int[] surroundingSlots = {0, 1, 2, 3, 5, 6, 7, 8};
        for (int slot : surroundingSlots) {
            ItemStack stack = input.getItem(slot);
            if (!this.input.test(stack)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        // Return the essence item from center slot
        ItemStack essenceStack = input.getItem(4);
        if (!essenceStack.isEmpty()) {
            remaining.set(4, essenceStack.copy());
        }

        return remaining;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.withSize(9, Ingredient.EMPTY);
        ingredients.set(0, input);
        ingredients.set(1, input);
        ingredients.set(2, input);
        ingredients.set(3, input);
        ingredients.set(4, essence); // Center slot
        ingredients.set(5, input);
        ingredients.set(6, input);
        ingredients.set(7, input);
        ingredients.set(8, input);
        return ingredients;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.TINTED_BLOCK_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.BUILDING;
    }

    public static class Serializer implements RecipeSerializer<TintedBlockRecipe> {
        private static final MapCodec<TintedBlockRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Ingredient.CODEC.fieldOf("input").forGetter(recipe -> recipe.input),
                        Ingredient.CODEC.fieldOf("essence").forGetter(recipe -> recipe.essence),
                        ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.output)
                ).apply(instance, TintedBlockRecipe::new)
        );

        private static final StreamCodec<RegistryFriendlyByteBuf, TintedBlockRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC,
                recipe -> recipe.input,
                Ingredient.CONTENTS_STREAM_CODEC,
                recipe -> recipe.essence,
                ItemStack.STREAM_CODEC,
                recipe -> recipe.output,
                TintedBlockRecipe::new
        );

        @Override
        public MapCodec<TintedBlockRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, TintedBlockRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}