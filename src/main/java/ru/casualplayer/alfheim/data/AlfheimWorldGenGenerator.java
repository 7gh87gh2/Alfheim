package ru.casualplayer.alfheim.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.worldgen.AlfheimBiomeModifiers;
import ru.casualplayer.alfheim.worldgen.AlfheimConfiguredFeatures;
import ru.casualplayer.alfheim.worldgen.AlfheimPlacedFeatures;
import ru.casualplayer.alfheim.worldgen.biome.AlfheimBiomes;
import ru.casualplayer.alfheim.worldgen.dimension.AlfheimModDimensions;
import ru.casualplayer.alfheim.worldgen.generation.AlfheimModGenerationSettings;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class AlfheimWorldGenGenerator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, AlfheimModDimensions::bootstrapModDimensionTypes)
            .add(Registries.LEVEL_STEM, AlfheimModDimensions::bootstrapModDimensionStems)
            .add(Registries.CONFIGURED_FEATURE, AlfheimConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, AlfheimPlacedFeatures::bootstrap)
            .add(Registries.BIOME, AlfheimBiomes::bootstrap)
            .add(Registries.NOISE_SETTINGS, context -> {
                context.register(AlfheimModGenerationSettings.ALFHEIM_NOISE_GENERATION_SETTINGS, AlfheimModGenerationSettings.createAlfheimSettings());
            })
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, AlfheimBiomeModifiers::bootstrap)
            ;

    public AlfheimWorldGenGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(AlfheimMod.MODID));
    }
}
