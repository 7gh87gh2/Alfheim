package ru.casualplayer.alfheim.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.util.AlfheimTags;
import ru.casualplayer.alfheim.worldgen.biome.AlfheimBiomes;

import java.util.concurrent.CompletableFuture;

public class AlfheimBiomeTagsGenerator extends BiomeTagsProvider {

    public AlfheimBiomeTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AlfheimMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        this.tag(AlfheimTags.Biomes.ALFHEIM_BIOMES)
                .add(AlfheimBiomes.ALFHEIM_PLAINS)
        ;
    }
}
