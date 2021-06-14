package com.davigj.silkablooie.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SilkablooieConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final SilkablooieConfig.Common COMMON;

    static {
        Pair<SilkablooieConfig.Common, ForgeConfigSpec> commonSpecPair = (new ForgeConfigSpec.Builder()).configure(SilkablooieConfig.Common::new);
        COMMON_SPEC = (ForgeConfigSpec)commonSpecPair.getRight();
        COMMON = (SilkablooieConfig.Common)commonSpecPair.getLeft();
    }

    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> creepersDropSporeBombsNotTNT;
        public final ForgeConfigSpec.ConfigValue<Boolean> sporiSilkBoolean;
        public final ForgeConfigSpec.ConfigValue<Double> silkLootChance;
        public final ForgeConfigSpec.ConfigValue<Double> deeperLootChance;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.comment("entities").push("creepers");
            this.creepersDropSporeBombsNotTNT = builder.comment("While Savage and Ravage is loaded, creepers will drop spore bombs instead of TNT").define("creepersDropSporeBombsNotTNT", false);
            this.sporiSilkBoolean = builder.comment("While Nether Extension is loaded, exploding spori have a chance to cause killed creepers to drop their silk loot.").define("sporiSilkBoolean", true);
            this.deeperLootChance = builder.comment("This is the chance that deepers from Caverns and Chasms drop TNT when killed with a silk touch tool; default 60%.").define("deeperLootChance", 0.6);
            this.silkLootChance = builder.comment("This is the chance that creepers drop TNT or spore bombs when killed with a silk touch tool; default 60%.").define("silkLootChance", 0.6);
            builder.pop();
        }
    }
}
