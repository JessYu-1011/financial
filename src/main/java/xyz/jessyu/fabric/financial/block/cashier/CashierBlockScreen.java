package xyz.jessyu.fabric.financial.block.cashier;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class CashierBlockScreen extends CottonInventoryScreen<CashierGuiDescription> {

    public CashierBlockScreen(CashierGuiDescription gui, PlayerEntity player, Text title){
        super(gui, player, title);
    }
}
