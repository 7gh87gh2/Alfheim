package ru.casualplayer.alfheim.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AlfheimModDirt extends Block {

    public AlfheimModDirt() {
        super(BlockBehaviour.Properties.copy(Blocks.DIRT));
    }

}
