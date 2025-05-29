package ru.casualplayer.alfheim.worldgen.dimension;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public class AlfheimModDimensions {

    public static void bootstrapModDimensionTypes(BootstapContext<DimensionType> context) {
        DimensionAlfheim.bootstrapType(context);
    }

    public static void bootstrapModDimensionStems(BootstapContext<LevelStem> context) {
        DimensionAlfheim.bootstrapStem(context);
    }

}
