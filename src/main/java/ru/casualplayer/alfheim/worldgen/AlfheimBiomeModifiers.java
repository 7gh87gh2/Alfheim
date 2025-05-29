package ru.casualplayer.alfheim.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import ru.casualplayer.alfheim.AlfheimMod;

public class AlfheimBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_TREE_DREAMWOOD = registerKey("add_tree_dreamwood");

    public static final ResourceKey<BiomeModifier> ADD_URU_METAL_ORE = registerKey("add_uru_metal_ore");
    public static final ResourceKey<BiomeModifier> ADD_ELEMENTIUM_ORE = registerKey("add_elementium_ore");
    public static final ResourceKey<BiomeModifier> ADD_DRAGONSTONE_ORE = registerKey("add_dragonstone_ore");
    public static final ResourceKey<BiomeModifier> ADD_MITHRIL_ORE = registerKey("add_mithril_ore");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // Ores
        context.register(ADD_URU_METAL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(AlfheimPlacedFeatures.URU_METAL_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_ELEMENTIUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(AlfheimPlacedFeatures.ELEMENTIUM_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_DRAGONSTONE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(AlfheimPlacedFeatures.DRAGONSTONE_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_MITHRIL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(AlfheimPlacedFeatures.MITHRIL_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        // Dreamwood Tree
        context.register(ADD_TREE_DREAMWOOD, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                //biomes.getOrThrow(AlfheimTags.Biomes.ALFHEIM_BIOMES),
                biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                HolderSet.direct(placedFeatures.getOrThrow(AlfheimPlacedFeatures.DREAMWOOD_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, name));
    }

}
