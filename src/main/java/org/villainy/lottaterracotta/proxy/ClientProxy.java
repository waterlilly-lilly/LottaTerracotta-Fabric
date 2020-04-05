package org.villainy.lottaterracotta.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.villainy.lottaterracotta.blocks.TerracottaLadderBlock;
import org.villainy.lottaterracotta.client.TerracottaSignEditScreen;
import org.villainy.lottaterracotta.tileEntities.TerracottaSignTileEntity;
import org.villainy.lottaterracotta.tileEntities.renderers.TerracottaSignTileEntityRenderer;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ClientRegistry.bindTileEntityRenderer(TerracottaSignTileEntity.TYPE, TerracottaSignTileEntityRenderer::new);
    }

    @Override
    public void onFMLClientSetup(FMLClientSetupEvent event) {
        setRenderTypes(event);
    }

    @Override
    public void onFMLCommonSetup(FMLCommonSetupEvent event) { }

    @Override
    public void openSignButtonGui(BlockPos pos)
    {
        Minecraft mc = Minecraft.getInstance();
        mc.execute(() -> {
            World world = mc.world;
            TileEntity te = world.getTileEntity(pos);
            if (te instanceof TerracottaSignTileEntity) {
                mc.displayGuiScreen(new TerracottaSignEditScreen((TerracottaSignTileEntity) te));
            }
        });
    }

    public void setRenderTypes(final FMLClientSetupEvent event)
    {
        TerracottaLadderBlock.allBlocks().forEach (block -> {
            RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
        });
    }
}
