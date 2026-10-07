package com.grasshopper.survivor.entity;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import net.minecraft.world.World;

public class SurvivorEntity extends StandEntity {

    public SurvivorEntity(StandEntityType<SurvivorEntity> type, World world) {
        super(type, world);
    }
}