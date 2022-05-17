package xyz.jessyu.fabric.financial.item.tools;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class KitchenKnifeMaterial implements ToolMaterial {

    public static final KitchenKnifeMaterial INSTANCE = new KitchenKnifeMaterial();

    @Override
    public int getDurability() {
        return 64;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 2.0F;
    }

    @Override
    public float getAttackDamage() {
        return 0F;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 8;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.RAW_IRON);
    }
}
