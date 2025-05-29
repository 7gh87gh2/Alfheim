package ru.casualplayer.alfheim.block.alfheim_portal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.casualplayer.alfheim.util.PlayerEventHandler;
import ru.casualplayer.alfheim.util.TeleportHandler;
import ru.casualplayer.alfheim.worldgen.dimension.DimensionAlfheim;

public class AlfheimPortalBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape NORTH_SOUTH = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape EAST_WEST = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    private static final long TELEPORT_COOLDOWN = 100; // ticks

    public AlfheimPortalBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.NETHER_PORTAL).noLootTable());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            default -> NORTH_SOUTH;
            case EAST, WEST -> EAST_WEST;
        };
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if(level.isClientSide) return;
        if(canTeleport(entity) && entity.canChangeDimensions()) {
            handleTeleport(entity, pos);
        }
    }

    private boolean canTeleport(Entity player) {
        CompoundTag persistentData = player.getPersistentData();
        long lastTeleport = persistentData.getLong(PlayerEventHandler.TIME_OF_LAST_TELEPORT);
        long currentTime = player.level().getGameTime();

        return (currentTime - lastTeleport) >= TELEPORT_COOLDOWN;
    }

    private void setTeleportCooldown(Entity player) {
        CompoundTag persistentData = player.getPersistentData();
        persistentData.putLong(PlayerEventHandler.TELEPORT_COOLDOWN_KEY, TELEPORT_COOLDOWN);
    }

    private void handleTeleport(Entity player, BlockPos pPos) {
        if(!canTeleport(player)) return;
        if (player.level() instanceof ServerLevel serverlevel) {

            CompoundTag persistentData = player.getPersistentData();
            long currentTime = player.level().getGameTime();

            // Teleporting the entity to the new dimension
            MinecraftServer minecraftserver = serverlevel.getServer();
            ResourceKey<Level> resourcekey =
                    player.level().dimension() == DimensionAlfheim.ALFHEIM_LEVEL_KEY // is player's dimension Alfheim?
                            ? Level.OVERWORLD // yes -> tp to overworld
                            : DimensionAlfheim.ALFHEIM_LEVEL_KEY; // no -> tp to Alfheim

            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
            assert portalDimension != null;
            player.changeDimension(portalDimension, new TeleportHandler(pPos));
            persistentData.putLong(PlayerEventHandler.TIME_OF_LAST_TELEPORT, currentTime);
            setTeleportCooldown(player);
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
