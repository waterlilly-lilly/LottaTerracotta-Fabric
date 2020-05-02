package org.villainy.lottaterracotta.config;

import net.minecraftforge.fml.config.ModConfig;


public final class ConfigHelper {

    private static ModConfig commonConfig;

    public static void bakeCommon(final ModConfig config) {
        commonConfig = config;

        LottaTerracottaConfig.enableSlabs = ConfigHolder.COMMON.enableSlabs.get();
        LottaTerracottaConfig.enableStairs = ConfigHolder.COMMON.enableStairs.get();
        LottaTerracottaConfig.enableWalls = ConfigHolder.COMMON.enableWalls.get();
        LottaTerracottaConfig.enableButtons = ConfigHolder.COMMON.enableButtons.get();
        LottaTerracottaConfig.enablePressurePlates = ConfigHolder.COMMON.enablePressurePlates.get();
        LottaTerracottaConfig.enableFences = ConfigHolder.COMMON.enableFences.get();
        LottaTerracottaConfig.enableLadders = ConfigHolder.COMMON.enableLadders.get();
        LottaTerracottaConfig.enableSigns = ConfigHolder.COMMON.enableSigns.get();
        LottaTerracottaConfig.enableLevers = ConfigHolder.COMMON.enableLevers.get();
        LottaTerracottaConfig.enableVerticalSlabs = ConfigHolder.COMMON.enableVerticalSlabs.get();
        LottaTerracottaConfig.enableTiles = ConfigHolder.COMMON.enableTiles.get();
    }

    public static void setValueAndSave(final ModConfig modConfig, final String path, final Object newValue) {
        modConfig.getConfigData().set(path, newValue);
        modConfig.save();
    }

}
