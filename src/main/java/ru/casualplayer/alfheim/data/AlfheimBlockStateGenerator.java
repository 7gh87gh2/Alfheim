package ru.casualplayer.alfheim.data;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.block.AlfheimModBlocks;

public class AlfheimBlockStateGenerator extends BlockStateProvider {

    public AlfheimBlockStateGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AlfheimMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        // Terrain Blocks
        blockWithItem(AlfheimModBlocks.ALFHEIM_STONE);
        blockWithItem(AlfheimModBlocks.ALFHEIM_COBBLESTONE);
        blockWithItem(AlfheimModBlocks.ALFHEIM_DIRT);

        // Tree Blocks
        blockWithItem(AlfheimModBlocks.DREAMWOOD_LEAVES);

        // Ores
        blockWithItem(AlfheimModBlocks.URU_METAL_ORE);
        blockWithItem(AlfheimModBlocks.ELEMENTIUM_ORE);
        blockWithItem(AlfheimModBlocks.DRAGONSTONE_ORE);
        blockWithItem(AlfheimModBlocks.MITHRIL_ORE);

        // Raw Ore Blocks
        blockWithItem(AlfheimModBlocks.RAW_URU_METAL_BLOCK);
        blockWithItem(AlfheimModBlocks.RAW_ELEMENTIUM_BLOCK);
        blockWithItem(AlfheimModBlocks.RAW_MITHRIL_BLOCK);

        // Storage Blocks
        blockWithItem(AlfheimModBlocks.URU_METAL_BLOCK);
        blockWithItem(AlfheimModBlocks.MITHRIL_BLOCK);

        // Saplings
        sapling(AlfheimModBlocks.DREAMWOOD_SAPLING);
    }

    private void sapling(RegistryObject<Block> block) {
        simpleBlock(block.get(), models().cross(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), blockTexture(block.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
