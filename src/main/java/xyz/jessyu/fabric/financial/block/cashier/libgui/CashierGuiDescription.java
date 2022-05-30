package xyz.jessyu.fabric.financial.block.cashier.libgui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import xyz.jessyu.fabric.financial.Financial;

import java.util.concurrent.atomic.AtomicInteger;


public class CashierGuiDescription extends SyncedGuiDescription {
    private static int INVENTORY_SIZE = 50;

    public CashierGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context){
        super(Financial.SCREEN_HANDLER_TYPE,
                syncId,
                playerInventory,
                getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context)
        );

        /**
         * Root Panel
         * Contains left-hand side => PlayerInventory and Card itemSlot
         * Right-hand side => list of the goods for selling
         * */
        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(20*18, 12*18);
        root.setInsets(Insets.ROOT_PANEL);

        /**
         * The slot for putting card
         * */
        WItemSlot cardSlot = WItemSlot.of(blockInventory, 0);
        cardSlot.setFilter(stack -> stack.getItem() == Financial.CARD);
        root.add(cardSlot, 50, 40);


        /**
         * This panel if for goods storage
         */
        WPlainPanel panel = new WPlainPanel();
        panel.setSize(4*18, 12*18);
        root.add(panel, 9*18, 0);


        /**
         * rightPanel is for goods inventories
         * */
        WPlainPanel rightPanel = new WPlainPanel();
        panel.setSize(6*18, 12*18);
        int slotIndex = 2;
        int slotX = 2;
        for(int i = 0 ; i < 3; i ++){
            int slotY = 2;
            for(int j = 0; j < 10; j++){
                WItemSlot innerSlot = WItemSlot.of(blockInventory, slotIndex);
                innerSlot.setInsertingAllowed(false);
                /**
                 * When the player want to take out the item from the slot,
                 * the balance of the card will decrease
                 * This part has been set in Inventory class
                 * */
                rightPanel.add(innerSlot, slotX, slotY);
                slotY += 18;
                slotIndex ++;
            }
            slotX += 18;
        }
        root.add(rightPanel, 14*18, 0);


        /**
         * When the itemstack put to this slot,
         * the itemstack will move to the goods inventories
         * */
        AtomicInteger outerIndex = new AtomicInteger(2);
        WItemSlot putSlot = WItemSlot.of(blockInventory, 1);
        putSlot.setFilter(stack -> stack.getItem() != Financial.CARD);
        putSlot.addChangeListener(
                (slot, inventory, index, stack) -> {
                    if(!stack.isEmpty()){
                        inventory.setStack(outerIndex.get(), stack.copy());
                        inventory.removeStack(1);
                        inventory.markDirty();
                        outerIndex.getAndIncrement();
                    }
                }
        );
        root.add(putSlot, 90, 40);

        root.add(this.createPlayerInventoryPanel(), 0, 100);
        root.validate(this);
    }

}
