package ru.casualplayer.alfheim.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.block.AlfheimModBlocks;
import ru.casualplayer.alfheim.util.AlfheimTags;

import java.util.concurrent.CompletableFuture;

public class AlfheimBlockTagGenerator extends BlockTagsProvider {

    public AlfheimBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AlfheimMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // Terrain Blocks
        this.tag(BlockTags.DIRT)
                .add(AlfheimModBlocks.ALFHEIM_DIRT.get())
        ;
        this.tag(Tags.Blocks.COBBLESTONE)
                .add(AlfheimModBlocks.ALFHEIM_COBBLESTONE.get())
        ;
        this.tag(Tags.Blocks.STONE)
                .add(AlfheimModBlocks.ALFHEIM_STONE.get())
        ;

        // Ores
        this.tag(Tags.Blocks.ORES)
                .add(AlfheimModBlocks.URU_METAL_ORE.get())
                .add(AlfheimModBlocks.ELEMENTIUM_ORE.get())
                .add(AlfheimModBlocks.DRAGONSTONE_ORE.get())
                .add(AlfheimModBlocks.MITHRIL_BLOCK.get())
        ;
        this.tag(AlfheimTags.Blocks.ORE_URU_METAL).add(AlfheimModBlocks.URU_METAL_ORE.get());
        this.tag(AlfheimTags.Blocks.ORE_ELEMENTIUM).add(AlfheimModBlocks.ELEMENTIUM_ORE.get());
        this.tag(AlfheimTags.Blocks.ORE_DRAGONSTONE).add(AlfheimModBlocks.DRAGONSTONE_ORE.get());
        this.tag(AlfheimTags.Blocks.ORE_MITHRIL).add(AlfheimModBlocks.MITHRIL_ORE.get());


        // Storage Blocks
        this.tag(Tags.Blocks.STORAGE_BLOCKS)
                .add(AlfheimModBlocks.URU_METAL_BLOCK.get())
                .add(AlfheimModBlocks.RAW_URU_METAL_BLOCK.get())
                .add(AlfheimModBlocks.RAW_ELEMENTIUM_BLOCK.get())
                .add(AlfheimModBlocks.MITHRIL_BLOCK.get())
                .add(AlfheimModBlocks.RAW_MITHRIL_BLOCK.get())
        ;
        this.tag(AlfheimTags.Blocks.STORAGE_BLOCK_URU_METAL).add(AlfheimModBlocks.URU_METAL_BLOCK.get());
        this.tag(AlfheimTags.Blocks.STORAGE_BLOCK_RAW_URU_METAL).add(AlfheimModBlocks.RAW_URU_METAL_BLOCK.get());
        this.tag(AlfheimTags.Blocks.STORAGE_BLOCK_RAW_ELEMENTIUM).add(AlfheimModBlocks.RAW_ELEMENTIUM_BLOCK.get());
        this.tag(AlfheimTags.Blocks.STORAGE_BLOCK_MITHRIL).add(AlfheimModBlocks.MITHRIL_BLOCK.get());
        this.tag(AlfheimTags.Blocks.STORAGE_BLOCK_RAW_MITHRIL).add(AlfheimModBlocks.RAW_MITHRIL_BLOCK.get());

        // Mineable with Pickaxe
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(AlfheimModBlocks.ALFHEIM_STONE.get())
                .add(AlfheimModBlocks.ALFHEIM_COBBLESTONE.get())
                .add(AlfheimModBlocks.URU_METAL_ORE.get())
                .add(AlfheimModBlocks.RAW_URU_METAL_BLOCK.get())
                .add(AlfheimModBlocks.URU_METAL_BLOCK.get())
                .add(AlfheimModBlocks.ELEMENTIUM_ORE.get())
                .add(AlfheimModBlocks.RAW_ELEMENTIUM_BLOCK.get())
                .add(AlfheimModBlocks.DRAGONSTONE_ORE.get())
                .add(AlfheimModBlocks.MITHRIL_ORE.get())
                .add(AlfheimModBlocks.RAW_MITHRIL_BLOCK.get())
                .add(AlfheimModBlocks.MITHRIL_BLOCK.get())
        ;

        // Stone Tool
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(AlfheimModBlocks.ALFHEIM_STONE.get())
                .add(AlfheimModBlocks.ALFHEIM_COBBLESTONE.get())
        ;
        // Iron Tool
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(AlfheimModBlocks.URU_METAL_ORE.get())
                .add(AlfheimModBlocks.RAW_URU_METAL_BLOCK.get())
                .add(AlfheimModBlocks.URU_METAL_BLOCK.get())
                .add(AlfheimModBlocks.ELEMENTIUM_ORE.get())
                .add(AlfheimModBlocks.RAW_ELEMENTIUM_BLOCK.get())
        ;
        // Diamond Tool
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(AlfheimModBlocks.MITHRIL_ORE.get())
                .add(AlfheimModBlocks.RAW_MITHRIL_BLOCK.get())
                .add(AlfheimModBlocks.MITHRIL_BLOCK.get())
        ;
    }
}
