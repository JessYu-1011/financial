package xyz.jessyu.fabric.financial.block.ATM;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import xyz.jessyu.fabric.financial.Financial;
import xyz.jessyu.fabric.financial.item.Card;

public class ATMGuiDescription extends SyncedGuiDescription {
    private static int INVENTORY_SIZE = 50;
    public ATMGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context){
        super(
                Financial.ATM_SCREEN_HANDLER_TYPE,
                syncId,
                playerInventory,
                getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context)
        );

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(5*18, 5*18);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot cardSlot = WItemSlot.of(blockInventory, 0);
        cardSlot.setFilter(stack -> stack.getItem() instanceof Card );
        root.add(cardSlot, 4, 1);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 1);
        itemSlot.setFilter(stack -> ATM.checkExist(stack.getItem().getTranslationKey()));
        root.add(itemSlot, 8, 1);


        root.add(this.createPlayerInventoryPanel(), 0, 3);
        root.validate(this);
    }


}
