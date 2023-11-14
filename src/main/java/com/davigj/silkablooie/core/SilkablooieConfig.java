package com.davigj.silkablooie.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SilkablooieConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Float> explosiveDropChance;
        public final ForgeConfigSpec.ConfigValue<Boolean> snrCompat;

        Common (ForgeConfigSpec.Builder builder) {
            builder.push("changes");
            explosiveDropChance = builder.comment("TNT has this percent chance to drop").define("Explosive drop chance", 1.0F);
            snrCompat = builder.comment("If Savage and Ravage is installed, creepers drop Spore Bombs instead of TNT").define("SNR optional compat", true);
            builder.pop();
        }
    }

    static final ForgeConfigSpec COMMON_SPEC;
    public static final SilkablooieConfig.Common COMMON;


    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(SilkablooieConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
