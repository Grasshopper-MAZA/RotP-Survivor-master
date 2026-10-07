package com.grasshopper.survivor.event;

import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.grasshopper.survivor.SurvivorMain;
import com.grasshopper.survivor.util.AngerHelper;
import com.grasshopper.survivor.util.WetHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = SurvivorMain.MOD_ID)
public class MobAggroHandler {

    private static final double SURVIVOR_RANGE = 8.0D;
    private static final double TARGET_RANGE = 16.0D;
    private static final double HIT_RANGE = 1.5D;
    private static final float HIT_DAMAGE = 2.0F;
    private static final double MOVE_SPEED = 0.25D;
    private static final int ATTACK_COOLDOWN = 0;

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        World world = entity.level;
        if (world.isClientSide()) return;

        if (!(entity instanceof MobEntity)) return;
        MobEntity mob = (MobEntity) entity;

        if (!WetHelper.isWet(mob)) return;
        if (!AngerHelper.isAngry(mob)) return;

        List<PlayerEntity> users = world.getEntitiesOfClass(PlayerEntity.class,
                mob.getBoundingBox().inflate(SURVIVOR_RANGE),
                player -> isSurvivorUser(player));

        if (users.isEmpty()) return;

        LivingEntity target = mob.getTarget();

        if (target instanceof PlayerEntity && isSurvivorUser((PlayerEntity) target)) {
            mob.setTarget(null);
            target = null;
        }

        if (target == null || !target.isAlive() || mob.distanceTo(target) > TARGET_RANGE) {
            List<LivingEntity> targets = world.getEntitiesOfClass(LivingEntity.class,
                    mob.getBoundingBox().inflate(TARGET_RANGE),
                    e -> e != mob && e.isAlive() && !(e instanceof PlayerEntity && isSurvivorUser((PlayerEntity) e)));

            if (targets.isEmpty()) return;

            target = targets.get(world.random.nextInt(targets.size()));
            mob.setTarget(target);
        }

        Vector3d direction = target.position().subtract(mob.position()).normalize().scale(MOVE_SPEED);
        mob.setDeltaMovement(direction.x, mob.getDeltaMovement().y, direction.z);
        mob.hurtMarked = true;

        if (mob.distanceTo(target) < HIT_RANGE && mob.attackAnim == 0.0F) {
            target.hurt(DamageSource.mobAttack(mob), HIT_DAMAGE);
            mob.attackAnim = ATTACK_COOLDOWN;
        }
    }

    private static boolean isSurvivorUser(PlayerEntity player) {
        return IStandPower.getStandPowerOptional(player).resolve()
                .map(power -> power.getType().getRegistryName().getPath().equals("survivor"))
                .orElse(false);
    }
}