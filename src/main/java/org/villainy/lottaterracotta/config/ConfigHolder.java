package org.villainy.lottaterracotta.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * This holds the Client & Server Configs and the Client & Server ConfigSpecs.
 * It can be merged into the main ExampleModConfig class, but is separate because of personal preference and to keep the code organised
 *
 * @author Cadiboo
 */
public final class ConfigHolder {

    public static final ForgeConfigSpec COMMON_SPEC;
    static final CommonConfig COMMON;

    static {
        {
            final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
            COMMON = specPair.getLeft();
            COMMON_SPEC = specPair.getRight();
        }
    }
}
