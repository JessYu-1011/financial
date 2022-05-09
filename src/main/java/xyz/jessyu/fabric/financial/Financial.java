package xyz.jessyu.fabric.financial;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.jessyu.fabric.financial.block.cashier.CashierBlock;
import xyz.jessyu.fabric.financial.block.cashier.CashierBlockEntity;
import xyz.jessyu.fabric.financial.block.cashier.libgui.CashierGuiDescription;
import xyz.jessyu.fabric.financial.item.Coin;


public class Financial implements ModInitializer {
    public static final String MOD_ID = "financial";
    public static Block CASHIER_BLOCK; // = new CashierBlock(Block.Settings.of(Material.STONE).requiresTool());
    public static BlockItem CASHIER_ITEM;
    public static Item COIN; // = new Coin(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64));
    public static BlockEntityType<CashierBlockEntity> CASHIER_BLOCK_ENTITY;
    public static ScreenHandlerType<CashierGuiDescription> SCREEN_HANDLER_TYPE;

    @Override
    public void onInitialize() {
        CASHIER_BLOCK = Registry.register(
                Registry.BLOCK,
                new Identifier(MOD_ID, "cashier"),
                new CashierBlock(Block.Settings.of(Material.STONE).requiresTool())
        );

        CASHIER_ITEM = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cashier"),
                new BlockItem(CASHIER_BLOCK, new Item.Settings().group(ItemGroup.MISC)));

        COIN = Registry.register(
                Registry.ITEM,
                new Identifier(MOD_ID, "coin"),
                new Coin((new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)))
        );

        CASHIER_BLOCK_ENTITY = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MOD_ID, "cashier_block_entity"),
                FabricBlockEntityTypeBuilder.create(CashierBlockEntity::new, CASHIER_BLOCK).build(null)
        );

        SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "screen_handler_type"),
                (syncId, inventory) -> new CashierGuiDescription(
                        syncId, inventory, ScreenHandlerContext.EMPTY)
        );
    }
}
