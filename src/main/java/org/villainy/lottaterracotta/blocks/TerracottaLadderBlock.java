package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.helper.Shape;
import org.villainy.lottaterracotta.objectholders.TerracottaLadderBlocks;

import java.util.stream.Stream;

public class TerracottaLadderBlock extends LadderBlock {

    protected static final AxisAlignedBB LADDER_UNROTATED_AABB = Shape.getPixeledAABB(1.75, 0, 0, 14.25, 16, 1.5);
    protected static final VoxelShape LADDER_SOUTH_AABB =  VoxelShapes.create(Shape.getRotatedAABB(LADDER_UNROTATED_AABB, Direction.SOUTH, false));
    protected static final VoxelShape LADDER_EAST_AABB  = VoxelShapes.create(Shape.getRotatedAABB(LADDER_UNROTATED_AABB, Direction.EAST, false));
    protected static final VoxelShape LADDER_WEST_AABB  = VoxelShapes.create(Shape.getRotatedAABB(LADDER_UNROTATED_AABB, Direction.WEST, false));
    protected static final VoxelShape LADDER_NORTH_AABB = VoxelShapes.create(Shape.getRotatedAABB(LADDER_UNROTATED_AABB, Direction.NORTH, false));

    private boolean isEnabled() {
        return LottaTerracottaConfig.enableLadders;
    }

    public TerracottaLadderBlock(DyeColor dyeColor) {
        super(Block.Properties.create(Material.ROCK, dyeColor).notSolid().hardnessAndResistance(0.4F).sound(SoundType.LADDER));
        setRegistryName(dyeColor.getTranslationKey() + "_terracotta_ladder");
    }

    public TerracottaLadderBlock() {
        super(Block.Properties.create(Material.ROCK, MaterialColor.ADOBE).hardnessAndResistance(0.4F).sound(SoundType.LADDER));
        setRegistryName("terracotta_ladder");
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH: return LADDER_NORTH_AABB;
            case SOUTH: return LADDER_SOUTH_AABB;
            case WEST: return LADDER_WEST_AABB;
            default: return LADDER_EAST_AABB;
        }
    }

    public static Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaLadderBlocks.UNCOLORED,
                TerracottaLadderBlocks.WHITE,
                TerracottaLadderBlocks.ORANGE,
                TerracottaLadderBlocks.MAGENTA,
                TerracottaLadderBlocks.LIGHT_BLUE,
                TerracottaLadderBlocks.YELLOW,
                TerracottaLadderBlocks.LIME,
                TerracottaLadderBlocks.PINK,
                TerracottaLadderBlocks.GRAY,
                TerracottaLadderBlocks.LIGHT_GRAY,
                TerracottaLadderBlocks.CYAN,
                TerracottaLadderBlocks.PURPLE,
                TerracottaLadderBlocks.BLUE,
                TerracottaLadderBlocks.BROWN,
                TerracottaLadderBlocks.GREEN,
                TerracottaLadderBlocks.RED,
                TerracottaLadderBlocks.BLACK
        );
    }
}
