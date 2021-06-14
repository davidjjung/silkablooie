package com.davigj.silkablooie.core;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SilkablooieMod.MOD_ID)
public class SilkablooieMod {
    public static final String MOD_ID = "silkablooie";

    public SilkablooieMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SilkablooieConfig.COMMON_SPEC);

        MinecraftForge.EVENT_BUS.register(this);
    }
}