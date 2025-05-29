package ru.casualplayer.alfheim.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.block.AlfheimModBlocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlfheimPlacedFeatures {

    // Tree
    public static final ResourceKey<PlacedFeature> DREAMWOOD_PLACED_KEY = registerKey("dreamwood_placed");

    // FLowers
    public static HashMap<ResourceKey<ConfiguredFeature<?, ?>>, ResourceKey<PlacedFeature>> MYSTICAL_FLOWER_FEATURES_PLACED = new HashMap<>();

    // Ore
    public static final ResourceKey<PlacedFeature> URU_METAL_ORE_KEY = registerKey("uru_metal_ore_placed");
    public static final ResourceKey<PlacedFeature> ELEMENTIUM_ORE_KEY = registerKey("elementium_ore_placed");
    public static final ResourceKey<PlacedFeature> DRAGONSTONE_ORE_KEY = registerKey("dragonstone_ore_placed");
    public static final ResourceKey<PlacedFeature> MITHRIL_ORE_KEY = registerKey("mithril_ore_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Ores
        register(context, URU_METAL_ORE_KEY, configuredFeatures.getOrThrow(AlfheimConfiguredFeatures.URU_METAL_ORE_KEY),
                AlfheimOrePlacement.commonOrePlacement(9,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(96))));

        register(context, ELEMENTIUM_ORE_KEY,
                configuredFeatures.getOrThrow(AlfheimConfiguredFeatures.ELEMENTIUM_ORE_KEY),
                AlfheimOrePlacement.commonOrePlacement(7,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(64))));

        register(context, DRAGONSTONE_ORE_KEY,
                configuredFeatures.getOrThrow(AlfheimConfiguredFeatures.DRAGONSTONE_ORE_KEY),
                AlfheimOrePlacement.commonOrePlacement(5,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24))));

        register(context, MITHRIL_ORE_KEY,
                configuredFeatures.getOrThrow(AlfheimConfiguredFeatures.MITHRIL_ORE_KEY),
                AlfheimOrePlacement.commonOrePlacement(3,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(8))));

        // Trees
        register(context, DREAMWOOD_PLACED_KEY, configuredFeatures.getOrThrow(AlfheimConfiguredFeatures.DREAMWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1), AlfheimModBlocks.DREAMWOOD_SAPLING.get()));

        // Mystical Flowers
        List<PlacementModifier> list = List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        for(ResourceKey<ConfiguredFeature<?, ?>> key : AlfheimConfiguredFeatures.MYSTICAL_FLOWER_FEATURES.keySet()) {
            ResourceKey<PlacedFeature> feature = registerKey(getKeyName(key)+"_placed");
            MYSTICAL_FLOWER_FEATURES_PLACED.put(key, feature);
            register(context, feature, configuredFeatures.getOrThrow(key), list);
        }

        System.out.println("\n\n\nHashMap:\n");
        for(ResourceKey<ConfiguredFeature<?, ?>> key : MYSTICAL_FLOWER_FEATURES_PLACED.keySet()) {
            System.out.println(key.location() + " : " + MYSTICAL_FLOWER_FEATURES_PLACED.get(key).location());
        }
    }

    protected static String getKeyName(ResourceKey<ConfiguredFeature<?, ?>> feature) {
        return feature.location().getPath();
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, name));
    }

    private static void register(
            BootstapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> configuration,
            List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}
