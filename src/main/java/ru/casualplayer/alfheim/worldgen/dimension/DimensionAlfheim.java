package ru.casualplayer.alfheim.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.worldgen.biome.AlfheimBiomes;

import java.util.List;
import java.util.OptionalLong;

public class DimensionAlfheim {

    public static final ResourceKey<LevelStem> ALFHEIM_DIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, "alfheim"));
    public static final ResourceKey<Level> ALFHEIM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, "alfheim"));
    public static final ResourceKey<DimensionType> ALFHEIM_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, "alfheim_type"));

    protected static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(ALFHEIM_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000), // day/night length
                true, // has skylight
                false, // has ceiling
                false, // ultra warm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                320, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // the block that can burn infinitely in this dimension
                ALFHEIM_DIM_TYPE.location(), // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    protected static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(AlfheimBiomes.ALFHEIM_PLAINS)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0.0F, 0.1F, 0.3F, 0.5F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(AlfheimBiomes.ALFHEIM_PLAINS))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ALFHEIM_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(ALFHEIM_DIM_KEY, stem);
    }

}
