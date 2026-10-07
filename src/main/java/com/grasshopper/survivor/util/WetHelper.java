package com.grasshopper.survivor.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WetHelper {

    public static final String WET = "SurvivorWet";
    public static final String WET_TIMER = "SurvivorWetTimer";
    public static final int WET_DURATION = 600;

    public static boolean isWet(LivingEntity entity) {
        return entity.getPersistentData().getBoolean(WET);
    }

    public static void setWet(LivingEntity entity) {
        entity.getPersistentData().putBoolean(WET, true);
        entity.getPersistentData().putInt(WET_TIMER, WET_DURATION);
    }

    public static void tickWet(LivingEntity entity) {
        if (!isWet(entity)) return;

        int timer = entity.getPersistentData().getInt(WET_TIMER);
        timer--;
        if (timer <= 0) {
            entity.getPersistentData().remove(WET);
            entity.getPersistentData().remove(WET_TIMER);
        } else {
            entity.getPersistentData().putInt(WET_TIMER, timer);
        }
    }

    public static boolean isInWater(LivingEntity entity) {
        AxisAlignedBB box = entity.getBoundingBox().inflate(0.1D);
        BlockPos min = new BlockPos(box.minX, box.minY, box.minZ);
        BlockPos max = new BlockPos(box.maxX, box.maxY, box.maxZ);
        World world = entity.level;

        for (BlockPos pos : BlockPos.betweenClosed(min, max)) {
            BlockState state = world.getBlockState(pos);

            if (state.getFluidState().is(FluidTags.WATER)) return true;
            if (state.is(Blocks.ICE) || state.is(Blocks.SNOW_BLOCK)) return true;
            if (state.hasProperty(BlockStateProperties.WATERLOGGED)
                    && state.getValue(BlockStateProperties.WATERLOGGED)) {
                return true;
            }
        }

        return world.isRainingAt(entity.blockPosition());
    }
}