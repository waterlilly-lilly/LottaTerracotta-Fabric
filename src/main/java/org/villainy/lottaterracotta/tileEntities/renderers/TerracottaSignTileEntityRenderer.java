package org.villainy.lottaterracotta.tileEntities.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer.SignModel;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import org.villainy.lottaterracotta.blocks.TerracottaSignBlock;
import org.villainy.lottaterracotta.tileEntities.TerracottaSignTileEntity;
import org.villainy.lottaterracotta.client.Atlases;

import java.util.List;


import static net.minecraft.state.properties.BlockStateProperties.*;

public class TerracottaSignTileEntityRenderer extends TileEntityRenderer<TerracottaSignTileEntity> {
    private final SignModel model = new SignModel();

    public TerracottaSignTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    private Material getSignMaterial(Block block) {
        TerracottaSignBlock signBlock = (TerracottaSignBlock) block;
        return Atlases.getTerracottaMaterial(signBlock.dyeColor);
    }

    @Override
    public void render(TerracottaSignTileEntity tileEntity, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        final float scale = 0.6666667F;
        matrixStackIn.push();

        BlockState state = tileEntity.getBlockState();

        Direction facing = state.get(HORIZONTAL_FACING);
        AttachFace face = state.get(FACE);

        int rotAroundY = 0;
        int rotAroundX = 0;

        switch (face) {
            case CEILING:
                rotAroundX = 90;
                break;
            case FLOOR:
                rotAroundX = -90;
                break;
        }

        switch (facing) {
            case SOUTH:
                break;
            case NORTH:
                rotAroundY = 180;
                break;
            case EAST:
                rotAroundY = 90;
                break;
            case WEST:
                rotAroundY = -90;
                break;
        }

        matrixStackIn.translate(0.5, 0.5, 0.5);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(rotAroundY));
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(rotAroundX));
        matrixStackIn.translate(0.0, -0.3125, -0.4375D);
        this.model.signStick.showModel = false;

        matrixStackIn.push();
        matrixStackIn.scale(scale, -scale, -scale);
        {
            Material material = SignTileEntityRenderer.getMaterial(state.getBlock());
            IVertexBuilder ivertexbuilder = material.getBuffer(bufferIn, RenderType::getEntityCutout);
            this.model.signBoard.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
            this.model.signStick.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        }
        {
            IVertexBuilder ivertexbuilder = getSignMaterial(tileEntity.getBlockState().getBlock()).getBuffer(bufferIn, RenderType::getEntityCutout);
            this.model.signBoard.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
            this.model.signStick.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        }
        matrixStackIn.pop();
        FontRenderer fontrenderer = this.renderDispatcher.getFontRenderer();
        matrixStackIn.translate(0.0D, (double) 0.33333334F, (double) 0.046666667F);
        matrixStackIn.scale(0.010416667F, -0.010416667F, 0.010416667F);

        int color = tileEntity.getTextColor().getTextColor();
        int red = (int) ((double) NativeImage.getRed(color) * 0.4D);
        int green = (int) ((double) NativeImage.getGreen(color) * 0.4D);
        int blue = (int) ((double) NativeImage.getBlue(color) * 0.4D);
        int adjustedColor = NativeImage.getCombined(0, blue, green, red);

        for (int j1 = 0; j1 < 4; ++j1)
        {
            String s = tileEntity.getRenderText(j1, (p_212491_1_) -> {
                List<ITextComponent> list = RenderComponentsUtil.splitText(p_212491_1_, 90, fontrenderer, false, true);
                return list.isEmpty() ? "" : list.get(0).getFormattedText();
            });
            if (s != null)
            {
                float f3 = (float) (-fontrenderer.getStringWidth(s) / 2);
                fontrenderer.renderString(s, f3, (float) (j1 * 10 - tileEntity.signText.length * 5), adjustedColor, false, matrixStackIn.getLast().getMatrix(), bufferIn, false, 0, combinedLightIn);
            }
        }

        matrixStackIn.pop();

    }
}