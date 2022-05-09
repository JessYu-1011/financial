package xyz.jessyu.fabric.financial.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class Coin extends Item {

    private static String balance = "financial.balance";
    public Coin(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand){
            if(!player.getStackInHand(hand).hasNbt()){
                NbtCompound nbt = new NbtCompound();
                nbt.putInt(balance, 0);
                player.getStackInHand(hand).setNbt(nbt.copy());
            } else {
                // this part is for testing purpose
                modifyBalance(player.getStackInHand(hand), -10);
            }
            return super.use(world, player, hand);
    }


    /**
     * Append the amount of balance to tooltip
     * */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()) {
            int currentBalance = stack.getNbt().getInt(balance);
            String currentB = String.valueOf(currentBalance);
            tooltip.add(new LiteralText(currentB));
        }
    }


    /**
     *  Modify the balance amount of the stack
     *  This is a class level method, not need to be created a new instance when using.
     * */
    public static void modifyBalance(ItemStack stack, int offsetBalance){
        if(stack.hasNbt()){
            int currentBalance = stack.getNbt().getInt(balance);
            currentBalance -= offsetBalance;
            NbtCompound nbt = new NbtCompound();
            nbt.putInt(balance, currentBalance);
            stack.setNbt(nbt);
        }
    }

    public static int getBalance(ItemStack stack){
        return stack.getNbt().getInt(balance);
    }
}
