package xyz.jessyu.fabric.financial.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import xyz.jessyu.fabric.financial.Financial;
import xyz.jessyu.fabric.financial.block.cashier.libgui.CashierBlockScreen;
import xyz.jessyu.fabric.financial.block.cashier.libgui.CashierGuiDescription;

@Environment(EnvType.CLIENT)
public class FinancialClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.<CashierGuiDescription, CashierBlockScreen>register(
                Financial.SCREEN_HANDLER_TYPE,
                (gui, inventory, title) -> new CashierBlockScreen(
                        gui, inventory.player, title
                )
        );
    }
}
