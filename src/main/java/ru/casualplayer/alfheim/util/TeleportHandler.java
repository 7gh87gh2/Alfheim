package ru.casualplayer.alfheim.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraftforge.common.util.ITeleporter;
import ru.casualplayer.alfheim.block.alfheim_portal.AlfheimPortalCore;

import java.util.function.Function;

public class TeleportHandler implements ITeleporter {

    private static BlockPos corePos;

    public TeleportHandler(BlockPos pos) {
        corePos = pos;
    }

    private Rotation getPortalRotation(Level level, BlockPos pos) {
        return AlfheimPortalCore.MULTIBLOCK.get().validate(level, pos);
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        entity.setPos(corePos.getX(), corePos.getY(), corePos.getZ());

        return entity;
    }

}
