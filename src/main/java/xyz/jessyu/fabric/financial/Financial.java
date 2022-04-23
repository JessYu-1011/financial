package xyz.jessyu.fabric.financial;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.jessyu.fabric.financial.block.CashierBlock;
import xyz.jessyu.fabric.financial.block.CashierBlockEntity;
import xyz.jessyu.fabric.financial.item.Coin;


public class Financial implements ModInitializer {
    public static final String MOD_ID = "financial";
    public static final CashierBlock CASHIER_BLOCK = new CashierBlock(Block.Settings.of(Material.STONE).requiresTool());
    public static final Coin COIN = new Coin(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64));
    public static BlockEntityType<CashierBlockEntity> CASHIER_BLOCK_ENTITY;


    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cashier"), CASHIER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cashier"),
                new BlockItem(CASHIER_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "coin"), COIN);
        CASHIER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                "financial:cashier_block_entity",
                FabricBlockEntityTypeBuilder.create(CashierBlockEntity::new, CASHIER_BLOCK).build(null)
        );
    }
}
