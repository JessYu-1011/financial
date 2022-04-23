package xyz.jessyu.fabric.financial.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
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
            nbt.putInt("balance", 0);
            player.getStackInHand(hand).setNbt(nbt);

        }
        return super.use(world, player, hand);
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String currentBalance = stack.getNbt().getString("balance");
        tooltip.add(new LiteralText(currentBalance));
    }

}
