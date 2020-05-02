package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarpetBlock;
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
import org.villainy.lottaterracotta.objectholders.GlazedTerracottaTileBlocks;

import java.util.stream.Stream;

public class GlazedTerracottaTileBlock extends CarpetBlock {
    private boolean isEnabled() {
        return LottaTerracottaConfig.enableTiles;
    }
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

    public GlazedTerracottaTileBlock(DyeColor dyeColor) {
        super(dyeColor, Block.Properties.create(Material.CARPET, dyeColor).hardnessAndResistance(0.1F));
        setRegistryName(dyeColor.getName() + "_glazed_terracotta_tile");
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
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
                GlazedTerracottaTileBlocks.WHITE,
                GlazedTerracottaTileBlocks.ORANGE,
                GlazedTerracottaTileBlocks.MAGENTA,
                GlazedTerracottaTileBlocks.LIGHT_BLUE,
                GlazedTerracottaTileBlocks.YELLOW,
                GlazedTerracottaTileBlocks.LIME,
                GlazedTerracottaTileBlocks.PINK,
                GlazedTerracottaTileBlocks.GRAY,
                GlazedTerracottaTileBlocks.LIGHT_GRAY,
                GlazedTerracottaTileBlocks.CYAN,
                GlazedTerracottaTileBlocks.PURPLE,
                GlazedTerracottaTileBlocks.BLUE,
                GlazedTerracottaTileBlocks.BROWN,
                GlazedTerracottaTileBlocks.GREEN,
                GlazedTerracottaTileBlocks.RED,
                GlazedTerracottaTileBlocks.BLACK
        );
    }
}
