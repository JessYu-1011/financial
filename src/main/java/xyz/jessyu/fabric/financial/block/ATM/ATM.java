package xyz.jessyu.fabric.financial.block.ATM;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class ATM extends BlockWithEntity {
    private static Map<String, Integer> itemToMoney = new HashMap<String, Integer>();
    private static String minecraftItemPrefix = "item.minecraft.";
    public ATM(Settings settings){
        super(settings.nonOpaque());
        setItemPrice();
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ATMBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state){
        return BlockRenderType.MODEL;
    }


    /**
     *  腐肉		5元
     * 	骨頭		10元
     * 	火藥		20元
     * 	蜘蛛眼	50元
     * 	烈焰桿	100元
     * 	終界珍珠	100元
     *
     * 	種子		1元 --> this will be finished at the end, have to be with tag
     * 	甜梅		1元
     * 	花(各種)	1元  --> this will be finished at the end, have to be with tag
     * 	蘑菇(雙色)	5元
     * 	小麥		10元
     * 	胡蘿蔔	10元
     * 	馬鈴薯	10元
     * 	甜菜根	10元
     * 	西瓜片	10元
     * 	蘋果		20元
     * 	餅乾		20元
     * 	南瓜派	50元
     * 	蛋糕		100元
     * 	金蘋果	1000元
     * 	附魔金蘋果	5000元
     *
     * 	雞蛋		5元
     * 	生雞肉	10元
     * 	生兔肉	10元
     * 	生豬肉	10元
     * 	生牛肉	20元
     * 	生羊肉	20元
     *
     * 	生鱈魚	10元
     * 	生鮭魚	10元
     * 	熱帶魚	10元
     * 	河豚		100元
     * 	鱈魚桶	200元
     * 	鮭魚桶	200元
     * 	熱帶魚桶	200元
     * 	河豚桶	500元
     *
     * 	煤炭		10元
     * 	鐵錠		50元
     * 	銅錠		50元
     * 	紅石		50元
     * 	石英		50元
     * 	金錠		100元
     * 	青晶石	200元
     * 	綠寶石	200元
     * 	鑽石		500元
     * 	獄髓錠	1000元
     */
    public static void putItemPrice(String itemName, int price){
        itemToMoney.put(minecraftItemPrefix + itemName, price);
    }
    public static void setItemPrice(){
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

    public static Map getItemPrice(){
        return itemToMoney;
    }

    public static boolean checkExist(String itemId){
        if(itemToMoney.containsKey(itemId)){
            return true;
        } else {
            return false;
        }
    }



}
