package com.davigj.silkablooie.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SilkablooieConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Float> TNTDropChance;
        public final ForgeConfigSpec.ConfigValue<Float> advancedTNTDropChance;
        public final ForgeConfigSpec.ConfigValue<Boolean> snrCompat;
        public final ForgeConfigSpec.ConfigValue<Float> cobwebDropChance;
        public final ForgeConfigSpec.ConfigValue<Float> advancedCobwebDropChance;

        Common (ForgeConfigSpec.Builder builder) {
            builder.push("changes");
            TNTDropChance = builder.comment("TNT has this percent chance to drop from mobs tagged as TNT droppers").define("TNT drop chance", .35F);
            advancedTNTDropChance = builder.comment("TNT has this percent chance to drop from mobs tagged as advanced TNT droppers").define("Advanced TNT drop chance", 0.7F);
            snrCompat = builder.comment("If Savage and Ravage is installed and this is set to true, creepers drop Spore Bombs instead of TNT").define("SNR optional compat", false);
            cobwebDropChance = builder.comment("Cobwebs have this chance to drop from tagged mobs tagged as cobweb droppers").define("Cobweb drop chance", .35F);
            advancedCobwebDropChance = builder.comment("Cobwebs have this chance to drop from tagged mobs tagged as advanced cobweb droppers").define("Advanced cobweb drop chance", .7F);

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
