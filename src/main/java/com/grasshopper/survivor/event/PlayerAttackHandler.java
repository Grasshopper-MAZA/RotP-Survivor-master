package com.grasshopper.survivor.event;

import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.grasshopper.survivor.SurvivorMain;
import com.grasshopper.survivor.util.AngerHelper;
import com.grasshopper.survivor.util.WetHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = SurvivorMain.MOD_ID)
public class PlayerAttackHandler {

    private static final double SURVIVOR_RANGE = 8.0D;
    private static final double TARGET_RANGE = 4.0D;

    private static boolean isAttacking = false;

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        if (isAttacking) return;

        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide()) return;
        if (!WetHelper.isWet(player)) return;
        if (!AngerHelper.isAngry(player)) return;

        boolean isSurvivorUser = IStandPower.getStandPowerOptional(player).resolve()
                .map(power -> power.getType().getRegistryName().getPath().equals("survivor"))
                .orElse(false);
        if (isSurvivorUser) return;

        boolean nearSurvivor = player.level.getEntitiesOfClass(PlayerEntity.class,
                        player.getBoundingBox().inflate(SURVIVOR_RANGE),
                        p -> IStandPower.getStandPowerOptional(p).resolve()
                                .map(power -> power.getType().getRegistryName().getPath().equals("survivor"))
                                .orElse(false))
                .size() > 0;

        if (!nearSurvivor) return;

        List<LivingEntity> targets = player.level.getEntitiesOfClass(LivingEntity.class,
                player.getBoundingBox().inflate(TARGET_RANGE),
                e -> e != player && e.isAlive());

        if (targets.isEmpty()) return;

        LivingEntity newTarget = targets.get(player.level.random.nextInt(targets.size()));

        event.setCanceled(true);
        isAttacking = true;
        player.attack(newTarget);
        isAttacking = false;
    }
}