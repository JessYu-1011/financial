package xyz.jessyu.fabric.financial.item.foods.sashimis;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Sashimi extends Item {
    public Sashimi(Settings settings){
        super(settings.maxCount(64).group(ItemGroup.FOOD));
    }

    @Override
    public boolean isFood() {
        return true;
    }
}
