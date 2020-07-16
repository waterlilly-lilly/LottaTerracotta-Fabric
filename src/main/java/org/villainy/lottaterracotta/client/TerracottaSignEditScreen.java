package org.villainy.lottaterracotta.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.gui.screen.EditSignScreen;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.vector.Matrix4f;
import org.villainy.lottaterracotta.blocks.TerracottaSignBlock;
import org.villainy.lottaterracotta.tileEntities.TerracottaSignTileEntity;

public class TerracottaSignEditScreen extends EditSignScreen {
    private TerracottaSignTileEntity tileSign;

    public TerracottaSignEditScreen(TerracottaSignTileEntity teSign) {
        super(teSign);
        this.tileSign = teSign;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        RenderHelper.setupGuiFlatDiffuseLighting();

        this.renderBackground(matrixStack);

        this.drawCenteredString(matrixStack,
                this.font,
                this.title,
                this.width / 2,
                40,
                16777215);

        MatrixStack matrixstack = new MatrixStack();
        matrixstack.push();
        matrixstack.translate((double)(this.width / 2), 0.0D, 50.0D);

        matrixstack.scale(93.75F, -93.75F, 93.75F);
        matrixstack.translate(0.0D, -1.3125D, 0.0D);

        TerracottaSignBlock block = (TerracottaSignBlock)this.tileSign.getBlockState().getBlock();

        boolean shouldUpdate = this.updateCounter / 6 % 2 == 0;

        matrixstack.push();
        matrixstack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        IRenderTypeBuffer.Impl renderTypeBuffer = this.minecraft.getRenderTypeBuffers().getBufferSource();
        RenderMaterial material = Atlases.getTerracottaMaterial(block.dyeColor);
        IVertexBuilder ivertexbuilder = material.getBuffer(renderTypeBuffer, this.field_228191_a_::getRenderType);
        this.field_228191_a_.signBoard.render(matrixstack, ivertexbuilder, 15728880, OverlayTexture.NO_OVERLAY);

        matrixstack.pop();
        matrixstack.translate(0.0D, (double)0.33333334F, (double)0.046666667F);
        matrixstack.scale(0.010416667F, -0.010416667F, 0.010416667F);

        int textColor = this.tileSign.getTextColor().getTextColor();

        Matrix4f matrix4f = matrixstack.getLast().getMatrix();
        int selectionPoint1 = this.textInputUtil.func_216896_c();
        int selectionPoint2 = this.textInputUtil.func_216898_d();
        int direction = this.minecraft.fontRenderer.getBidiFlag() ? -1 : 1;
        int j1 = this.editLine * 10 - this.field_238846_r_.length * 5;

        for(int lineNumber = 0; lineNumber < field_238846_r_.length; ++lineNumber) {
            String line = field_238846_r_[lineNumber];
            if (line != null) {
                float center = (float)(-this.minecraft.fontRenderer.getStringWidth(line) / 2);
                this.minecraft.fontRenderer.renderString(line, center, (float)(lineNumber * 10 - this.field_238846_r_.length * 5), textColor, false, matrix4f, renderTypeBuffer, false, 0, 15728880);
                if (lineNumber == this.editLine && selectionPoint1 >= 0 && shouldUpdate) {
                    int l1 = this.minecraft.fontRenderer.getStringWidth(line.substring(0, Math.max(Math.min(selectionPoint1, line.length()), 0)));
                    int i2 = (l1 - this.minecraft.fontRenderer.getStringWidth(line) / 2) * direction;
                    if (selectionPoint1 >= line.length()) {
                        this.minecraft.fontRenderer.renderString("_", (float)i2, (float)j1, textColor, false, matrix4f, renderTypeBuffer, false, 0, 15728880);
                    }
                }
            }
        }

        renderTypeBuffer.finish();

        for(int lineNumber = 0; lineNumber < field_238846_r_.length; ++lineNumber) {
            String line = field_238846_r_[lineNumber];
            if (line != null && lineNumber == this.editLine && selectionPoint1 >= 0) {
                int l3 = this.minecraft.fontRenderer.getStringWidth(line.substring(0, Math.max(Math.min(selectionPoint1, line.length()), 0)));
                int i4 = (l3 - this.minecraft.fontRenderer.getStringWidth(line) / 2) * direction;
                if (shouldUpdate && selectionPoint1 < line.length()) {
                    fill(matrixStack, i4, j1 - 1, i4 + 1, j1 + 9, -16777216 | textColor);
                }

                if (selectionPoint2 != selectionPoint1) {
                    int minSelectionPoint = Math.min(selectionPoint1, selectionPoint2);
                    int maxSelectionPoint = Math.max(selectionPoint1, selectionPoint2);
                    int k2 = (this.minecraft.fontRenderer.getStringWidth(line.substring(0, minSelectionPoint)) - this.minecraft.fontRenderer.getStringWidth(line) / 2) * direction;
                    int l2 = (this.minecraft.fontRenderer.getStringWidth(line.substring(0, maxSelectionPoint)) - this.minecraft.fontRenderer.getStringWidth(line) / 2) * direction;
                    int i3 = Math.min(k2, l2);
                    int j3 = Math.max(k2, l2);
                    Tessellator tessellator = Tessellator.getInstance();
                    BufferBuilder bufferbuilder = tessellator.getBuffer();
                    RenderSystem.disableTexture();
                    RenderSystem.enableColorLogicOp();
                    RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
                    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
                    bufferbuilder.pos(matrix4f, (float)i3, (float)(j1 + 9), 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.pos(matrix4f, (float)j3, (float)(j1 + 9), 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.pos(matrix4f, (float)j3, (float)j1, 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.pos(matrix4f, (float)i3, (float)j1, 0.0F).color(0, 0, 255, 255).endVertex();
                    bufferbuilder.finishDrawing();
                    WorldVertexBufferUploader.draw(bufferbuilder);
                    RenderSystem.disableColorLogicOp();
                    RenderSystem.enableTexture();
                }
            }
        }

        matrixstack.pop();
        RenderHelper.setupGui3DDiffuseLighting();

        for (int i = 0; i < this.buttons.size(); ++i) {
            this.buttons.get(i).render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
}

