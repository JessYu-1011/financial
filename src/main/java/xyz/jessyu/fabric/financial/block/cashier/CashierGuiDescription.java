package xyz.jessyu.fabric.financial.block.cashier;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import xyz.jessyu.fabric.financial.Financial;



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
        root.setSize(23*18, 12*18);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot cardSlot = WItemSlot.of(blockInventory, 0);
        cardSlot.setFilter(stack -> stack.getItem() == Financial.COIN);
        root.add(cardSlot, 50, 40);

        /**
         * Thr right han side root painel
         * Inside has left-hand side button
         * Right-hand side goods itemSlot
         * */

        WBox box = new WBox(Axis.VERTICAL);
        box.setSize(12*18, 12*18);
        box.setInsets(Insets.ROOT_PANEL);

        int itemSlotIndex = 2;
        for(int i = 0; i < 6 ; i ++){
            WBox innerBox = new WBox(Axis.VERTICAL);
            innerBox.setSize(10*18, 30);
            innerBox.setInsets(Insets.ROOT_PANEL);
            for(int j = 0 ; j < 5 ; j++){
                WPlainPanel innerPanel = new WPlainPanel();
                innerPanel.setSize(10*18, 30);
                innerPanel.setInsets(Insets.ROOT_PANEL);
                WButton btn = new WButton();
                WItemSlot innerSlot = WItemSlot.of(blockInventory, itemSlotIndex);
                innerPanel.add(btn, 1, 1);
                innerPanel.add(innerSlot, 7*18, 1);
                innerBox.add(innerPanel);
                itemSlotIndex++;
            }
            box.add(innerBox);
        }

        WScrollPanel sp = new WScrollPanel(box);
        root.add(sp, 11*18, 2, 12*18, 12*18);
        root.add(this.createPlayerInventoryPanel(), 0, 100);
        root.validate(this);
    }
}
