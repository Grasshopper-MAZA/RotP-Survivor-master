package com.grasshopper.survivor.event;

import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.grasshopper.survivor.SurvivorMain;
import com.grasshopper.survivor.util.WetHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = SurvivorMain.MOD_ID)
public class SurvivorAggroHandler {

    private static final double AGGRO_RANGE = 8.0D;

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        World world = entity.level;
        if (world.isClientSide()) return;

        if (!WetHelper.isWet(entity)) return;

        List<PlayerEntity> users = world.getEntitiesOfClass(PlayerEntity.class,
                entity.getBoundingBox().inflate(AGGRO_RANGE),
                player -> IStandPower.getStandPowerOptional(player).resolve()
                        .map(power -> power.getType().getRegistryName().getPath().equals("survivor"))
                        .orElse(false));

        if (users.isEmpty()) return;

        if (entity instanceof MobEntity) {
            List<LivingEntity> targets = world.getEntitiesOfClass(LivingEntity.class,
                    entity.getBoundingBox().inflate(16),
                    e -> e != entity && e.isAlive());

            if (!targets.isEmpty()) {
                LivingEntity target = targets.get(world.random.nextInt(targets.size()));
                ((MobEntity) entity).setTarget(target);
            }
        }
    }
}