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
    public Coin(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand){
            if(!player.getStackInHand(hand).hasNbt()){
                NbtCompound nbt = new NbtCompound();
                nbt.putInt("financial.balance", 11);
                player.getStackInHand(hand).setNbt(nbt.copy());
            }
            return super.use(world, player, hand);
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()) {
            int currentBalance = stack.getNbt().getInt("financial.balance");
            String currentB = String.valueOf(currentBalance);
            tooltip.add(new LiteralText(currentB));
        }
    }

}
