package org.villainy.lottaterracotta.client;

import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.EnumMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class Atlases {
    private static final Map<DyeColor, RenderMaterial> TERRACOTTA_MATERIALS;
    private static final RenderMaterial PLAIN_TERRACOTTA_MATERIAL;

    static {
        TERRACOTTA_MATERIALS = makeTerracottaMaterials();
        PLAIN_TERRACOTTA_MATERIAL = new RenderMaterial(net.minecraft.client.renderer.Atlases.SIGN_ATLAS,
                new ResourceLocation("block/terracotta"));
    }

    private Atlases() {
    }

    private static Map<DyeColor, RenderMaterial> makeTerracottaMaterials() {
        final EnumMap<DyeColor, RenderMaterial> materials = new EnumMap<>(DyeColor.class);
        for (final DyeColor dyeColor : DyeColor.values()) {
            materials.put(dyeColor, new RenderMaterial(net.minecraft.client.renderer.Atlases.SIGN_ATLAS,
                    new ResourceLocation("block/" + dyeColor.getTranslationKey() + "_terracotta")));
        }
        return materials;
    }

    public static RenderMaterial getTerracottaMaterial(final DyeColor dyeColor) {
        if (dyeColor == null) {
            return PLAIN_TERRACOTTA_MATERIAL;
        }
        return TERRACOTTA_MATERIALS.get(dyeColor);
    }

    @SubscribeEvent
    public static void onTextureStitchPre(final TextureStitchEvent.Pre event) {
        final ResourceLocation atlas = event.getMap().getTextureLocation();
        TERRACOTTA_MATERIALS.values().stream()
                .filter(material -> material.getAtlasLocation().equals(atlas))
                .forEach(material -> event.addSprite(material.getTextureLocation()));

        event.addSprite(PLAIN_TERRACOTTA_MATERIAL.getTextureLocation());

    }
}