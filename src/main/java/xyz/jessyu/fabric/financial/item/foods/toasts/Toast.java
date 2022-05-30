package xyz.jessyu.fabric.financial.item.foods.toasts;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Toast extends Item {
    public Toast(Settings settings){
        super(settings.
                food(new FoodComponent.Builder().
                        hunger(2)
                        .build()
                )
                .group(ItemGroup.FOOD)
        );
    }
}
