package xyz.jessyu.fabric.financial.item.foods.sashimis;

import net.minecraft.item.Item;

public class Sashimi extends Item {
    public Sashimi(Settings settings){
        super(settings.maxCount(64));
    }

    @Override
    public boolean isFood() {
        return true;
    }
}
