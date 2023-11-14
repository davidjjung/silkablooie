package com.davigj.silkablooie.core.other.tags;

import com.davigj.silkablooie.core.SilkablooieMod;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class SilkablooieEntityTypeTags {
    public static final TagKey<EntityType<?>> TNT_DROPPERS = entityTypeTag("tnt_droppers");
    public static final TagKey<EntityType<?>> ADVANCED_TNT_DROPPERS = entityTypeTag("advanced_tnt_droppers");
    public static final TagKey<EntityType<?>> COBWEB_DROPPERS = entityTypeTag("cobweb_droppers");
    public static final TagKey<EntityType<?>> ADVANCED_COBWEB_DROPPERS = entityTypeTag("advanced_cobweb_droppers");
    private static TagKey<EntityType<?>> entityTypeTag(String name) {
        return TagUtil.entityTypeTag(SilkablooieMod.MOD_ID, name);
    }
}
