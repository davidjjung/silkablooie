package com.davigj.silkablooie.core.other;

import com.davigj.silkablooie.core.SilkablooieConfig;
import com.davigj.silkablooie.core.SilkablooieMod;
import com.davigj.silkablooie.core.other.tags.SilkablooieEntityTypeTags;
import com.teamabnormals.savage_and_ravage.core.registry.SRBlocks;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SilkablooieMod.MOD_ID)
public class SilkablooieEvents {

    @SubscribeEvent
    public static void onLivingDeath (LivingDeathEvent event) {
        LivingEntity victim = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        Player player;
        ItemStack stack;
        boolean isSilky = false;
        if (attacker instanceof Player) {
            player = (Player) attacker;
            stack = player.getMainHandItem();
            isSilky = isSilky(stack);
        }
        if (isRelevant(SilkablooieEntityTypeTags.TNT_DROPPERS, event.getSource(), victim, SilkablooieConfig.COMMON.TNTDropChance.get()) && isSilky) {
            ItemEntity tnt = new ItemEntity(victim.level(), victim.getX(), victim.getY(), victim.getZ(), Items.TNT.getDefaultInstance());
            if (ModList.get().isLoaded(SilkablooieConstants.SNR) && SilkablooieConfig.COMMON.snrCompat.get() && victim.getType().toString().equals("entity.minecraft.creeper")) {
                tnt = new ItemEntity(victim.level(), victim.getX(), victim.getY(), victim.getZ(), SRBlocks.SPORE_BOMB.get().asItem().getDefaultInstance());
            }
            victim.level().addFreshEntity(tnt);
        }
        if (isRelevant(SilkablooieEntityTypeTags.ADVANCED_TNT_DROPPERS, event.getSource(), victim, SilkablooieConfig.COMMON.advancedTNTDropChance.get()) && isSilky) {
            ItemEntity tnt = new ItemEntity(victim.level(), victim.getX(), victim.getY(), victim.getZ(), Items.TNT.getDefaultInstance());
            if (ModList.get().isLoaded(SilkablooieConstants.SNR) && SilkablooieConfig.COMMON.snrCompat.get() && victim.getType().toString().equals("entity.minecraft.creeper")) {
                tnt = new ItemEntity(victim.level(), victim.getX(), victim.getY(), victim.getZ(), SRBlocks.SPORE_BOMB.get().asItem().getDefaultInstance());
            }
            victim.level().addFreshEntity(tnt);
        }
        if (isRelevant(SilkablooieEntityTypeTags.COBWEB_DROPPERS, event.getSource(), victim, SilkablooieConfig.COMMON.cobwebDropChance.get()) && isSilky) {
            ItemEntity cobweb = new ItemEntity(victim.level(), victim.getX(), victim.getY(), victim.getZ(), Items.COBWEB.getDefaultInstance());
            victim.level().addFreshEntity(cobweb);
        }
        if (isRelevant(SilkablooieEntityTypeTags.ADVANCED_COBWEB_DROPPERS, event.getSource(), victim, SilkablooieConfig.COMMON.advancedCobwebDropChance.get()) && isSilky) {
            ItemEntity cobweb = new ItemEntity(victim.level(), victim.getX(), victim.getY(), victim.getZ(), Items.COBWEB.getDefaultInstance());
            victim.level().addFreshEntity(cobweb);
        }
    }

    public static boolean isRelevant(TagKey<EntityType<?>> tag, DamageSource source, LivingEntity victim, Float dropChance) {
        return victim.getType().is(tag) && !source.isIndirect() && victim.getRandom().nextDouble() < dropChance;
    }

    public static boolean isSilky(ItemStack stack) {
        return stack.getItem() instanceof PickaxeItem && stack.getEnchantmentLevel(Enchantments.SILK_TOUCH) > 0;
    }
}