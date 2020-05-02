package org.villainy.lottaterracotta.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.villainy.lottaterracotta.LottaTerracotta;

final class CommonConfig {

    final ForgeConfigSpec.BooleanValue enableSlabs;
    final ForgeConfigSpec.BooleanValue enableStairs;
    final ForgeConfigSpec.BooleanValue enableWalls;
    final ForgeConfigSpec.BooleanValue enableButtons;
    final ForgeConfigSpec.BooleanValue enablePressurePlates;
    final ForgeConfigSpec.BooleanValue enableFences;
    final ForgeConfigSpec.BooleanValue enableLadders;
    final ForgeConfigSpec.BooleanValue enableSigns;
    final ForgeConfigSpec.BooleanValue enableLevers;
    final ForgeConfigSpec.BooleanValue enableVerticalSlabs;
    final ForgeConfigSpec.BooleanValue enableTiles;

    CommonConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("general");
        enableSlabs = builder
                .comment("Enable terracotta slabs")
                .translation(LottaTerracotta.MODID + ".config.enableSlabs")
                .define("enableSlabs", true);
        enableStairs = builder
                .comment("Enable terracotta stairs")
                .translation(LottaTerracotta.MODID + ".config.enableStairs")
                .define("enableStairs", true);
        enableWalls = builder
                .comment("Enable terracotta walls")
                .translation(LottaTerracotta.MODID + ".config.enableWalls")
                .define("enableWalls", true);
        enableButtons = builder
                .comment("Enable terracotta buttons")
                .translation(LottaTerracotta.MODID + ".config.enableButtons")
                .define("enableButtons", true);
        enablePressurePlates = builder
                .comment("Enable terracotta pressure plates")
                .translation(LottaTerracotta.MODID + ".config.enablePressurePlates")
                .define("enablePressurePlates", true);
        enableFences = builder
                .comment("Enable terracotta fences")
                .translation(LottaTerracotta.MODID + ".config.enableFences")
                .define("enableFences", true);
        enableLadders = builder
                .comment("Enable terracotta ladders")
                .translation(LottaTerracotta.MODID + ".config.enableLadders")
                .define("enableLadders", true);
        enableSigns = builder
                .comment("Enable terracotta signs")
                .translation(LottaTerracotta.MODID + ".config.enableSigns")
                .define("enableSigns", true);
        enableLevers = builder
                .comment("Enable terracotta levers")
                .translation(LottaTerracotta.MODID + ".config.enableLevers")
                .define("enableLevers", true);
        enableVerticalSlabs = builder
                .comment("Enable terracotta vertical slabs")
                .translation(LottaTerracotta.MODID + ".config.enableVerticalSlabs")
                .define("enableVerticalSlabs", true);
        enableTiles = builder
                .comment("Enable terracotta tiles")
                .translation(LottaTerracotta.MODID + ".config.enableTiles")
                .define("enableTiles", true);
        builder.pop();
    }

}