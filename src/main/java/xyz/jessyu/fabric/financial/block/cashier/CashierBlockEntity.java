package xyz.jessyu.fabric.financial.block.cashier;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import xyz.jessyu.fabric.financial.Financial;
import xyz.jessyu.fabric.financial.block.cashier.inventory.CashierBlockInventory;
import xyz.jessyu.fabric.financial.block.cashier.libgui.CashierGuiDescription;

public class CashierBlockEntity extends BlockEntity implements CashierBlockInventory, NamedScreenHandlerFactory {

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(50, ItemStack.EMPTY);

    public CashierBlockEntity(BlockPos pos, BlockState state){
        super(Financial.CASHIER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems(){
        return items;
    }


    /**
     * Read and write Inventories tag
     * */
    @Override
    public void readNbt(NbtCompound nbt){
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.items);
    }

    @Override
    public void writeNbt(NbtCompound nbt){
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.items);
    }

    @Override
    public Text getDisplayName() {
        // Using the block name as the screen title
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
        return new CashierGuiDescription(syncId, inventory, ScreenHandlerContext.create(world, pos));
    }
}
