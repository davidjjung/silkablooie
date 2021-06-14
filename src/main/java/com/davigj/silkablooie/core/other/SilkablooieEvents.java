package com.davigj.silkablooie.core.other;

import com.davigj.silkablooie.core.SilkablooieConfig;
import com.davigj.silkablooie.core.SilkablooieMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = SilkablooieMod.MOD_ID)
public class SilkablooieEvents {
    public static final ResourceLocation DEEPER = new ResourceLocation("caverns_and_chasms", "deeper");
    public static final ResourceLocation SPORUS = new ResourceLocation("nether_extension", "sporus");

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        boolean creeper = event.getEntity().getType() == (EntityType.CREEPER);
        boolean deeper = event.getEntity().getType() == ForgeRegistries.ENTITIES.getValue(DEEPER);
        if (creeper || deeper) {
            Entity cause = event.getSource().getTrueSource();
            if (cause instanceof PlayerEntity) {
                Hand hand = ((PlayerEntity) cause).swingingHand;
                Item item = ((PlayerEntity) cause).getHeldItem(hand).getItem();
                double chance = 0.6;
                if (creeper) {
                    chance = SilkablooieConfig.COMMON.silkLootChance.get();
                } else if (deeper) {
                    chance = SilkablooieConfig.COMMON.deeperLootChance.get();
                }
                if (Math.random() < chance && item instanceof PickaxeItem && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, ((PlayerEntity) cause).getHeldItemMainhand()) > 0) {
                    LivingEntity victim = (LivingEntity) event.getEntity();
                    LootTable loottable = victim.world.getServer().getLootTableManager().getLootTableFromLocation(SilkablooieLootResources.SILKABLOOIE_DROPS);
                    if (creeper && ModList.get().isLoaded("savageandravage") && SilkablooieConfig.COMMON.creepersDropSporeBombsNotTNT.get()) {
                        loottable = victim.world.getServer().getLootTableManager().getLootTableFromLocation(SilkablooieLootResources.SAVAGEBLOOIE_DROPS);
                    }
                    LootContext context = (new LootContext.Builder((ServerWorld)victim.world)).withParameter(LootParameters.THIS_ENTITY, victim).withRandom(victim.world.rand).build(LootParameterSets.field_237453_h_);
                    loottable.generate(context).forEach(victim::entityDropItem);
                }
            } else{
                if (event.getSource().isExplosion() && event.getSource().getImmediateSource().getType() == ForgeRegistries.ENTITIES.getValue(SPORUS)) {
                    double chance2 = 0.6;
                    if (creeper) {
                        chance2 = SilkablooieConfig.COMMON.silkLootChance.get();
                    } else if (deeper) {
                        chance2 = SilkablooieConfig.COMMON.deeperLootChance.get();
                    }
                    if (Math.random() < chance2) {
                        LivingEntity victim2 = (LivingEntity) event.getEntity();
                        LootTable loottable2 = victim2.world.getServer().getLootTableManager().getLootTableFromLocation(SilkablooieLootResources.SILKABLOOIE_DROPS);
                        if (creeper && ModList.get().isLoaded("savageandravage") && SilkablooieConfig.COMMON.creepersDropSporeBombsNotTNT.get()) {
                            loottable2 = victim2.world.getServer().getLootTableManager().getLootTableFromLocation(SilkablooieLootResources.SAVAGEBLOOIE_DROPS);
                        }
                        LootContext context = (new LootContext.Builder((ServerWorld)victim2.world)).withParameter(LootParameters.THIS_ENTITY, victim2).withRandom(victim2.world.rand).build(LootParameterSets.field_237453_h_);
                        loottable2.generate(context).forEach(victim2::entityDropItem);
                    }
                }
            }
        }
    }
}
