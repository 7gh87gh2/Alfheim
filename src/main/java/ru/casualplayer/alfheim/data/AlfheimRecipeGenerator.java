package ru.casualplayer.alfheim.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.block.AlfheimModBlocks;
import ru.casualplayer.alfheim.item.AlfheimModItems;
import vazkii.botania.common.item.BotaniaItems;

import java.util.List;
import java.util.function.Consumer;

public class AlfheimRecipeGenerator extends RecipeProvider implements IConditionBuilder {

    public AlfheimRecipeGenerator(PackOutput output) { super(output); }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        // Stone from Cobblestone
        smeltBlast(consumer, List.of(AlfheimModBlocks.ALFHEIM_COBBLESTONE.get()), RecipeCategory.BUILDING_BLOCKS, AlfheimModBlocks.ALFHEIM_STONE.get(), 0f, 200, "alfheim_stone");

        // From Ore
        smeltBlast(consumer, List.of(AlfheimModBlocks.URU_METAL_ORE.get()), RecipeCategory.MISC, AlfheimModItems.URU_METAL_INGOT.get(), 0.25f, 200, "uru_metal");
        smeltBlast(consumer, List.of(AlfheimModBlocks.ELEMENTIUM_ORE.get()), RecipeCategory.MISC, BotaniaItems.elementium, 0.25f, 200, "elementium");
        smeltBlast(consumer, List.of(AlfheimModBlocks.DRAGONSTONE_ORE.get()), RecipeCategory.MISC, BotaniaItems.dragonstone, 0.25f, 200, "dragonstone");
        smeltBlast(consumer, List.of(AlfheimModBlocks.MITHRIL_ORE.get()), RecipeCategory.MISC, AlfheimModItems.MITHRIL_INGOT.get(), 0.25f, 200, "mithril");

        // From Raw Metals
        smeltBlast(consumer, List.of(AlfheimModItems.RAW_URU_METAL.get()), RecipeCategory.MISC, AlfheimModItems.URU_METAL_INGOT.get(), 0.25f, 200, "uru_metal");
        smeltBlast(consumer, List.of(AlfheimModItems.RAW_ELEMENTIUM.get()), RecipeCategory.MISC, BotaniaItems.elementium, 0.25f, 200, "elementium");
        smeltBlast(consumer, List.of(AlfheimModItems.RAW_MITHRIL.get()), RecipeCategory.MISC, AlfheimModItems.MITHRIL_INGOT.get(), 0.25f, 200, "mithril");

        // Crafting
        pack3x3andBack(consumer, AlfheimModItems.URU_METAL_INGOT.get(), AlfheimModItems.URU_METAL_NUGGET.get());
        pack3x3andBack(consumer, AlfheimModBlocks.URU_METAL_BLOCK.get(), AlfheimModItems.URU_METAL_INGOT.get());
        pack3x3andBack(consumer, AlfheimModBlocks.RAW_URU_METAL_BLOCK.get(), AlfheimModItems.RAW_URU_METAL.get());

        pack3x3andBack(consumer, AlfheimModItems.MITHRIL_INGOT.get(), AlfheimModItems.MITHRIL_NUGGET.get());
        pack3x3andBack(consumer, AlfheimModBlocks.MITHRIL_BLOCK.get(), AlfheimModItems.MITHRIL_INGOT.get());
        pack3x3andBack(consumer, AlfheimModBlocks.RAW_MITHRIL_BLOCK.get(), AlfheimModItems.RAW_MITHRIL.get());
    }

    protected static void pack3x3andBack(Consumer<FinishedRecipe> consumer, ItemLike packedItem, ItemLike unpackedItem) {
        // Pack 3x3
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, packedItem)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', unpackedItem)
                .unlockedBy(getHasName(unpackedItem), has(unpackedItem))
                .save(consumer, AlfheimMod.MODID + ":" + getItemName(packedItem) + "_from_" + getItemName(unpackedItem));

        // Unpack to 9 inputs
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, unpackedItem, 9)
                .requires(packedItem)
                .unlockedBy(getHasName(packedItem), has(packedItem))
                .save(consumer, AlfheimMod.MODID + ":" + getItemName(unpackedItem) + "_from_" + getItemName(packedItem));
    }

    protected static void smeltBlast(Consumer<FinishedRecipe> consumer, List<ItemLike> inputs, RecipeCategory category, ItemLike result, float xp, int time, String group) {
        oreCooking(consumer, RecipeSerializer.SMELTING_RECIPE, inputs, category, result, xp, time, group, "_from_smelting");
        oreCooking(consumer, RecipeSerializer.BLASTING_RECIPE, inputs, category, result, xp, time/2, group, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> consumer, RecipeSerializer<? extends AbstractCookingRecipe> recipe, List<ItemLike> inputs, RecipeCategory category, ItemLike result, float xp, int time, String group, String name) {
        for(ItemLike itemlike : inputs) {
            SimpleCookingRecipeBuilder.generic(
                    Ingredient.of(itemlike),
                    category, result, xp, time, recipe)
                    .group(group)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(consumer, AlfheimMod.MODID + ":" + getItemName(result) + name + "_" + getItemName(itemlike));
        }
    }

}
