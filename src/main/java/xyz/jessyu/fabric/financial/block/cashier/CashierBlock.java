package xyz.jessyu.fabric.financial.block.cashier;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class CashierBlock extends BlockWithEntity {
    private String OWNER = "financial.cashier_owner";
    public CashierBlock(Settings settings){
        super(settings.of(Material.METAL).nonOpaque());
    }

    /**
     * Create teh block's entity while block created
     * */
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state){
        return new CashierBlockEntity(pos, state);
    }

    /**
     * If right-click the block
     * */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.SUCCESS;
    }


    /**
     * BlockWithEntity default set this to invisible, so we need to set this to visible
     * */
    @Override
    public BlockRenderType getRenderType(BlockState state){
        return BlockRenderType.MODEL;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        CashierBlockEntity blockEntity = (CashierBlockEntity) world.getBlockEntity(pos);
        if(blockEntity.owner == null){
            blockEntity.owner = placer.getUuid();
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
