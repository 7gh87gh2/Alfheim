package ru.casualplayer.alfheim.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class AlfheimModStone extends Block {

    public AlfheimModStone() {
        super(Properties.copy(Blocks.STONE));
    }

//    @Override
//    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
//        if(player.canChangeDimensions()) {
//            handleTeleport(player, pos);
//            return InteractionResult.SUCCESS;
//        } else {
//            return InteractionResult.CONSUME;
//        }
//    }
//
//    private void handleTeleport(Entity player, BlockPos pos) {
//        if(player.level() instanceof ServerLevel server) {
//            MinecraftServer minecraftServer = server.getServer();
//            ResourceKey<Level> resourceKey = player.level().dimension() == AlfheimModDimensions.ALFHEIM_LEVEL_KEY ? Level.OVERWORLD : AlfheimModDimensions.ALFHEIM_LEVEL_KEY;
//
//            ServerLevel portalDimension = minecraftServer.getLevel(resourceKey);
//            if(portalDimension != null && !player.isPassenger()) {
//                if(resourceKey == AlfheimModDimensions.ALFHEIM_LEVEL_KEY) {
//                    player.changeDimension(portalDimension, new AlfheimModTeleporter(pos, true));
//                } else {
//                    player.changeDimension(portalDimension, new AlfheimModTeleporter(pos, false));
//                }
//            }
//        }
//    }
}
