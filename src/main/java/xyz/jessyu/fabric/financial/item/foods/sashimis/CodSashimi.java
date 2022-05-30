package xyz.jessyu.fabric.financial.item.foods.sashimis;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroup;

public class CodSashimi extends Sashimi{
    public CodSashimi(Settings settings){
        super(settings.
                food(new FoodComponent.Builder().
                        hunger(4).
                        saturationModifier(2F).
                        alwaysEdible().
                        /**
                         * Every 20 ticks are 1 second
                         * */
                                statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20*10), 0.01F).
                        statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*10), 1).
                        build()
                ).
                group(ItemGroup.FOOD)
        );
    }
}
