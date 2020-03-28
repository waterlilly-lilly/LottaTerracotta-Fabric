package org.villainy.lottaterracotta.tileEntities.renderers;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.model.SignModel;
import net.minecraft.item.DyeColor;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.villainy.lottaterracotta.blocks.TerracottaSignBlock;
import org.villainy.lottaterracotta.tileEntities.TerracottaSignTileEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.minecraft.state.properties.BlockStateProperties.*;

public class TerracottaSignTileEntityRenderer extends TileEntityRenderer<TerracottaSignTileEntity> {
    private static final Map<DyeColor, ResourceLocation> signTextures =
            Arrays.stream(DyeColor.values()).collect(Collectors.toMap(c -> c, c -> new ResourceLocation("textures/block/" + c.getTranslationKey() + "_terracotta.png")));

    private final SignModel model = new SignModel();

    public TerracottaSignTileEntityRenderer() {
        model.getSignStick().isHidden = true;
    }

    private ResourceLocation getSignTexture(Block block) {
        return signTextures.get(((TerracottaSignBlock) block).dyeColor);
    }

    @Override
    public void render(TerracottaSignTileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();

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

        GlStateManager.translatef((float) x + 0.5F, (float) y + 0.75F * 2.0f / 3.0f, (float) z + 0.5F);
        GlStateManager.rotatef(rotAroundY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(rotAroundX, 1.0F, 0.0F, 0.0F);
        GlStateManager.translatef(0.0F, -0.3125F, -0.4375F);

        if (destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(4.0F, 2.0F, 1.0F);
            GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
            this.bindTexture(getSignTexture(tileEntity.getBlockState().getBlock()));
        }

        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        GlStateManager.scalef(0.6666667F, -0.6666667F, -0.6666667F);
        this.model.renderSign();

        if (destroyStage < 0) {
            GlStateManager.enableBlend();
            GlStateManager.color4f(1, 1, 1, 1);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.bindTexture(getSignTexture(tileEntity.getBlockState().getBlock()));
            this.model.renderSign();
            GlStateManager.disableBlend();
        }

        GlStateManager.popMatrix();
        FontRenderer fontrenderer = this.getFontRenderer();
        float rot = 0.015625F * 0.6666667F;
        GlStateManager.translatef(0.0F, 0.5F * 0.6666667F, 0.07F * 0.6666667F);
        GlStateManager.scalef(rot, -rot, rot);
        GlStateManager.normal3f(0.0F, 0.0F, -1.0F * rot);
        GlStateManager.depthMask(false);

        if (destroyStage < 0) {
            for (int j = 0; j < tileEntity.signText.length; ++j) {
                if (tileEntity.signText[j] != null) {
                    ITextComponent ichatcomponent = tileEntity.signText[j];
                    List<ITextComponent> list = RenderComponentsUtil.splitText(ichatcomponent, 90, fontrenderer, false, true);
                    String s = list.size() > 0 ? list.get(0).getFormattedText() : "";

                    if (j == tileEntity.lineBeingEdited) {
                        s = "> " + s + " <";
                    }
                    fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - tileEntity.signText.length * 5, tileEntity.getTextColor().getColorValue());
                }
            }
        }

        GlStateManager.depthMask(true);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();

        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }
}