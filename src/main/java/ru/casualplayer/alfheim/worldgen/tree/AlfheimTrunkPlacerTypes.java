package ru.casualplayer.alfheim.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.worldgen.tree.dreamwood.DreamwoodTrunkPlacer;

public class AlfheimTrunkPlacerTypes {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, AlfheimMod.MODID);

    public static final RegistryObject<TrunkPlacerType<DreamwoodTrunkPlacer>> DREAMWOOD_TRUNK_PLACER = TRUNK_PLACERS.register("dreamwood_trunk_placer",
            ()->new TrunkPlacerType<>(DreamwoodTrunkPlacer.CODEC));

    public static void register(IEventBus bus) {
        TRUNK_PLACERS.register(bus);
    }

}
