package ru.casualplayer.alfheim.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.worldgen.biome.AlfheimBiomes;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = AlfheimMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new AlfheimRecipeGenerator(packOutput));                            // Recipes
        generator.addProvider(event.includeServer(), AlfheimLootTableGenerator.create(packOutput));                      // Loot Tables
        generator.addProvider(event.includeClient(), new AlfheimBlockStateGenerator(packOutput, existingFileHelper));    // Block States and Models
        generator.addProvider(event.includeClient(), new AlfheimItemModelGenerator(packOutput, existingFileHelper));     // Item Models

        // World Gen
        generator.addProvider(event.includeServer(), new AlfheimWorldGenGenerator(packOutput, lookupProvider));

        // Tags
        AlfheimBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(), new AlfheimBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new AlfheimItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        //generator.addProvider(event.includeServer(), new AlfheimBiomeTagsGenerator(packOutput, lookupProvider, existingFileHelper));
    }
}
