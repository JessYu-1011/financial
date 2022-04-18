package xyz.jessyu.fabric.financial.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import xyz.jessyu.fabric.financial.Financial;
import xyz.jessyu.fabric.financial.block.inventory.CashierBlockInventory;

public class CashierBlockEntity extends BlockEntity implements CashierBlockInventory {

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public CashierBlockEntity(BlockPos pos, BlockState state){
        super(Financial.CASHIER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems(){
        return items;
    }

}
