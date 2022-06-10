package org.villainy.lottaterracotta.block.sign;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.moddingplayground.frame.api.rendering.v0.SignTextureProvider;
import org.villainy.lottaterracotta.mixin.SignTypeInvoker;

public class TerracottaSignType extends SignType implements SignTextureProvider {
    private final Identifier id;
    public TerracottaSignType(Identifier id) {
        super(id.toString());
        this.id = id;
    }
    public Identifier getId() {
        return id;
    }
    @Override
    public Identifier getTexture(SignType type) {
        Identifier id = ((TerracottaSignType) type).getId();
        return new Identifier(id.getNamespace(), "entity/signs/%s".formatted(id.getPath()));
    }
    public static SignType register(String id) {
        return SignTypeInvoker.invokeRegister(new TerracottaSignType(new Identifier("lottaterracotta", id)));
    }

}
