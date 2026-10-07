package com.grasshopper.survivor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.grasshopper.survivor.init.InitEntities;
import com.grasshopper.survivor.init.InitSounds;
import com.grasshopper.survivor.init.InitStands;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SurvivorMain.MOD_ID)
public class SurvivorMain {
    public static final String MOD_ID = "survivor";
    public static final Logger LOGGER = LogManager.getLogger();

    public SurvivorMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InitEntities.ENTITIES.register(modEventBus);
        InitSounds.SOUNDS.register(modEventBus);
        InitStands.ACTIONS.register(modEventBus);
        InitStands.STANDS.register(modEventBus);
    }
}