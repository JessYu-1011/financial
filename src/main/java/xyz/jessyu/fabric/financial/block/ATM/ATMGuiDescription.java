package xyz.jessyu.fabric.financial.block.ATM;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.networking.NetworkSide;
import io.github.cottonmc.cotton.gui.networking.ScreenNetworking;
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
import net.minecraft.util.Identifier;
import xyz.jessyu.fabric.financial.Financial;
import xyz.jessyu.fabric.financial.item.Card;

import java.util.Map;

public class ATMGuiDescription extends SyncedGuiDescription {
    private static int INVENTORY_SIZE = 2;
    private final int CARD_INDEX = 0;
    private final int ITEM_INDEX = 1;
    private static final Identifier ONCLICK_MESSAGE = new Identifier(Financial.MOD_ID, "onclicked");

    public ATMGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context){
        super(
                Financial.ATM_SCREEN_HANDLER_TYPE, syncId, playerInventory,
                getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context)
        );

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(15*18, 10*18);
        root.setInsets(Insets.ROOT_PANEL);
        /**
         * @param cardSlot is the slot to put the card
         * @param itemSlot is the slot to put the item for selling
         * */
        WItemSlot cardSlot = WItemSlot.of(blockInventory, CARD_INDEX);
        cardSlot.setFilter(stack -> stack.getItem() instanceof Card );
        root.add(cardSlot, 2*18, 2*18);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, ITEM_INDEX);
        itemSlot.setFilter(stack -> ATM.checkExist(stack.getItem().getTranslationKey()));
        root.add(itemSlot, 5*18, 2*18);

        /**
         * If the player click the button, then go to check the price of item then return the balance into player's card.
         * We use the {@link ScreenNetworking} to send the packet, bcuz the button just work at the client side.
         * */

        ScreenNetworking.of(this, NetworkSide.SERVER).receive(ONCLICK_MESSAGE, buf -> {
            ItemStack item = blockInventory.getStack(ITEM_INDEX);
            ItemStack card = blockInventory.getStack(CARD_INDEX);
            if(item != ItemStack.EMPTY && card != ItemStack.EMPTY){
                int price = ATM.getItemPrice(item);
                Card.modifyBalance(blockInventory.getStack(CARD_INDEX), price);
                blockInventory.removeStack(ITEM_INDEX, item.getCount());
                blockInventory.setStack(ITEM_INDEX, ItemStack.EMPTY);
            }
        });

        WButton submit = new WButton();
        submit.setLabel(new LiteralText("Withdrawal"));
        submit.setOnClick(() -> {
            ScreenNetworking.of(this, NetworkSide.CLIENT).send(ONCLICK_MESSAGE, buf -> {
                buf.writeInt(1);
            });
        });
        root.add(submit, 46, 4*18, 55, 20);


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
