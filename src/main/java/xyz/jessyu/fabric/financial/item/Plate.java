package xyz.jessyu.fabric.financial.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Plate extends Item {
    public Plate(Settings settings){
        super(settings.group(ItemGroup.MISC));
    }
}
