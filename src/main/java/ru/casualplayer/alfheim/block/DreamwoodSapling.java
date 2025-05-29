package ru.casualplayer.alfheim.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import ru.casualplayer.alfheim.worldgen.tree.dreamwood.DreamwoodTreeGrower;

public class DreamwoodSapling extends SaplingBlock {

    public DreamwoodSapling() {
        super(new DreamwoodTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING));
    }
}
