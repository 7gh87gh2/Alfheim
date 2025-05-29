package ru.casualplayer.alfheim.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.block.AlfheimModBlocks;
import ru.casualplayer.alfheim.worldgen.tree.dreamwood.DreamwoodFoliagePlacer;
import ru.casualplayer.alfheim.worldgen.tree.dreamwood.DreamwoodTrunkPlacer;
import vazkii.botania.common.block.BotaniaBlocks;

import java.util.HashMap;

public class AlfheimConfiguredFeatures {

    // Trees
    public static final ResourceKey<ConfiguredFeature<?, ?>> DREAMWOOD_KEY = registerKey("dreamwood");

    // Flowers
    public static final HashMap<ResourceKey<ConfiguredFeature<?,?>>, DyeColor> MYSTICAL_FLOWER_FEATURES = new HashMap<>();

    // Ore Generation
    public static final ResourceKey<ConfiguredFeature<?, ?>> URU_METAL_ORE_KEY = registerKey("uru_metal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ELEMENTIUM_ORE_KEY = registerKey("elementium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRAGONSTONE_ORE_KEY = registerKey("dragonstone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MITHRIL_ORE_KEY = registerKey("mithril_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        // Fill the mystical flowers HashMap
        for(DyeColor color : DyeColor.values()) MYSTICAL_FLOWER_FEATURES.put(registerKey("mystical_flowers_"+color.getName()), color);

        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);

        // Ores
        RuleTest stone = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        register(context, URU_METAL_ORE_KEY, Feature.ORE, new OreConfiguration(stone, AlfheimModBlocks.URU_METAL_ORE.get().defaultBlockState(), 6));
        register(context, ELEMENTIUM_ORE_KEY, Feature.ORE, new OreConfiguration(stone, AlfheimModBlocks.ELEMENTIUM_ORE.get().defaultBlockState(), 4));
        register(context, DRAGONSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(stone, AlfheimModBlocks.DRAGONSTONE_ORE.get().defaultBlockState(), 5));
        register(context, MITHRIL_ORE_KEY, Feature.ORE, new OreConfiguration(stone, AlfheimModBlocks.MITHRIL_ORE.get().defaultBlockState(), 2));

        // Trees
        register(context, DREAMWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                // Trunk
                BlockStateProvider.simple(BotaniaBlocks.dreamwood.defaultBlockState()),
                new DreamwoodTrunkPlacer(6, 0, 4), // only the center 1 block wide trunk is affected

                // Leaves
                BlockStateProvider.simple(AlfheimModBlocks.DREAMWOOD_LEAVES.get()),
                new DreamwoodFoliagePlacer(ConstantInt.of(0),ConstantInt.of(0),0), // parameters here don't matter as the canopy is always the same

                new TwoLayersFeatureSize(1, 0, 2)
        ).build());

        // Mystical Flowers
        for(ResourceKey<ConfiguredFeature<?,?>> key : MYSTICAL_FLOWER_FEATURES.keySet()) {
            register(context, key, Feature.FLOWER, new RandomPatchConfiguration(64, 7, 3,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BotaniaBlocks.getDoubleFlower(MYSTICAL_FLOWER_FEATURES.get(key))))))); // 7 fucking closing parentheses, fucking SEVEN dude
        }
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration
            ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
