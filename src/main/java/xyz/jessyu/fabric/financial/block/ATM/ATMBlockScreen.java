package xyz.jessyu.fabric.financial.block.ATM;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class ATMBlockScreen extends CottonInventoryScreen<ATMGuiDescription> {
    public ATMBlockScreen(ATMGuiDescription gui, PlayerEntity player, Text title){
        super(gui, player, title);
    }
}
