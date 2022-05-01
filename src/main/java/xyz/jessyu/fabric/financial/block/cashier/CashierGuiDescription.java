package xyz.jessyu.fabric.financial.block.cashier;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import xyz.jessyu.fabric.financial.Financial;

public class CashierGuiDescription extends SyncedGuiDescription {
    private static int INVENTORY_SIZE = 1;

    public CashierGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context){
        super(Financial.SCREEN_HANDLER_TYPE,
                syncId,
                playerInventory,
                getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context)
        );

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(300, 200);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot cardSlot = WItemSlot.of(blockInventory, 0);
        cardSlot.setFilter(stack -> stack.getItem() == Financial.COIN);
        root.add(cardSlot, 2, 40);

        WBox box = new WBox(Axis.HORIZONTAL);
        for(int i = 0 ; i < 10 ; i++){
            WButton btn = new WButton();
            btn.setSize(3, 8);
        }


        root.add(this.createPlayerInventoryPanel(), 0, 100);
        root.validate(this);
    }
}
