package ru.casualplayer.alfheim.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AlfheimModOreBlock extends DropExperienceBlock {

    public AlfheimModOreBlock(int minxp, int maxxp) {
        super(BlockBehaviour.Properties.of()
                .strength(3.5f)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops(),
                UniformInt.of(minxp,maxxp)
        );
    }
}
