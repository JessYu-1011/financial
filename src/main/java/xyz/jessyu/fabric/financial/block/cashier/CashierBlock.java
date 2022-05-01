package xyz.jessyu.fabric.financial.block.cashier;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CashierBlock extends BlockWithEntity {
    public CashierBlock(Settings settings){
        super(settings.of(Material.METAL).nonOpaque());
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.0f, 1.0f);
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.SUCCESS;
    }


}
