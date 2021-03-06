package xyz.jessyu.fabric.financial.item.foods;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Salad extends Item {
    public Salad(Settings settings){
        super(settings.
                food(new FoodComponent.Builder().
                        hunger(20).
                        alwaysEdible().
                        statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*30), 1).
                        statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20*30), 1)
                        .build()
                )
                .group(ItemGroup.FOOD)
        );
    }
}
