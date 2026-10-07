package com.grasshopper.survivor.client.render;

import com.grasshopper.survivor.entity.SurvivorEntity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SurvivorRenderer extends EntityRenderer<SurvivorEntity> {

    public SurvivorRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getTextureLocation(SurvivorEntity entity) {
        return null;
    }
}