package org.villainy.lottaterracotta.config;

import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;


public class FlagRecipeCondition implements ICondition {

    private final String flag;
    private final ResourceLocation loc;

    public FlagRecipeCondition(String flag, ResourceLocation loc) {
        this.flag = flag;
        this.loc = loc;
    }


    @Override
    public ResourceLocation getID() {
        return loc;
    }

    @Override
    public boolean test() {
        if (flag.equals("slabs")) {
            return LottaTerracottaConfig.enableSlabs;
        }
        if (flag.equals("stairs")) {
            return LottaTerracottaConfig.enableStairs;
        }
        if (flag.equals("walls")) {
            return LottaTerracottaConfig.enableWalls;
        }
        if (flag.equals("buttons")) {
            return LottaTerracottaConfig.enableButtons;
        }
        if (flag.equals("pressure_plates")) {
            return LottaTerracottaConfig.enablePressurePlates;
        }
        if (flag.equals("fences")) {
            return LottaTerracottaConfig.enableFences;
        }
        if (flag.equals("ladders")) {
            return LottaTerracottaConfig.enableLadders;
        }
        if (flag.equals("cake")) {
            return LottaTerracottaConfig.enableCake;
        }
        if (flag.equals("signs")) {
            return LottaTerracottaConfig.enableSigns;
        }
        if (flag.equals("levers")) {
            return LottaTerracottaConfig.enableLevers;
        }
        return true;
    }

    public static class Serializer implements IConditionSerializer<FlagRecipeCondition> {
        private final ResourceLocation location;

        public Serializer(ResourceLocation location) {
            this.location = location;
        }

        @Override
        public void write(JsonObject json, FlagRecipeCondition value) {
            json.addProperty("flag", value.flag);
        }

        @Override
        public FlagRecipeCondition read(JsonObject json) {
            return new FlagRecipeCondition(json.getAsJsonPrimitive("flag").getAsString(), location);
        }

        @Override
        public ResourceLocation getID() {
            return location;
        }
    }
}

