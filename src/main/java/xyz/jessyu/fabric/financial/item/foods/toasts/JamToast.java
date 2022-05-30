package xyz.jessyu.fabric.financial.item.foods.toasts;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroup;

public class JamToast extends Toast {
    public JamToast(Settings settings){
        super(settings.
                food(new FoodComponent.Builder().
                        hunger(6).
                        alwaysEdible().
                        saturationModifier(3F).
                        statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 20*20), 1)
                        .build()
                )
                .group(ItemGroup.FOOD)
        );
    }
}
