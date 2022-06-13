package xyz.jessyu.fabric.financial.block.ATM;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class ATM extends BlockWithEntity {
    public static Map<String, Integer> itemToMoney = new HashMap<String, Integer>();
    public static Map<Item, Integer> itemToMoneyItems = new HashMap<Item, Integer>();
    private static String minecraftItemPrefix = "item.minecraft.";
    public ATM(Settings settings){
        super(settings.nonOpaque());
        setItemPrice();
        setItems();
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ATMBlockEntity(pos, state);
    }

    /**
     * This method will be called when open the block.
     * The first player open this Block will be the owner of this block
     * */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state){
        return BlockRenderType.MODEL;
    }

    public static void putItemPrice(String itemName, int price){
        itemToMoney.put(minecraftItemPrefix + itemName, price);
    }

    /**
     * Set the price of the Item, and this method will be called when new a instance.
     * */
    private static void setItemPrice(){
        putItemPrice( "rotten_flesh", 5);
        putItemPrice("bone", 10);
        putItemPrice( "gunpowder", 20);
        putItemPrice( "spider_eye", 50);
        putItemPrice( "blaze_rod", 100);
        putItemPrice( "ender_pearl", 100);
        putItemPrice("sweet_berries", 1);
        putItemPrice("mushroom", 5);
        putItemPrice("wheat", 10);
        putItemPrice("carrot", 10);
        putItemPrice("potato", 10);
        putItemPrice("beetroot", 10);
        putItemPrice("melon_slice", 10);
        putItemPrice("apple", 20);
        putItemPrice("cookie", 20);
        putItemPrice("pumpkin_pie", 50);
        putItemPrice("cake", 100);
        putItemPrice("golden_apple", 1000);
        putItemPrice("enchanted_golden_apple", 5000);
        putItemPrice("egg", 5);
        putItemPrice("chicken", 10);
        putItemPrice("rabbit", 10);
        putItemPrice("porkchop", 10);
        putItemPrice("beef", 20);
        putItemPrice("mutton", 20);
        putItemPrice("cod", 10);
        putItemPrice("salmon", 10);
        putItemPrice("tropical_fish", 10);
        putItemPrice("pufferfish", 100);
        putItemPrice("cod_bucket", 200);
        putItemPrice("salmon_bucket", 200);
        putItemPrice("tropical_fish_bucket", 200);
        putItemPrice("pufferfish_bucket", 500);
        putItemPrice("coal", 10);
        putItemPrice("iron_ingot", 50);
        putItemPrice("copper_ingot", 50);
        putItemPrice("redstone", 50);
        putItemPrice("quartz", 50);
        putItemPrice("gold_ingot", 100);
        putItemPrice("lapis_lazuli", 200);
        putItemPrice("emerald", 200);
        putItemPrice("diamond", 500);
        putItemPrice("netherite_ingot", 1000);
    }
    private static void setItems() {
        itemToMoneyItems.put(Items.SWEET_BERRIES, 1);
        itemToMoneyItems.put(Items.ROTTEN_FLESH, 5);
        itemToMoneyItems.put(Items.BROWN_MUSHROOM, 5);
        itemToMoneyItems.put(Items.EGG, 5);
        itemToMoneyItems.put(Items.RED_MUSHROOM, 5);
        itemToMoneyItems.put(Items.WHEAT, 10);
        itemToMoneyItems.put(Items.CARROT, 10);
        itemToMoneyItems.put(Items.POTATO, 10);
        itemToMoneyItems.put(Items.BEETROOT, 10);
        itemToMoneyItems.put(Items.MELON_SLICE, 10);
        itemToMoneyItems.put(Items.BONE, 10);
        itemToMoneyItems.put(Items.COD, 10);
        itemToMoneyItems.put(Items.SALMON, 10);
        itemToMoneyItems.put(Items.TROPICAL_FISH, 10);
        itemToMoneyItems.put(Items.COAL, 10);
        itemToMoneyItems.put(Items.CHICKEN, 10);
        itemToMoneyItems.put(Items.RABBIT, 10);
        itemToMoneyItems.put(Items.PORKCHOP, 10);
        itemToMoneyItems.put(Items.BEEF, 20);
        itemToMoneyItems.put(Items.MUTTON, 20);
        itemToMoneyItems.put(Items.GUNPOWDER, 20);
        itemToMoneyItems.put(Items.APPLE, 20);
        itemToMoneyItems.put(Items.COOKIE, 20);
        itemToMoneyItems.put(Items.SPIDER_EYE, 50);
        itemToMoneyItems.put(Items.PUMPKIN_PIE, 50);
        itemToMoneyItems.put(Items.IRON_INGOT, 50);
        itemToMoneyItems.put(Items.COPPER_INGOT, 50);
        itemToMoneyItems.put(Items.REDSTONE, 50);
        itemToMoneyItems.put(Items.QUARTZ, 50);
        itemToMoneyItems.put(Items.CAKE, 100);
        itemToMoneyItems.put(Items.BLAZE_ROD, 100);
        itemToMoneyItems.put(Items.ENDER_PEARL, 100);
        itemToMoneyItems.put(Items.GOLD_INGOT, 100);
        itemToMoneyItems.put(Items.PUFFERFISH, 100);
        itemToMoneyItems.put(Items.LAPIS_LAZULI, 200);
        itemToMoneyItems.put(Items.EMERALD, 200);
        itemToMoneyItems.put(Items.COD_BUCKET, 200);
        itemToMoneyItems.put(Items.SALMON_BUCKET, 200);
        itemToMoneyItems.put(Items.TROPICAL_FISH_BUCKET, 200);
        itemToMoneyItems.put(Items.PUFFERFISH_BUCKET, 500);
        itemToMoneyItems.put(Items.DIAMOND, 500);
        itemToMoneyItems.put(Items.NETHERITE_INGOT, 1000);
        itemToMoneyItems.put(Items.GOLDEN_APPLE, 1000);
        itemToMoneyItems.put(Items.ENCHANTED_GOLDEN_APPLE, 5000);
    }

    /*** To get the price of the given {@link ItemStack}
     * @param stack the ItemStack want to get price
     * @return The price of the Item
     */
    public static int getItemPrice(ItemStack stack){
        if(stack != null){
            int count = stack.getCount();
            int price = itemToMoney.get(stack.getItem().getTranslationKey());
            return price*count;
        }
        return 0;
    }

    public static boolean checkExist(String itemId){
        if(itemToMoney.containsKey(itemId)){
            return true;
        } else {
            return false;
        }
    }
}
