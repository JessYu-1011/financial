package xyz.jessyu.fabric.financial.item.foods;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Breakfast extends Item {
    public Breakfast(Settings settings){
        super(settings.
                food(new FoodComponent.Builder().
                        hunger(20).
                        alwaysEdible().
                        statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 20*180), 1).
                        statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 20*180), 1).
                        statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*180), 1).
                        statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20*10), 1).
                        statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 20*60), 1)
                        .build()
                ).group(ItemGroup.FOOD)
        );
    }
}
