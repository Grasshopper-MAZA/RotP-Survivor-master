package com.grasshopper.survivor.init;

import java.util.function.Supplier;

import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.util.mc.OstSoundList;
import com.grasshopper.survivor.SurvivorMain;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS, SurvivorMain.MOD_ID);

    public static final RegistryObject<SoundEvent> SURVIVOR_SUMMON_VOICELINE = SOUNDS.register("survivor_summon_voiceline",
            () -> new SoundEvent(new ResourceLocation(SurvivorMain.MOD_ID, "survivor_summon_voiceline")));

    public static final Supplier<SoundEvent> SURVIVOR_SUMMON_SOUND = ModSounds.STAND_SUMMON_DEFAULT;
    public static final Supplier<SoundEvent> SURVIVOR_UNSUMMON_SOUND = ModSounds.STAND_UNSUMMON_DEFAULT;

    public static final OstSoundList SURVIVOR_OST = new OstSoundList(
            new ResourceLocation(SurvivorMain.MOD_ID, "survivor_ost"), SOUNDS);
}