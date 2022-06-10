package org.villainy.lottaterracotta.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.function.Function;

public class VerticalSlabBlock extends Block implements Waterloggable {
    public static final EnumProperty<VerticalSlabType> TYPE = EnumProperty.of("type", VerticalSlabType.class);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public VerticalSlabBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(TYPE, VerticalSlabType.NORTH).with(WATERLOGGED, false));
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return state.get(TYPE) != VerticalSlabType.DOUBLE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(TYPE).shape;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockPos blockpos = context.getBlockPos();
        BlockState blockstate = context.getWorld().getBlockState(blockpos);
        if(blockstate.getBlock() == this) {
            return blockstate.with(TYPE, VerticalSlabType.DOUBLE).with(WATERLOGGED, false);
        }
        FluidState fluid = context.getWorld().getFluidState(blockpos);
        BlockState retState = getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
        Direction direction = getDirectionForPlacement(context);
        VerticalSlabType type = VerticalSlabType.fromDirection(direction);
        return retState.with(TYPE, type);
    }

    private static Direction getDirectionForPlacement(ItemPlacementContext context) {
        Direction direction = context.getPlayerLookDirection();
        if(direction.getAxis() != Direction.Axis.Y) {
            return direction;
        }
        BlockPos pos = context.getBlockPos();
        Vec3d vec = context.getHitPos().subtract(new Vec3d(pos.getX(), pos.getY(), pos.getZ())).subtract(0.5, 0, 0.5);
        double angle = Math.atan2(vec.x, vec.z) * -180.0 / Math.PI;
        return Direction.fromRotation(angle).getOpposite();
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext useContext) {
        ItemStack itemstack = useContext.getStack();
        VerticalSlabType slabtype = state.get(TYPE);
        return slabtype != VerticalSlabType.DOUBLE && itemstack.getItem() == asItem() && useContext.hitsInsideBlock() &&
                (useContext.getPlayerFacing() == slabtype.direction && getDirectionForPlacement(useContext) == slabtype.direction);
    }

    @SuppressWarnings("deprecation")
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.get(TYPE) != VerticalSlabType.DOUBLE && Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
    }

    @Override
    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(TYPE) != VerticalSlabType.DOUBLE && Waterloggable.super.canFillWithFluid(world, pos, state, fluid);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return type == NavigationType.WATER && world.getFluidState(pos).isIn(FluidTags.WATER);
    }

    public enum VerticalSlabType implements Serializable, StringIdentifiable {
        NORTH(Direction.NORTH),
        SOUTH(Direction.SOUTH),
        WEST(Direction.WEST),
        EAST(Direction.EAST),
        DOUBLE(null);

        private final String name;
        public final Direction direction;
        public final VoxelShape shape;

        VerticalSlabType(Direction directionIn) {
            direction = directionIn;
            name = direction == null ? "double" : direction.getName();
            if (direction == null)
                shape = VoxelShapes.fullCube();
            else {
                double min = 0;
                double max = 8;
                if (direction.getDirection() == Direction.AxisDirection.NEGATIVE) {
                    min = 8;
                    max = 16;
                }
                if (direction.getAxis() == Direction.Axis.X) {
                    shape = Block.createCuboidShape(min, 0, 0, max, 16, 16);
                } else shape = Block.createCuboidShape(0, 0, min, 16, 16, max);
            }
        }

        @Override
        public String toString() {
            return name;
        }

        public static VerticalSlabType fromDirection(Direction direction) {
            for (VerticalSlabType type : VerticalSlabType.values()) {
                if (type.direction != null && direction == type.direction) {
                    return type;
                }
            }
            return null;
        }

        @Override
        public String asString() {
            return name;
        }
    }
}

