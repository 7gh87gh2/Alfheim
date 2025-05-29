package ru.casualplayer.alfheim.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class DreamwoodLeaves extends LeavesBlock {

    public DreamwoodLeaves() {
        super(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES));
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(3)).setValue(PERSISTENT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    protected boolean decaying(BlockState state) {
        return state.getValue(DISTANCE) == 14 && !state.getValue(PERSISTENT);
    }
}
