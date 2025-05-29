package ru.casualplayer.alfheim.block.alfheim_portal;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import ru.casualplayer.alfheim.block.AlfheimModBlocks;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.item.WandOfTheForestItem;
import vazkii.patchouli.api.IMultiblock;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.function.Supplier;

public class AlfheimPortalCore extends Block {

    public static final Supplier<IMultiblock> MULTIBLOCK = Suppliers.memoize(() -> PatchouliAPI.get().makeMultiblock(
            new String[][] {
                    {"_", "d", "g", "d", "_"},
                    {"d", " ", " ", " ", "d"},
                    {"g", " ", " ", " ", "g"},
                    {"d", " ", " ", " ", "d"},
                    {"_", "d", "0", "d", "_"},
            },
            'd', BotaniaBlocks.dreamwood,
            'g', BotaniaBlocks.dreamwoodGlimmering,
            '0', AlfheimModBlocks.ALFHEIM_PORTAL_CORE.get()
    ));

    private boolean isCorrectMultiblock(Level level, BlockState state, BlockPos pos) {
        return MULTIBLOCK.get().validate(level, pos, this.getRotation(state));
    }

    private Rotation getRotation(BlockState state) {
        return switch(state.getValue(FACING)) {
            case NORTH, SOUTH -> Rotation.NONE;
            case WEST, EAST -> Rotation.CLOCKWISE_90;
            case DOWN, UP -> null;
        };
    }

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public AlfheimPortalCore() {
        super(BlockBehaviour.Properties.of()
                .strength(2)
                .sound(SoundType.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .mapColor(MapColor.COLOR_LIGHT_BLUE)
                .lightLevel(state -> state.getValue(ACTIVE) ? 15 : 0)
        );
        this.registerDefaultState(this.getStateDefinition().any().setValue(ACTIVE, false).setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.getStateDefinition().any().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(ACTIVE, false);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        // Only work on server
        if(level.isClientSide) return InteractionResult.PASS;

        // Check if clicked with Wand of the Forest
        if(!(player.getItemInHand(hand).getItem() instanceof WandOfTheForestItem)) return InteractionResult.PASS;

        // Check if the Portal Core is already activated -> if yes do nothing
        if(state.getValue(ACTIVE)) return InteractionResult.PASS;

        // Check for multiblock structure, and if it is correct, enable portal if it is disabled
        if(!this.isCorrectMultiblock(level, state, pos)) return InteractionResult.PASS;

        BlockPos startPosition = pos;
        BlockState portal = AlfheimModBlocks.ALFHEIM_PORTAL_BLOCK.get().getStateDefinition().any().setValue(FACING, state.getValue(FACING));
        Direction right = null;
            switch (state.getValue(FACING)) {
                case NORTH -> {
                    startPosition = pos.above().east();
                    right = Direction.WEST;
                }
                case SOUTH -> {
                    startPosition = pos.above().west();
                    right = Direction.EAST;
                }
                case EAST -> {
                    startPosition = pos.above().south();
                    right = Direction.NORTH;
                }
                case WEST -> {
                    startPosition = pos.above().north();
                    right = Direction.SOUTH;
                }
            }
        for(int i=0;i<3;++i) {
            for(int j=0;j<3;++j) {
                // Place 3x3 Alfheim Portal Blocks
                assert right != null;
                level.setBlock(startPosition.above(j).relative(right, i), portal, 2);
            }
        }
        level.setBlockAndUpdate(pos, state.setValue(ACTIVE, true));
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        // If Portal Core isn't activated -> just drop the block
        if(!state.getValue(ACTIVE)) return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);

        BlockPos startPosition = pos;
        Direction right = null;
        switch (state.getValue(FACING)) {
            case NORTH -> {
                startPosition = pos.above().east();
                right = Direction.WEST;
            }
            case SOUTH -> {
                startPosition = pos.above().west();
                right = Direction.EAST;
            }
            case EAST -> {
                startPosition = pos.above().south();
                right = Direction.NORTH;
            }
            case WEST -> {
                startPosition = pos.above().north();
                right = Direction.SOUTH;
            }
        }
        for(int i=0;i<3;++i) {
            for(int j=0;j<3;++j) {
                // Destroy 3x3 Alfheim Portal Blocks
                assert right != null;
                level.destroyBlock(startPosition.above(j).relative(right, i), false, player, 2);
            }
        }

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ACTIVE, FACING);
    }
}
