package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.GlazedTerracottaSlabBlocks;

import java.util.stream.Stream;

public class GlazedTerracottaSlabBlock extends SlabBlock {

    private boolean isEnabled() {
        return LottaTerracottaConfig.enableSlabs;
    }
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

    public GlazedTerracottaSlabBlock(DyeColor dyeColor) {
        super(Block.Properties.create(Material.ROCK, dyeColor).hardnessAndResistance(1.8F));
        setRegistryName(dyeColor.getName() + "_glazed_terracotta_slab");
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockState = super.getStateForPlacement(context);
        return blockState.with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    static public Stream<Block> allBlocks() {
        return Stream.of(
                GlazedTerracottaSlabBlocks.WHITE,
                GlazedTerracottaSlabBlocks.ORANGE,
                GlazedTerracottaSlabBlocks.MAGENTA,
                GlazedTerracottaSlabBlocks.LIGHT_BLUE,
                GlazedTerracottaSlabBlocks.YELLOW,
                GlazedTerracottaSlabBlocks.LIME,
                GlazedTerracottaSlabBlocks.PINK,
                GlazedTerracottaSlabBlocks.GRAY,
                GlazedTerracottaSlabBlocks.LIGHT_GRAY,
                GlazedTerracottaSlabBlocks.CYAN,
                GlazedTerracottaSlabBlocks.PURPLE,
                GlazedTerracottaSlabBlocks.BLUE,
                GlazedTerracottaSlabBlocks.BROWN,
                GlazedTerracottaSlabBlocks.GREEN,
                GlazedTerracottaSlabBlocks.RED,
                GlazedTerracottaSlabBlocks.BLACK
        );
    }
}

