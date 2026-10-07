package com.grasshopper.survivor.event;

import com.grasshopper.survivor.SurvivorMain;
import com.grasshopper.survivor.util.AngerHelper;
import com.grasshopper.survivor.util.WetHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SurvivorMain.MOD_ID)
public class SurvivorEventHandler {

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity.level.isClientSide()) return;

        if (WetHelper.isInWater(entity)) {
            WetHelper.setWet(entity);
            AngerHelper.startWarmup(entity);
        }

        WetHelper.tickWet(entity);

        if (WetHelper.isWet(entity)) {
            if (entity.tickCount % 2 == 0 && !AngerHelper.isAngry(entity)) {
                int increase = entity.level.random.nextInt(6);
                AngerHelper.addAnger(entity, increase);
            }
            AngerHelper.tickWarmup(entity);
            AngerHelper.tickAngerTimer(entity);
        } else {
            AngerHelper.resetAnger(entity);
        }
    }
}