package ru.casualplayer.alfheim;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.casualplayer.alfheim.block.AlfheimModBlocks;
import ru.casualplayer.alfheim.item.AlfheimModItems;
import ru.casualplayer.alfheim.worldgen.biome.AlfheimBiomes;
import ru.casualplayer.alfheim.worldgen.generation.AlfheimModGenerationSettings;
import ru.casualplayer.alfheim.worldgen.tree.AlfheimFoliagePlacers;
import ru.casualplayer.alfheim.worldgen.tree.AlfheimTrunkPlacerTypes;

@Mod(AlfheimMod.MODID)
public class AlfheimMod {
    public static final String MODID = "alfheim";

    public AlfheimMod(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();

        // Blocks, Items, CreativeTabs
        AlfheimModItems.register(bus);
        AlfheimModBlocks.register(bus);
        AlfheimModItems.AlfheimCreativeTabs.register(bus);

        // Worldgen
        AlfheimTrunkPlacerTypes.register(bus);
        AlfheimFoliagePlacers.register(bus);
        AlfheimModGenerationSettings.register(bus);
        AlfheimBiomes.register(bus);


        MinecraftForge.EVENT_BUS.register(this);
    }
}
