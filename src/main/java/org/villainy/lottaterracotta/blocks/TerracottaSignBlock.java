package org.villainy.lottaterracotta.blocks;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.DyeColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.TerracottaSignBlocks;
import org.villainy.lottaterracotta.tileEntities.TerracottaSignTileEntity;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static net.minecraft.state.properties.BlockStateProperties.*;

public class TerracottaSignBlock extends AbstractSignBlock {
    public DyeColor dyeColor;
    private final Map<BlockState, VoxelShape> cache = Maps.newConcurrentMap();

    private boolean isEnabled() {
        return LottaTerracottaConfig.enableSigns;
    }

    public TerracottaSignBlock(DyeColor dyeColor) {
        super(Block.Properties.create(Material.ROCK, dyeColor)
                .doesNotBlockMovement()
                .hardnessAndResistance(1.0F)
                .sound(SoundType.STONE), WoodType.OAK);
        this.dyeColor = dyeColor;
        setRegistryName(dyeColor.getTranslationKey() + "_terracotta_sign");
    }

    public TerracottaSignBlock() {
        super(Block.Properties.create(Material.ROCK, MaterialColor.ADOBE)
                .doesNotBlockMovement()
                .hardnessAndResistance(1.0F)
                .sound(SoundType.STONE), WoodType.OAK);
        this.dyeColor = null;
        setRegistryName("terracotta_sign");
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if(group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACE, HORIZONTAL_FACING, WATERLOGGED);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TerracottaSignTileEntity();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx) {
        VoxelShape cached = cache.get(state);

        if (cached == null) {
            cached = VoxelShapes.empty();

            AttachFace face = state.get(FACE);
            Direction facing = state.get(HORIZONTAL_FACING);

            // when placed in wall: left/right
            float u0 = 0.0f;
            float u1 = 1.0f;

            // when placed in wall: bottom/top
            float offset = +0.02f;
            float v0 = 0.25f;
            float v1 = 0.75f;

            // when placed in wall: wall/exposed
            float thick = 1.36f / 16.0f;
            float w0 = 0.32f / 16.0f;
            float w1 = w0 + thick;
            float t1 = 1 - w0;
            float t0 = 1 - w1;

            if (face == AttachFace.FLOOR) {
                switch (facing) {
                    case NORTH:
                        cached = VoxelShapes.create(u0, w0, v0 + offset, u1, w1, v1 + offset);
                        break;
                    case SOUTH:
                        cached = VoxelShapes.create(u0, w0, v0 - offset, u1, w1, v1 - offset);
                        break;
                    case EAST:
                        cached = VoxelShapes.create(v0 - offset, w0, u0, v1 - offset, w1, u1);
                        break;
                    case WEST:
                        cached = VoxelShapes.create(v0 + offset, w0, u0, v1 + offset, w1, u1);
                        break;
                }
            } else if (face == AttachFace.CEILING) {
                switch (facing) {
                    case NORTH:
                        cached = VoxelShapes.create(u0, t0, v0 - offset, u1, t1, v1 - offset);
                        break;
                    case SOUTH:
                        cached = VoxelShapes.create(u0, t0, v0 + offset, u1, t1, v1 + offset);
                        break;
                    case EAST:
                        cached = VoxelShapes.create(v0 + offset, t0, u0, v1 + offset, t1, u1);
                        break;
                    case WEST:
                        cached = VoxelShapes.create(v0 - offset, t0, u0, v1 - offset, t1, u1);
                        break;
                }
            } else {
                switch (facing) {
                    case EAST:
                        cached = VoxelShapes.create(w0, v0 + offset, u0, w1, v1 + offset, u1);
                        break;
                    case WEST:
                        cached = VoxelShapes.create(t0, v0 + offset, u0, t1, v1 + offset, u1);
                        break;
                    case SOUTH:
                        cached = VoxelShapes.create(u0, v0 + offset, w0, u1, v1 + offset, w1);
                        break;
                    case NORTH:
                        cached = VoxelShapes.create(u0, v0 + offset, t0, u1, v1 + offset, t1);
                        break;
                }
            }

            cache.put(state, cached);
        }

        return cached;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = this.getDefaultState();
        FluidState fluid = context.getWorld().getFluidState(context.getPos());
        IWorldReader world = context.getWorld();
        BlockPos pos = context.getPos();

        Direction clickedFace = context.getFace().getOpposite();
        List<Direction> directions = Lists.newArrayList(clickedFace);
        Arrays.stream(context.getNearestLookingDirections()).filter(f -> f != clickedFace).forEach(directions::add);

        for (int i = 0; i < directions.size(); i++) {
            Direction lookDirection = directions.get(i);
            Direction lookDirection2 = i + 1 >= directions.size() ? Direction.NORTH : directions.get(i + 1);

            AttachFace face;
            Direction facing;
            if (lookDirection == Direction.DOWN) {
                face = AttachFace.FLOOR;
                facing = (lookDirection2.getAxis() == Direction.Axis.Y ? Direction.NORTH : lookDirection2).getOpposite();
            } else if (lookDirection == Direction.UP) {
                face = AttachFace.CEILING;
                facing = (lookDirection2.getAxis() == Direction.Axis.Y ? Direction.NORTH : lookDirection2).getOpposite();
            } else {
                face = AttachFace.WALL;
                facing = lookDirection.getOpposite();
            }

            state = state.with(FACE, face).with(HORIZONTAL_FACING, facing);
            if (state.isValidPosition(world, pos)) {
                return state.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
            }
        }

        return null;
    }

    @Deprecated
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        return world.getBlockState(pos.offset(getEffectiveFacing(state).getOpposite())).getMaterial().isSolid();
    }

    private Direction getEffectiveFacing(BlockState state) {
        switch (state.get(FACE)) {
            case FLOOR:
                return Direction.UP;
            case CEILING:
                return Direction.DOWN;
            default:
                return state.get(HORIZONTAL_FACING);
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return facing.getOpposite() == getEffectiveFacing(stateIn) && !stateIn.isValidPosition(worldIn, currentPos)
                ? Blocks.AIR.getDefaultState()
                : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public static Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaSignBlocks.UNCOLORED,
                TerracottaSignBlocks.WHITE,
                TerracottaSignBlocks.ORANGE,
                TerracottaSignBlocks.MAGENTA,
                TerracottaSignBlocks.LIGHT_BLUE,
                TerracottaSignBlocks.YELLOW,
                TerracottaSignBlocks.LIME,
                TerracottaSignBlocks.PINK,
                TerracottaSignBlocks.GRAY,
                TerracottaSignBlocks.LIGHT_GRAY,
                TerracottaSignBlocks.CYAN,
                TerracottaSignBlocks.PURPLE,
                TerracottaSignBlocks.BLUE,
                TerracottaSignBlocks.BROWN,
                TerracottaSignBlocks.GREEN,
                TerracottaSignBlocks.RED,
                TerracottaSignBlocks.BLACK
        );
    }

}
