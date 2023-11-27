package xyz.aikoyori.krathongmod.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.aikoyori.krathongmod.KrathongMod;
import xyz.aikoyori.krathongmod.entity.KrathongEntity;

import java.util.Objects;

public class KrathongItem extends Item {
    public KrathongItem(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        KrathongEntity kt = new KrathongEntity(KrathongMod.KRATHONG_TYPE,context.getWorld());
        Vec3d pos = context.getBlockPos().add(context.getSide().getVector()).toCenterPos();
        kt.setPos(pos.getX(), pos.getY(),pos.getZ());
        kt.getDataTracker().set(KrathongEntity.LIT,context.getStack().getOrCreateNbt().getBoolean("lit"));
        context.getWorld().spawnEntity(kt);
        Objects.requireNonNull(context.getPlayer()).getStackInHand(context.getHand()).decrement(1);
        return ActionResult.SUCCESS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Hand opposite = hand==Hand.MAIN_HAND?Hand.OFF_HAND:Hand.MAIN_HAND;
        ItemStack samestack = user.getStackInHand(hand);
        ItemStack stack = user.getStackInHand(opposite);
        if(stack.getItem() == Items.FLINT_AND_STEEL)
        {
            samestack.getOrCreateNbt().putBoolean("lit",true);
            if(!world.isClient)
            {
            if(!user.isCreative()) stack.damage(1, world.random, (ServerPlayerEntity) user);
            }
            samestack.getOrCreateNbt().putBoolean("lit",true);
        }
        else if(stack.getItem() == Items.FIRE_CHARGE)
        {
                samestack.getOrCreateNbt().putBoolean("lit",true);
            stack.decrement(1);
            return TypedActionResult.success(samestack);
        } if(stack.getItem() == Items.WATER_BUCKET)
        {
            samestack.getOrCreateNbt().putBoolean("lit",false);
            if(!user.isCreative())
                user.setStackInHand(opposite,Items.BUCKET.getDefaultStack());
            return TypedActionResult.success(samestack);
        }
        return super.use(world,user,hand);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return super.getTranslationKey(stack) + (stack.getOrCreateNbt().getBoolean("lit")?".lit":"");
    }
}
