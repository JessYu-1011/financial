package xyz.jessyu.fabric.financial.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import xyz.jessyu.fabric.financial.Financial;
import xyz.jessyu.fabric.financial.block.cashier.CashierScreen;

@Environment(EnvType.CLIENT)
public class FinancialClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(Financial.CASHIER_SCREEN_HANDLER, CashierScreen::new);
    }
}
