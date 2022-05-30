package xyz.jessyu.fabric.financial;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.jessyu.fabric.financial.block.cashier.CashierBlock;
import xyz.jessyu.fabric.financial.block.cashier.CashierBlockEntity;
import xyz.jessyu.fabric.financial.block.cashier.libgui.CashierGuiDescription;
import xyz.jessyu.fabric.financial.item.BowlWithStick;
import xyz.jessyu.fabric.financial.item.Card;
import xyz.jessyu.fabric.financial.item.Plate;
import xyz.jessyu.fabric.financial.item.foods.Breakfast;
import xyz.jessyu.fabric.financial.item.foods.Kebab;
import xyz.jessyu.fabric.financial.item.foods.Salad;
import xyz.jessyu.fabric.financial.item.foods.jams.Jam;
import xyz.jessyu.fabric.financial.item.foods.toasts.*;
import xyz.jessyu.fabric.financial.item.foods.jams.AppleJam;
import xyz.jessyu.fabric.financial.item.foods.jams.SweetBerriesJam;
import xyz.jessyu.fabric.financial.item.foods.jams.WatermelonJam;
import xyz.jessyu.fabric.financial.item.foods.sashimis.CodSashimi;
import xyz.jessyu.fabric.financial.item.foods.sashimis.PufferFishSashimi;
import xyz.jessyu.fabric.financial.item.foods.sashimis.SalmonSashimi;
import xyz.jessyu.fabric.financial.item.foods.sashimis.Sashimi;
import xyz.jessyu.fabric.financial.item.tools.KitchenKnife;
import xyz.jessyu.fabric.financial.item.tools.KitchenKnifeMaterial;


public class Financial implements ModInitializer {
    public static final String MOD_ID = "financial";
    public static Block CASHIER_BLOCK;
    public static BlockItem CASHIER_ITEM;
    public static Card CARD;
    public static BlockEntityType<CashierBlockEntity> CASHIER_BLOCK_ENTITY;
    public static ScreenHandlerType<CashierGuiDescription> SCREEN_HANDLER_TYPE;
    public static ToolItem KITCHEN_KNIFE;
    public static Sashimi COD_SASHIMI, PUFFER_FISH_SASHIMI, SALMON_SASHIMI;
    public static Kebab KEBAB;
    public static Salad SALAD;
    public static Breakfast BREAKFAST;
    public static Plate PLATE;
    public static BowlWithStick BOWL_WITH_STICK;
    public static Jam APPLE_JAM, SWEET_BERRIES_JAM, WATERMELON_JAM;
    public static Toast TOAST, APPLE_JAM_TOAST, SWEET_BERRIES_JAM_TOAST, WATERMELON_JAM_TOAST;

    @Override
    public void onInitialize() {
        CASHIER_BLOCK = Registry.register(
                Registry.BLOCK,
                new Identifier(MOD_ID, "cashier"),
                new CashierBlock(Block.Settings.of(Material.STONE).requiresTool())
        );

        CASHIER_ITEM = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cashier"),
                new BlockItem(CASHIER_BLOCK, new Item.Settings().group(ItemGroup.MISC)));

        CARD = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "card"), new Card(new FabricItemSettings())
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

        KITCHEN_KNIFE = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "kitchen_knife"),
                new KitchenKnife(KitchenKnifeMaterial.INSTANCE, 3, 0.8F,
                        new FabricItemSettings().group(ItemGroup.TOOLS)
                )
        );

        COD_SASHIMI = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "cod_sashimi"), new CodSashimi(new FabricItemSettings())
        );

        PUFFER_FISH_SASHIMI = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "puffer_fish_sashimi"), new PufferFishSashimi(new FabricItemSettings())
        );

        SALMON_SASHIMI = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "salmon_sashimi"), new SalmonSashimi(new FabricItemSettings())
        );

        KEBAB = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "kebab"), new Kebab(new FabricItemSettings())
        );

        SALAD = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "salad"), new Salad(new FabricItemSettings())
        );

        APPLE_JAM = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "apple_jam"), new AppleJam(new FabricItemSettings())
        );

        SWEET_BERRIES_JAM = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "sweet_berries_jam"), new SweetBerriesJam(new FabricItemSettings())
        );

        WATERMELON_JAM =  Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "watermelon_jam"), new WatermelonJam(new FabricItemSettings())
        );

        TOAST = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "toast"), new Toast(new FabricItemSettings())
        );

        APPLE_JAM_TOAST = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "apple_jam_toast"), new AppleJamToast(new FabricItemSettings())
        );

        SWEET_BERRIES_JAM_TOAST = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "sweet_berries_jam_toast"), new SweetBerriesJamToast(new FabricItemSettings())
        );

        WATERMELON_JAM_TOAST = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "watermelon_jam_toast"), new WatermelonJamToast(new FabricItemSettings())
        );

        BREAKFAST = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "breakfast"), new Breakfast(new FabricItemSettings())
        );

        PLATE = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "plate"), new Plate(new FabricItemSettings())
        );

        BOWL_WITH_STICK = Registry.register(
                Registry.ITEM, new Identifier(MOD_ID, "bowl_with_stick"), new BowlWithStick(new FabricItemSettings())
        );
    }
}
