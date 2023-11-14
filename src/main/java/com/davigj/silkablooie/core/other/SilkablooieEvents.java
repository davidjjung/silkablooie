package com.davigj.silkablooie.core.other;

import com.davigj.silkablooie.core.SilkablooieConfig;
import com.davigj.silkablooie.core.SilkablooieMod;
import com.davigj.silkablooie.core.other.tags.SilkablooieEntityTypeTags;
import com.teamabnormals.savage_and_ravage.core.registry.SRBlocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = SilkablooieMod.MOD_ID)
public class SilkablooieEvents {

    @SubscribeEvent
    public static void onLivingDeath (LivingDeathEvent event) {
        LivingEntity victim = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        if (victim.getType().is(SilkablooieEntityTypeTags.TNT_DROPPERS) && attacker instanceof Player player
                && !event.getSource().isMagic() && !event.getSource().isProjectile() &&
                victim.getRandom().nextDouble() < SilkablooieConfig.COMMON.explosiveDropChance.get()) {
            ItemStack stack = player.getMainHandItem();
            if (stack.getItem() instanceof PickaxeItem && EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0) {
                ItemEntity tnt = new ItemEntity(victim.level, victim.getX(), victim.getY(), victim.getZ(), Items.TNT.getDefaultInstance());
                if (ModList.get().isLoaded("savage_and_ravage") && SilkablooieConfig.COMMON.snrCompat.get()) {
                    tnt = new ItemEntity(victim.level, victim.getX(), victim.getY(), victim.getZ(), SRBlocks.SPORE_BOMB.get().asItem().getDefaultInstance());
                }
                victim.level.addFreshEntity(tnt);
            }
        }
    }
}