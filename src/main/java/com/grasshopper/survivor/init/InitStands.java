package com.grasshopper.survivor.init;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.grasshopper.survivor.SurvivorMain;
import com.grasshopper.survivor.entity.SurvivorEntity;

import net.minecraftforge.registries.DeferredRegister;

public class InitStands {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), SurvivorMain.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), SurvivorMain.MOD_ID);

    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<SurvivorEntity>> STAND_SURVIVOR =
            new EntityStandRegistryObject<>("survivor",
                    STANDS,
                    () -> new EntityStandType.Builder<StandStats>()
                            .color(0xFFFF00)
                            .storyPartName(ModStandsInit.PART_6_NAME)
                            .leftClickHotbar()
                            .rightClickHotbar()
                            .defaultStats(StandStats.class, new StandStats.Builder()
                                    .tier(6)
                                    .power(1)
                                    .speed(1)
                                    .range(100, 100)
                                    .durability(1)
                                    .precision(1)
                                    .build())
                            .disableManualControl()
                            .addSummonShout(InitSounds.SURVIVOR_SUMMON_VOICELINE)
                            .addOst(InitSounds.SURVIVOR_OST)
                            .build(),

                    InitEntities.ENTITIES,
                    () -> new StandEntityType<SurvivorEntity>(SurvivorEntity::new, 0.7F, 2.1F)
                            .summonSound(InitSounds.SURVIVOR_SUMMON_SOUND)
                            .unsummonSound(InitSounds.SURVIVOR_UNSUMMON_SOUND))
                    .withDefaultStandAttributes();
}