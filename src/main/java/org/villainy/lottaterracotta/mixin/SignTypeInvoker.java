package org.villainy.lottaterracotta.mixin;

import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
/*
Copyright (c) 2022 Modding Playground under the license at https://github.com/moddingplayground/twigs-fabric/blob/1.18.2/LICENSE
 */
@Mixin(SignType.class)
public interface SignTypeInvoker {
    @Invoker static SignType invokeRegister(SignType type) { throw new AssertionError(); }
}