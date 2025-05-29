package ru.casualplayer.alfheim.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import ru.casualplayer.alfheim.data.loot.AlfheimBlockLootTables;

import java.util.List;
import java.util.Set;

public class AlfheimLootTableGenerator {

    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(AlfheimBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }

}
