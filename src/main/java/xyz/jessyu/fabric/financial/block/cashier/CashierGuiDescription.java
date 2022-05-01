package xyz.jessyu.fabric.financial.block.cashier;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import xyz.jessyu.fabric.financial.Financial;

public class CashierGuiDescription extends SyncedGuiDescription {
    private static int INVENTORY_SIZE = 100;

    public CashierGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context){
        super(Financial.SCREEN_HANDLER_TYPE,
                syncId,
                playerInventory,
                getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context)
        );

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(400, 200);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot cardSlot = WItemSlot.of(blockInventory, 0);
        cardSlot.setFilter(stack -> stack.getItem() == Financial.COIN);
        root.add(cardSlot, 50, 40);

        WBox box = new WBox(Axis.VERTICAL);
        for(int i = 0 ; i < 10 ; i++){
            WPlainPanel panel = new WPlainPanel();
            panel.setBackgroundPainter(BackgroundPainter.VANILLA);
            panel.setInsets(Insets.ROOT_PANEL);
            panel.setSize(230, 60);

            WButton innerBtn = new WButton();
            panel.add(innerBtn, 1, 1, 30, 10);

            WItemSlot innerSlot =  WItemSlot.of(blockInventory, i+50);
            panel.add(innerSlot, 170, 0);
            box.add(panel);
        }

        WScrollPanel sp = new WScrollPanel(box);
        root.add(sp, 170, 15, 230, 180);

        root.add(this.createPlayerInventoryPanel(), 0, 100);
        root.validate(this);
    }
}
