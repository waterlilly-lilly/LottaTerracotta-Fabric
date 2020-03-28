package org.villainy.lottaterracotta;

import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.villainy.lottaterracotta.blocks.*;
import org.villainy.lottaterracotta.config.ConfigHelper;
import org.villainy.lottaterracotta.config.ConfigHolder;
import org.villainy.lottaterracotta.config.FlagRecipeCondition;
import org.villainy.lottaterracotta.items.TerracottaSignItem;
import org.villainy.lottaterracotta.items.helper.BlockItemHelper;
import org.villainy.lottaterracotta.network.OpenTerracottaSignEditor;
import org.villainy.lottaterracotta.objectholders.TerracottaSignBlocks;
import org.villainy.lottaterracotta.proxy.ClientProxy;
import org.villainy.lottaterracotta.proxy.IProxy;
import org.villainy.lottaterracotta.proxy.CommonProxy;
import org.villainy.lottaterracotta.tileEntities.TerracottaSignTileEntity;

import java.util.stream.Stream;

@Mod("lottaterracotta")
public class LottaTerracotta
{
    public static final String MODID = "lottaterracotta";

    public static final String CHANNEL = MODID;
    private static final String PROTOCOL_VERSION = "1.0";
    public static SimpleChannel channel = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(MODID, CHANNEL))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    private static final Logger LOG = LogManager.getLogger();

    public LottaTerracotta() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();

        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC);

        CraftingHelper.register(new FlagRecipeCondition.Serializer(new ResourceLocation(LottaTerracotta.MODID, "flag")));

        FMLJavaModLoadingContext.get().getModEventBus().addListener(proxy::onFMLClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(proxy::onFMLCommonSetup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        int messageNumber = 0;
        channel.registerMessage(messageNumber++, OpenTerracottaSignEditor.class, OpenTerracottaSignEditor::encode, OpenTerracottaSignEditor::new, OpenTerracottaSignEditor::handle);
    }

    private void loadComplete(final FMLLoadCompleteEvent event)
    {
        proxy.init();
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> blockRegistry = event.getRegistry();

            Stream.of(DyeColor.values()).forEach(dyeColor -> {
                TerracottaSlabBlock slab = new TerracottaSlabBlock(dyeColor);

                blockRegistry.register(new TerracottaSlabBlock(dyeColor));
                blockRegistry.register(new TerracottaStairsBlock(dyeColor, slab.getDefaultState()));
                blockRegistry.register(new TerracottaWallBlock(dyeColor));
                blockRegistry.register(new TerracottaButtonBlock(dyeColor));
                blockRegistry.register(new TerracottaPressurePlateBlock(dyeColor));
                blockRegistry.register(new TerracottaFenceBlock(dyeColor));
                blockRegistry.register(new TerracottaFenceGateBlock(dyeColor));
                blockRegistry.register(new TerracottaLadderBlock(dyeColor));
                blockRegistry.register(new TerracottaSignBlock(dyeColor));
                blockRegistry.register(new TerracottaLeverBlock(dyeColor));
            });

            // Register uncolored versions
            TerracottaSlabBlock slab = new TerracottaSlabBlock();
            blockRegistry.register(new TerracottaSlabBlock());
            blockRegistry.register(new TerracottaStairsBlock(slab.getDefaultState()));
            blockRegistry.register(new TerracottaWallBlock());
            blockRegistry.register(new TerracottaButtonBlock());
            blockRegistry.register(new TerracottaPressurePlateBlock());
            blockRegistry.register(new TerracottaFenceBlock());
            blockRegistry.register(new TerracottaFenceGateBlock());
            blockRegistry.register(new TerracottaLadderBlock());
            blockRegistry.register(new TerracottaSignBlock());
            blockRegistry.register(new TerracottaLeverBlock());
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> itemRegistry = event.getRegistry();

            TerracottaSlabBlock.allBlocks().forEach (block ->
                    itemRegistry.register(BlockItemHelper.createBasicBlockItem(block, ItemGroup.BUILDING_BLOCKS))
            );
            TerracottaStairsBlock.allBlocks().forEach (block ->
                    itemRegistry.register(BlockItemHelper.createBasicBlockItem(block, ItemGroup.BUILDING_BLOCKS))
            );
            TerracottaWallBlock.allBlocks().forEach (block ->
                    itemRegistry.register(BlockItemHelper.createBasicBlockItem(block, ItemGroup.DECORATIONS))
            );
            TerracottaButtonBlock.allBlocks().forEach (block ->
                    itemRegistry.register(BlockItemHelper.createBasicBlockItem(block, ItemGroup.REDSTONE))
            );
            TerracottaPressurePlateBlock.allBlocks().forEach (block ->
                    itemRegistry.register(BlockItemHelper.createBasicBlockItem(block, ItemGroup.REDSTONE))
            );
            TerracottaFenceBlock.allBlocks().forEach (block ->
                    itemRegistry.register(BlockItemHelper.createBasicBlockItem(block, ItemGroup.DECORATIONS))
            );
            TerracottaFenceGateBlock.allBlocks().forEach (block ->
                    itemRegistry.register(BlockItemHelper.createBasicBlockItem(block, ItemGroup.DECORATIONS))
            );
            TerracottaLadderBlock.allBlocks().forEach (block ->
                    itemRegistry.register(BlockItemHelper.createBasicBlockItem(block, ItemGroup.DECORATIONS))
            );
            TerracottaSignBlock.allBlocks().forEach (block ->
                    itemRegistry.register(new TerracottaSignItem(block))
			);
            TerracottaLeverBlock.allBlocks().forEach (block ->
                    itemRegistry.register(BlockItemHelper.createBasicBlockItem(block, ItemGroup.REDSTONE))
            );
        }

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            TileEntityType<?> terracottaSignType = TileEntityType.Builder.create(TerracottaSignTileEntity::new,
                    TerracottaSignBlocks.WHITE,
                    TerracottaSignBlocks.ORANGE,
                    TerracottaSignBlocks.MAGENTA,
                    TerracottaSignBlocks.LIGHT_BLUE,
                    TerracottaSignBlocks.YELLOW,
                    TerracottaSignBlocks.LIME,
                    TerracottaSignBlocks.PINK,
                    TerracottaSignBlocks.GRAY,
                    TerracottaSignBlocks.LIGHT_GRAY,
                    TerracottaSignBlocks.CYAN,
                    TerracottaSignBlocks.PURPLE,
                    TerracottaSignBlocks.BLUE,
                    TerracottaSignBlocks.BROWN,
                    TerracottaSignBlocks.GREEN,
                    TerracottaSignBlocks.RED,
                    TerracottaSignBlocks.BLACK
            ).build(null);
            terracottaSignType.setRegistryName(MODID, "terracotta_sign");
            event.getRegistry().register(terracottaSignType);
        }

        @SubscribeEvent
        public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
            final ModConfig config = event.getConfig();
            if (config.getSpec() == ConfigHolder.COMMON_SPEC) {
                ConfigHelper.bakeCommon(config);
            }
        }
    }
}
