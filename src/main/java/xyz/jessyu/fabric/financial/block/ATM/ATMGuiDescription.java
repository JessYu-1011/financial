package xyz.jessyu.fabric.financial.block.ATM;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import xyz.jessyu.fabric.financial.Financial;
import xyz.jessyu.fabric.financial.item.Card;

import java.util.Map;

public class ATMGuiDescription extends SyncedGuiDescription {
    private static int INVENTORY_SIZE = 50;
    public ATMGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context){
        super(
                Financial.ATM_SCREEN_HANDLER_TYPE,
                syncId,
                playerInventory,
                getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context)
        );

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(15*18, 10*18);
        root.setInsets(Insets.ROOT_PANEL);
        /**
         * cardSlot is for card
         * itemSlot is for sale item
         * */
        WItemSlot cardSlot = WItemSlot.of(blockInventory, 0);
        cardSlot.setFilter(stack -> stack.getItem() instanceof Card );
        root.add(cardSlot, 3*18, 2*18);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 1);
        itemSlot.setFilter(stack -> ATM.checkExist(stack.getItem().getTranslationKey()));
        root.add(itemSlot, 6*18, 2*18);

        WButton submit = new WButton();
        submit.setLabel(new LiteralText("Withdrawal"));
        root.add(submit, 8*18, 2*18);

        /**
         * Display the price of items
         * */
        WBox priceBox = new WBox(Axis.VERTICAL);
        priceBox.setSize(4*18, 5*18);
        priceBox.setInsets(Insets.ROOT_PANEL);


        for(Map.Entry<Item, Integer> entry: ATM.itemToMoneyItems.entrySet()){
            WPlainPanel pricePanel = new WPlainPanel();
            pricePanel.setSize(4*18, 2*18);
            pricePanel.setInsets(Insets.ROOT_PANEL);

            WButton buttonIcon = new WButton(new ItemIcon(new ItemStack(entry.getKey())));
            pricePanel.add(buttonIcon, 0, 0);

            WLabel priceLabel = new WLabel(new TranslatableText(entry.getValue().toString()));
            pricePanel.add(priceLabel, 2*18, 0);
            priceBox.add(pricePanel);
        }

        WScrollPanel scrollPanel = new WScrollPanel(priceBox);
        root.add(scrollPanel, 10*18, 1, 5*18, 10*18);

        root.add(this.createPlayerInventoryPanel(), 2, 6*18);
        root.validate(this);
    }


}
