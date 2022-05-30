package xyz.jessyu.fabric.financial.item.foods;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Kebab extends Item {
    public Kebab(Settings settings){
        super(settings.
                food(new FoodComponent.Builder().
                        hunger(16).
                        saturationModifier(8F).
                        alwaysEdible().
                        statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*20), 1)
                        .build()
                )
                .group(ItemGroup.FOOD)
        );
    }
}
