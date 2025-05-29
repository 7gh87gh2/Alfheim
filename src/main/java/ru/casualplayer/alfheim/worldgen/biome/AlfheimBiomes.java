package ru.casualplayer.alfheim.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.worldgen.AlfheimConfiguredFeatures;
import ru.casualplayer.alfheim.worldgen.AlfheimOrePlacement;
import ru.casualplayer.alfheim.worldgen.AlfheimPlacedFeatures;

public class AlfheimBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, AlfheimMod.MODID);

    public static final ResourceKey<Biome> ALFHEIM_PLAINS = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, "alfheim_plains_key"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(ALFHEIM_PLAINS, AlfheimPlains.createBiome(context));
    }

    public static void register(IEventBus bus) {
        BIOMES.register(bus);
    }

    public static void addAlfheimOres(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AlfheimPlacedFeatures.URU_METAL_ORE_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AlfheimPlacedFeatures.ELEMENTIUM_ORE_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AlfheimPlacedFeatures.DRAGONSTONE_ORE_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AlfheimPlacedFeatures.MITHRIL_ORE_KEY);
    }

    public static void addMysticalFlowers(BiomeGenerationSettings.Builder builder) {
        for(ResourceKey<ConfiguredFeature<?, ?>> key : AlfheimConfiguredFeatures.MYSTICAL_FLOWER_FEATURES.keySet()) {
            builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AlfheimPlacedFeatures.MYSTICAL_FLOWER_FEATURES_PLACED.get(key));
        }
    }
}
