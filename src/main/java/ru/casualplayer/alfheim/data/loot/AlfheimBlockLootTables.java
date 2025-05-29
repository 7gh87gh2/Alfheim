package ru.casualplayer.alfheim.data.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import ru.casualplayer.alfheim.block.AlfheimModBlocks;
import ru.casualplayer.alfheim.item.AlfheimModItems;
import vazkii.botania.common.item.BotaniaItems;

import java.util.Set;

public class AlfheimBlockLootTables extends BlockLootSubProvider {

    public AlfheimBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        // Stone -> Cobblestone
        this.dropOther(AlfheimModBlocks.ALFHEIM_STONE.get(), AlfheimModBlocks.ALFHEIM_COBBLESTONE.get());

        // Leaves
        this.add(AlfheimModBlocks.DREAMWOOD_LEAVES.get(), block -> createLeavesDrops(block, AlfheimModBlocks.DREAMWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Self Dropping
        this.dropSelf(AlfheimModBlocks.ALFHEIM_COBBLESTONE.get());
        this.dropSelf(AlfheimModBlocks.ALFHEIM_DIRT.get());
        this.dropSelf(AlfheimModBlocks.RAW_URU_METAL_BLOCK.get());
        this.dropSelf(AlfheimModBlocks.RAW_ELEMENTIUM_BLOCK.get());
        this.dropSelf(AlfheimModBlocks.RAW_MITHRIL_BLOCK.get());
        this.dropSelf(AlfheimModBlocks.URU_METAL_BLOCK.get());
        this.dropSelf(AlfheimModBlocks.MITHRIL_BLOCK.get());

        this.dropSelf(AlfheimModBlocks.DREAMWOOD_SAPLING.get());

        this.dropSelf(AlfheimModBlocks.ALFHEIM_PORTAL_CORE.get());

        // Ores
        this.add(AlfheimModBlocks.URU_METAL_ORE.get(), block -> createOreDrop(AlfheimModBlocks.URU_METAL_ORE.get(), AlfheimModItems.RAW_URU_METAL.get()));
        this.add(AlfheimModBlocks.ELEMENTIUM_ORE.get(), block -> createOreDrop(AlfheimModBlocks.ELEMENTIUM_ORE.get(), AlfheimModItems.RAW_ELEMENTIUM.get()));
        this.add(AlfheimModBlocks.DRAGONSTONE_ORE.get(), block -> createOreDrop(AlfheimModBlocks.DRAGONSTONE_ORE.get(), BotaniaItems.dragonstone));
        this.add(AlfheimModBlocks.MITHRIL_ORE.get(), block -> createOreDrop(AlfheimModBlocks.MITHRIL_ORE.get(), AlfheimModItems.RAW_MITHRIL.get()));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return AlfheimModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
