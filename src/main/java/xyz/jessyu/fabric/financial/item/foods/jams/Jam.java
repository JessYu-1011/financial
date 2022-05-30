package xyz.jessyu.fabric.financial.item.foods.jams;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Jam extends Item {
    public Jam(Settings settings){
        super(settings.food(new FoodComponent.Builder()
                        .build()
                )
                .group(ItemGroup.FOOD));
    }
}
