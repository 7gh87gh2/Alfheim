package ru.casualplayer.alfheim.worldgen.tree.dreamwood;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import ru.casualplayer.alfheim.worldgen.AlfheimConfiguredFeatures;

public class DreamwoodTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
        return AlfheimConfiguredFeatures.DREAMWOOD_KEY;
    }
}
