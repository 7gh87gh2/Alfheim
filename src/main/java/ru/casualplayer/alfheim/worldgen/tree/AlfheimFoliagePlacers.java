package ru.casualplayer.alfheim.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.worldgen.tree.dreamwood.DreamwoodFoliagePlacer;

public class AlfheimFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, AlfheimMod.MODID);

    public static final RegistryObject<FoliagePlacerType<DreamwoodFoliagePlacer>> DREAMWOOD_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("dreamwood_foliage_placer", ()->new FoliagePlacerType<>(DreamwoodFoliagePlacer.CODEC));

    public static void register(IEventBus bus) { FOLIAGE_PLACERS.register(bus); }
}
