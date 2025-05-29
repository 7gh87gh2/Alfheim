package ru.casualplayer.alfheim.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.registries.RegistryObject;
import ru.casualplayer.alfheim.worldgen.AlfheimPlacedFeatures;

public class AlfheimPlains {

    public static Biome createBiome(BootstapContext<Biome> context) {

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));


        // GenerationStep.Carving
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);

        // GenerationStep.Decoration
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);

        AlfheimBiomes.addAlfheimOres(biomeBuilder);
        // TODO: add mystical mushrooms to caves
        //BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AlfheimPlacedFeatures.DREAMWOOD_PLACED_KEY);
        AlfheimBiomes.addMysticalFlowers(biomeBuilder);

        // Mobs
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x31ebff)
                        .waterFogColor(0x2ac4d4)
                        .skyColor(0xa1f6ff)
                        .grassColorOverride(0x00c439)
                        .foliageColorOverride(0x00c439)
                        .fogColor(0xaae6e4)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.GAME).build())
                .build();
    }
}
