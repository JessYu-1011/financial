package xyz.jessyu.fabric.financial;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.jessyu.fabric.financial.block.cashier.CashierBlock;
import xyz.jessyu.fabric.financial.block.cashier.CashierBlockEntity;
import xyz.jessyu.fabric.financial.block.cashier.libgui.CashierGuiDescription;
import xyz.jessyu.fabric.financial.item.Coin;
import xyz.jessyu.fabric.financial.item.foods.Kebab;
import xyz.jessyu.fabric.financial.item.foods.Salad;
import xyz.jessyu.fabric.financial.item.foods.sashimis.CodSashimi;
import xyz.jessyu.fabric.financial.item.foods.sashimis.PufferFishSashimi;
import xyz.jessyu.fabric.financial.item.foods.sashimis.SalmonSashimi;
import xyz.jessyu.fabric.financial.item.foods.sashimis.Sashimi;
import xyz.jessyu.fabric.financial.item.tools.KitchenKnife;
import xyz.jessyu.fabric.financial.item.tools.KitchenKnifeMaterial;


public class Financial implements ModInitializer {
    public static final String MOD_ID = "financial";
    public static Block CASHIER_BLOCK; // = new CashierBlock(Block.Settings.of(Material.STONE).requiresTool());
    public static BlockItem CASHIER_ITEM;
    public static Item COIN; // = new Coin(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64));
    public static BlockEntityType<CashierBlockEntity> CASHIER_BLOCK_ENTITY;
    public static ScreenHandlerType<CashierGuiDescription> SCREEN_HANDLER_TYPE;
    public static ToolItem KITCHEN_KNIFE;
    public static Sashimi COD_SASHIMI, PUFFER_FISH_SASHIMI, SALMON_SASHIMI;
    public static Kebab KEBAB;
    public static Salad SALAD;

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
                new Coin(
                        (new FabricItemSettings().group(ItemGroup.MISC).maxCount(1))
                )
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
                Registry.ITEM,
                new Identifier(MOD_ID, "kitchen_knife"),
                new KitchenKnife(
                        KitchenKnifeMaterial.INSTANCE,
                        3,
                        0.8F,
                        new Item.Settings().group(ItemGroup.TOOLS)
                )
        );

        COD_SASHIMI = Registry.register(
                Registry.ITEM,
                new Identifier(MOD_ID, "cod_sashimi"),
                new CodSashimi(new FabricItemSettings().
                        food(new FoodComponent.Builder().
                                hunger(4).
                                saturationModifier(6F).
                                alwaysEdible().
                                /**
                                 * Every 20 ticks are 1 second
                                 * */
                                statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20*10), 0.01F).
                                statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*10), 1).
                                build()
                        ).
                        group(ItemGroup.FOOD)
                )
        );

        PUFFER_FISH_SASHIMI = Registry.register(
                Registry.ITEM,
                new Identifier(MOD_ID, "puffer_fish_sashimi"),
                new PufferFishSashimi(new FabricItemSettings().
                        food(new FoodComponent.Builder().
                                hunger(2).
                                saturationModifier(6F).
                                alwaysEdible().
                                statusEffect(new StatusEffectInstance(StatusEffects.POISON, 20*10), 0.01F).
                                statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20*10), 0.1F).
                                statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20*60), 1).
                                statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20*60), 1).
                                build()
                        ).
                        group(ItemGroup.FOOD)
                )
        );

        SALMON_SASHIMI = Registry.register(
                Registry.ITEM,
                new Identifier(MOD_ID, "salmon_sashimi"),
                new SalmonSashimi(new FabricItemSettings().
                        food(new FoodComponent.Builder().
                                hunger(4).
                                saturationModifier(6F).
                                alwaysEdible().
                                statusEffect(new StatusEffectInstance(StatusEffects.POISON, 20*10), 0.01F).
                                statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*10), 1).
                                build()
                        )
                        .group(ItemGroup.FOOD)
                )
        );

        KEBAB = Registry.register(
                Registry.ITEM,
                new Identifier(MOD_ID, "kebab"),
                new Kebab(new FabricItemSettings().
                        food(new FoodComponent.Builder().
                                hunger(16).
                                saturationModifier(6F).
                                alwaysEdible().
                                statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*20), 1)
                                .build()
                        )
                        .group(ItemGroup.FOOD)
                )
        );

        SALAD = Registry.register(
                Registry.ITEM,
                new Identifier(MOD_ID, "salad"),
                new Salad(new FabricItemSettings().
                            food(new FoodComponent.Builder().
                                    hunger(20).
                                    statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*30), 1).
                                    statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20*30), 1)
                                    .build()
                            )
                        .group(ItemGroup.FOOD)
                        )
        );

    }
}
