package ru.casualplayer.alfheim.worldgen.generation;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.block.AlfheimModBlocks;
import ru.casualplayer.alfheim.worldgen.biome.AlfheimBiomes;

import java.util.List;

public class AlfheimModGenerationSettings {

    public static final DeferredRegister<NoiseGeneratorSettings> MOD_NOISE_GENERATOR_SETTINGS = DeferredRegister.create(Registries.NOISE_SETTINGS, AlfheimMod.MODID);

    public static final ResourceKey<NoiseGeneratorSettings> ALFHEIM_NOISE_GENERATION_SETTINGS = ResourceKey.create(
            Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, "alfheim_noise_generation_settings"));


    public static NoiseGeneratorSettings createAlfheimSettings() {
        NoiseSettings settings = NoiseSettings.create(
                -64,
                384,
                1,
                2
        );

        SurfaceRules.RuleSource surfaceRules = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(AlfheimBiomes.ALFHEIM_PLAINS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), SurfaceRules.state(Blocks.GRASS_BLOCK.defaultBlockState())),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(AlfheimModBlocks.ALFHEIM_DIRT.get().defaultBlockState()))
                        )
                )
        );

        DensityFunction zero = DensityFunctions.zero();
        NoiseRouter noiseRouter = new NoiseRouter(
                zero, // barrier (для аквиферов)
                zero, // fluidLevelFloodedness (затопленность)
                zero, // fluidLevelSpread (распространение жидкости)
                zero, // lava (лава)
                zero, // temperature (температура)
                zero, // vegetation (растительность)
                zero, // continents (континенты)
                zero, // erosion (эрозия)
                zero, // depth (глубина)
                zero, // ridges (хребты)
                DensityFunctions.constant(1.0), // initialDensity (начальная плотность)
                DensityFunctions.yClampedGradient(-64, 384, 1.0, -1.0), // finalDensity (финальная плотность, градиент по высоте)
                zero, // veinToggle (жилы)
                zero, // veinRidged (жилы с хребтами)
                zero  // veinGap (зазор жил)
        );

        return new NoiseGeneratorSettings(
                settings,
                Blocks.STONE.defaultBlockState(), // default block
                Blocks.WATER.defaultBlockState(), // default fluid
                noiseRouter,
                surfaceRules,
                List.of(),
                64,
                false,
                false,
                false,
                false
        );
    }

    public static void register(IEventBus bus) {
        MOD_NOISE_GENERATOR_SETTINGS.register(bus);
    }

}
