package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.DyeColor;

public class GlazedTerracottaTileBlock extends CarpetBlock {

    public static final DirectionProperty HORIZONTAL_FACING = Properties.HORIZONTAL_FACING;

    public GlazedTerracottaTileBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.CARPET).mapColor(color).hardness(0.1f));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HORIZONTAL_FACING);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }
    public BlockState rotate(BlockState state, BlockRotation rot) {
        return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
    }
    public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.get(HORIZONTAL_FACING)));
    }
}
