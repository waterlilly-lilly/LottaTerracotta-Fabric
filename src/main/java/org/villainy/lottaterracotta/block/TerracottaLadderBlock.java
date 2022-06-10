package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.helper.Shape;

public class TerracottaLadderBlock extends LadderBlock {

    protected static final Box LADDER_UNROTATED_AABB = Shape.getPixeledBox(1.75, 0, 0, 14.25, 16, 1.5);
    protected static final VoxelShape LADDER_SOUTH_AABB =  VoxelShapes.cuboid(Shape.getRotatedBox(LADDER_UNROTATED_AABB, Direction.SOUTH, false));
    protected static final VoxelShape LADDER_EAST_AABB  = VoxelShapes.cuboid(Shape.getRotatedBox(LADDER_UNROTATED_AABB, Direction.EAST, false));
    protected static final VoxelShape LADDER_WEST_AABB  = VoxelShapes.cuboid(Shape.getRotatedBox(LADDER_UNROTATED_AABB, Direction.WEST, false));
    protected static final VoxelShape LADDER_NORTH_AABB = VoxelShapes.cuboid(Shape.getRotatedBox(LADDER_UNROTATED_AABB, Direction.NORTH, false));

    private boolean isEnabled() {
        return LottaTerracottaConfig.enableLadders;
    }

    public TerracottaLadderBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.STONE).mapColor(color).hardness(0.4f).sounds(BlockSoundGroup.LADDER));
    }
    public TerracottaLadderBlock() {
        super(FabricBlockSettings.of(Material.STONE).mapColor(MapColor.ORANGE).hardness(0.4f).sounds(BlockSoundGroup.LADDER));
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> LADDER_NORTH_AABB;
            case SOUTH -> LADDER_SOUTH_AABB;
            case WEST -> LADDER_WEST_AABB;
            default -> LADDER_EAST_AABB;
        };
    }
}
