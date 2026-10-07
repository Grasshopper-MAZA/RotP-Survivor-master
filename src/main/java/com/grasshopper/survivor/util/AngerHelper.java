package com.grasshopper.survivor.util;

import net.minecraft.entity.LivingEntity;

public class AngerHelper {

    public static final String ANGER = "SurvivorAnger";
    public static final String ANGER_TIMER = "SurvivorAngerTimer";
    public static final String WARMUP_TIMER = "SurvivorWarmupTimer";
    public static final int MAX_ANGER = 100;
    public static final int WARMUP_DURATION = 100;
    public static final int ANGER_DURATION = 600;

    public static int getAnger(LivingEntity entity) {
        return entity.getPersistentData().getInt(ANGER);
    }

    public static void setAnger(LivingEntity entity, int value) {
        entity.getPersistentData().putInt(ANGER, Math.max(0, Math.min(MAX_ANGER, value)));
    }

    public static void addAnger(LivingEntity entity, int value) {
        setAnger(entity, getAnger(entity) + value);
    }

    public static void resetAnger(LivingEntity entity) {
        entity.getPersistentData().putInt(ANGER, 0);
        entity.getPersistentData().remove(ANGER_TIMER);
        entity.getPersistentData().remove(WARMUP_TIMER);
    }

    public static boolean isAngry(LivingEntity entity) {
        return getAnger(entity) >= MAX_ANGER;
    }

    public static void startWarmup(LivingEntity entity) {
        if (!entity.getPersistentData().contains(WARMUP_TIMER)
                && !entity.getPersistentData().contains(ANGER_TIMER)) {
            entity.getPersistentData().putInt(WARMUP_TIMER, WARMUP_DURATION);
        }
    }

    public static void tickWarmup(LivingEntity entity) {
        if (!entity.getPersistentData().contains(WARMUP_TIMER)) return;

        int timer = entity.getPersistentData().getInt(WARMUP_TIMER);
        timer--;

        if (timer <= 0) {
            entity.getPersistentData().remove(WARMUP_TIMER);
            setAnger(entity, MAX_ANGER);
            entity.getPersistentData().putInt(ANGER_TIMER, ANGER_DURATION);
        } else {
            entity.getPersistentData().putInt(WARMUP_TIMER, timer);
        }
    }

    public static void tickAngerTimer(LivingEntity entity) {
        if (!entity.getPersistentData().contains(ANGER_TIMER)) return;

        int timer = entity.getPersistentData().getInt(ANGER_TIMER);
        timer--;

        if (timer <= 0) {
            resetAnger(entity);
        } else {
            entity.getPersistentData().putInt(ANGER_TIMER, timer);
        }
    }
}