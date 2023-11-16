package com.davigj.silkablooie.core.data.server.tags;

import com.davigj.silkablooie.core.SilkablooieMod;
import com.davigj.silkablooie.core.other.SilkablooieConstants;
import com.davigj.silkablooie.core.other.tags.SilkablooieEntityTypeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SilkablooieEntityTypeTagsProvider extends EntityTypeTagsProvider {
    public SilkablooieEntityTypeTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SilkablooieMod.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags() {
        this.tag(SilkablooieEntityTypeTags.TNT_DROPPERS).addOptional(SilkablooieConstants.CREEPER)
                .addOptional(SilkablooieConstants.BADLANDS_CREEPER).addOptional(SilkablooieConstants.BEACH_CREEPER)
                .addOptional(SilkablooieConstants.DARK_OAK_CREEPER).addOptional(SilkablooieConstants.DESERT_CREEPER).addOptional(SilkablooieConstants.DRIPSTONE_CREEPER)
                .addOptional(SilkablooieConstants.HILLS_CREEPER).addOptional(SilkablooieConstants.JUNGLE_CREEPER).addOptional(SilkablooieConstants.SPRUCE_CREEPER)
                .addOptional(SilkablooieConstants.SWAMP_CREEPER);
        this.tag(SilkablooieEntityTypeTags.ADVANCED_TNT_DROPPERS).addOptional(SilkablooieConstants.CAVE_CREEPER).addOptional(SilkablooieConstants.DEEPER)
                .addOptional(SilkablooieConstants.PEEPER);
        this.tag(SilkablooieEntityTypeTags.COBWEB_DROPPERS).addOptional(SilkablooieConstants.SPIDER).addOptional(SilkablooieConstants.PLANTAIN_SPIDER);
        this.tag(SilkablooieEntityTypeTags.ADVANCED_COBWEB_DROPPERS).addOptional(SilkablooieConstants.CAVE_SPIDER);
    }
}
