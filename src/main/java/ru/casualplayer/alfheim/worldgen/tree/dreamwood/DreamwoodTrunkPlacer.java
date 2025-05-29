package ru.casualplayer.alfheim.worldgen.tree.dreamwood;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import ru.casualplayer.alfheim.worldgen.tree.AlfheimTrunkPlacerTypes;

import java.util.List;
import java.util.function.BiConsumer;

public class DreamwoodTrunkPlacer extends TrunkPlacer {

    public static final Codec<DreamwoodTrunkPlacer> CODEC = RecordCodecBuilder.create(dreamwoodTrunkPlacerInstance ->
            trunkPlacerParts(dreamwoodTrunkPlacerInstance).apply(dreamwoodTrunkPlacerInstance, DreamwoodTrunkPlacer::new));

    public DreamwoodTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return AlfheimTrunkPlacerTypes.DREAMWOOD_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration treeConfig) {

        setDirtAt(level, consumer, random, pos.below(), treeConfig);

        // Roots
        placeLog(level, consumer, random, pos.east(-6).above(-3).south(2), treeConfig);
        placeLog(level, consumer, random, pos.east(-4).above(-3).south(-5), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(-3).south(6), treeConfig);
        placeLog(level, consumer, random, pos.east(3).above(-2).south(-6), treeConfig);
        placeLog(level, consumer, random, pos.east(4).above(-2).south(6), treeConfig);
        placeLog(level, consumer, random, pos.east(6).above(-2).south(-2), treeConfig);

        placeLog(level, consumer, random, pos.east(-4).above(-1).south(-5), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(-1).south(-4), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(-1).south(-3), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(-1).south(-4), treeConfig);
        placeLog(level, consumer, random, pos.east(3).above(-1).south(-6), treeConfig);
        placeLog(level, consumer, random, pos.east(3).above(-1).south(-5), treeConfig);
        placeLog(level, consumer, random, pos.east(5).above(-1).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(6).above(-1).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(-5).above(-1).south(1), treeConfig);
        placeLog(level, consumer, random, pos.east(-4).above(-1).south(1), treeConfig);
        placeLog(level, consumer, random, pos.east(-6).above(-1).south(2), treeConfig);
        placeLog(level, consumer, random, pos.east(-2).above(-1).south(4), treeConfig);
        placeLog(level, consumer, random, pos.east(3).above(-1).south(4), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(-1).south(5), treeConfig);
        placeLog(level, consumer, random, pos.east(7).above(-1).south(5), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(-1).south(6), treeConfig);
        placeLog(level, consumer, random, pos.east(4).above(-1).south(6), treeConfig);

        placeLog(level, consumer, random, pos.east(2).above(0).south(-4), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(0).south(-3), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(0).south(-3), treeConfig);
        placeLog(level, consumer, random, pos.east(-2).above(0).south(-2), treeConfig);
        placeLog(level, consumer, random, pos.east(1).above(0).south(-2), treeConfig);
        placeLog(level, consumer, random, pos.east(-2).above(0).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(0).above(0).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(1).above(0).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(5).above(0).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(0).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(-1).above(0).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(0).above(0).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(1).above(0).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(3).above(0).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(4).above(0).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(-4).above(0).south(1), treeConfig);
        placeLog(level, consumer, random, pos.east(0).above(0).south(1), treeConfig);
        placeLog(level, consumer, random, pos.east(-1).above(0).south(2), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(0).south(2), treeConfig);
        placeLog(level, consumer, random, pos.east(-1).above(0).south(3), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(0).south(3), treeConfig);
        placeLog(level, consumer, random, pos.east(-2).above(0).south(4), treeConfig);
        placeLog(level, consumer, random, pos.east(3).above(0).south(4), treeConfig);

        // Base Trunk
        placeLog(level, consumer, random, pos.east(-1).above(1).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(0).above(1).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(-2).above(1).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(-1).above(1).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(0).above(1).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(1).above(1).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(1).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(-1).above(1).south(1), treeConfig);
        placeLog(level, consumer, random, pos.east(0).above(1).south(1), treeConfig);
        placeLog(level, consumer, random, pos.east(1).above(1).south(1), treeConfig);

        // Vertical Trunk
        placeLog(level, consumer, random, pos.above(2), treeConfig);
        placeLog(level, consumer, random, pos.above(3), treeConfig);
        placeLog(level, consumer, random, pos.above(4), treeConfig);
        placeLog(level, consumer, random, pos.above(5), treeConfig);
        placeLog(level, consumer, random, pos.above(6), treeConfig);
        placeLog(level, consumer, random, pos.above(7), treeConfig);

        // Branches
        placeLog(level, consumer, random, pos.east(-1).above(8).south(-2), treeConfig);
        placeLog(level, consumer, random, pos.east(0).above(8).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(-2).above(8).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(-1).above(8).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(0).above(8).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(1).above(8).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(0).above(8).south(1), treeConfig);
        placeLog(level, consumer, random, pos.east(0).above(8).south(2), treeConfig);

        placeLog(level, consumer, random, pos.east(-2).above(9).south(-4), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(9).south(-4), treeConfig);
        placeLog(level, consumer, random, pos.east(-2).above(9).south(-3), treeConfig);
        placeLog(level, consumer, random, pos.east(5).above(9).south(-3), treeConfig);
        placeLog(level, consumer, random, pos.east(3).above(9).south(-2), treeConfig);
        placeLog(level, consumer, random, pos.east(4).above(9).south(-2), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(9).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(1).above(9).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(9).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(-2).above(9).south(0), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(9).south(1), treeConfig);
        placeLog(level, consumer, random, pos.east(1).above(9).south(1), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(9).south(1), treeConfig);
        placeLog(level, consumer, random, pos.east(3).above(9).south(1), treeConfig);
        placeLog(level, consumer, random, pos.east(-4).above(9).south(2), treeConfig);
        placeLog(level, consumer, random, pos.east(3).above(9).south(2), treeConfig);
        placeLog(level, consumer, random, pos.east(4).above(9).south(2), treeConfig);
        placeLog(level, consumer, random, pos.east(5).above(9).south(2), treeConfig);
        placeLog(level, consumer, random, pos.east(6).above(9).south(2), treeConfig);
        placeLog(level, consumer, random, pos.east(-4).above(9).south(3), treeConfig);
        placeLog(level, consumer, random, pos.east(-1).above(9).south(3), treeConfig);
        placeLog(level, consumer, random, pos.east(-1).above(9).south(4), treeConfig);

        placeLog(level, consumer, random, pos.east(-6).above(10).south(-2), treeConfig);
        placeLog(level, consumer, random, pos.east(-5).above(10).south(-2), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(10).south(-6), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(10).south(-6), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(10).south(-5), treeConfig);
        placeLog(level, consumer, random, pos.east(-2).above(10).south(-5), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(10).south(-5), treeConfig);
        placeLog(level, consumer, random, pos.east(-4).above(10).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(-3).above(10).south(-1), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(10).south(2), treeConfig);
        placeLog(level, consumer, random, pos.east(-7).above(10).south(3), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(10).south(3), treeConfig);
        placeLog(level, consumer, random, pos.east(2).above(10).south(4), treeConfig);
        placeLog(level, consumer, random, pos.east(-1).above(10).south(5), treeConfig);
        placeLog(level, consumer, random, pos.east(3).above(10).south(5), treeConfig);
        placeLog(level, consumer, random, pos.east(-2).above(10).south(6), treeConfig);
        placeLog(level, consumer, random, pos.east(3).above(10).south(6), treeConfig);
        placeLog(level, consumer, random, pos.east(-2).above(10).south(7), treeConfig);

        // yes I did try to use switch()

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.east(-7).south(-7), 0, false));
    }
}
