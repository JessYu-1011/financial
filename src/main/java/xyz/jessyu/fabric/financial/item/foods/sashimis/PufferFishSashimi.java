package xyz.jessyu.fabric.financial.item.foods.sashimis;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroup;

public class PufferFishSashimi extends Sashimi {
    public PufferFishSashimi(Settings settings){
        super(settings.
                food(new FoodComponent.Builder().
                        hunger(2).
                        saturationModifier(2F).
                        alwaysEdible().
                        statusEffect(new StatusEffectInstance(StatusEffects.POISON, 20*10), 0.01F).
                        statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20*10), 0.1F).
                        statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20*60), 1).
                        statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20*60), 1).
                        build()
                ).
                group(ItemGroup.FOOD)
        );
    }
}
