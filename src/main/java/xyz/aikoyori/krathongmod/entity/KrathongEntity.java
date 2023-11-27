package xyz.aikoyori.krathongmod.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.aikoyori.krathongmod.KrathongMod;

public class KrathongEntity extends Entity {

    private boolean isInWater = false;
    private double waterLevel;

    public Vec3d velocityRandomer = Vec3d.ZERO;
    private int tickToNextVelocity = 0;




    public static final TrackedData<Boolean> LIT = DataTracker.registerData(KrathongEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Integer> TICK_ALIVE = DataTracker.registerData(KrathongEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<Integer> TICK_INWATER = DataTracker.registerData(KrathongEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<ItemStack> FLOWER_ITEM = DataTracker.registerData(KrathongEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    @Override
    public boolean isCollidable() {
        return false;
    }
    void randomizeVelocity()
    {
        double xran,zran;
        xran = (this.random.nextDouble())-0.5;
        zran = (this.random.nextDouble())-0.5;
        xran += Math.copySign(0.5,xran);
        zran += Math.copySign(0.5,zran);
        velocityRandomer = new Vec3d((xran)*0.02d,0,(zran)*0.02d);
    }
    public KrathongEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
        randomizeVelocity();
    }
    @Override
    protected void initDataTracker() {
        dataTracker.startTracking(LIT,false);
        dataTracker.startTracking(TICK_ALIVE,0);
        dataTracker.startTracking(TICK_INWATER,0);
        dataTracker.startTracking(FLOWER_ITEM, Items.DANDELION.getDefaultStack());

    }

    @Override
    protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.65f;
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        dataTracker.set(LIT,nbt.getBoolean("lit"));
        dataTracker.set(TICK_ALIVE,nbt.getInt("tick_alive"));
        dataTracker.set(TICK_INWATER,nbt.getInt("tick_in_water"));
        dataTracker.set(FLOWER_ITEM,ItemStack.fromNbt(nbt.getCompound("flower")));
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putBoolean("lit",dataTracker.get(LIT));
        nbt.putInt("tick_alive",dataTracker.get(TICK_ALIVE));
        nbt.putInt("tick_in_water",dataTracker.get(TICK_ALIVE));
        nbt.put("flower",dataTracker.get(FLOWER_ITEM).writeNbt(new NbtCompound()));

    }

    @Override
    public boolean canHit() {
        return !this.isRemoved();
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if(stack.getItem() == Items.FLINT_AND_STEEL)
        {
            dataTracker.set(LIT,true);
            if(!getWorld().isClient)
            {
                if(!player.isCreative()) stack.damage(1,random, (ServerPlayerEntity) player);
            }
            return ActionResult.SUCCESS;
        }
        else if(stack.getItem() == Items.FIRE_CHARGE)
        {
            dataTracker.set(LIT,true);
            stack.decrement(1);
            return ActionResult.SUCCESS;
        }
        else if(stack.getItem() == Items.WATER_BUCKET)
        {
            dataTracker.set(LIT,false);
            if(!player.isCreative())
                player.setStackInHand(hand,Items.BUCKET.getDefaultStack());

            return ActionResult.SUCCESS;
        }
        else if(player.getMainHandStack().getItem() == Items.AIR)
        {
            dataTracker.set(LIT,false);
            return ActionResult.SUCCESS;
        }
        return super.interact(player, hand);
    }

    @Override
    public void tick() {
        super.tick();
        this.setVelocity(this.getVelocity().multiply(0.95));
        this.isInWater = checkKrathongInWater();
        dataTracker.set(TICK_ALIVE,dataTracker.get(TICK_ALIVE)+1);
        tickToNextVelocity = random.nextBetween(5,600);
        if(dataTracker.get(TICK_ALIVE)%tickToNextVelocity==0 || dataTracker.get(TICK_INWATER)==1)
        {
            randomizeVelocity();

        }
        else{

        }
        if(isInWater)
        {
            this.addVelocity(0,0.02,0);
            this.addVelocity(velocityRandomer);
            dataTracker.set(TICK_INWATER,dataTracker.get(TICK_INWATER)+1);



        }
        else{

            dataTracker.set(TICK_INWATER,0,true);
            this.addVelocity(new Vec3d(0,-0.03,0));
        }
        if(this.isSubmergedInWater())
        {
            dataTracker.set(LIT,false);
        }
        this.move(MovementType.SELF, this.getVelocity());
        this.velocityDirty = true;
        //updateVelocity();
        if(dataTracker.get(LIT))
        {

            this.getWorld().addParticle(ParticleTypes.FLAME,true,this.getX()+(0.4848/16f),this.getY()+(9/16f),this.getZ()+(1.5151/16f),0,0,0);
            this.getWorld().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,true,this.getX()+(-1/16f),this.getY()+(13/16f),this.getZ()+(0.5/16f),0,0.1,0);
            this.getWorld().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,true,this.getX()+(-2.5/16f),this.getY()+(13/16f),this.getZ()+(-0.5/16f),0,0.1,0);
            this.getWorld().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,true,this.getX()+(-1.25/16f),this.getY()+(12.25/16f),this.getZ()+(-2/16f),0,0.1,0);
        }
        //this.setYaw(this.getYaw()+(float) (this.getVelocity().lengthSquared()*300f));
        updatePositionAndAngles(this.getX(),this.getY(),this.getZ(),this.getYaw(),this.getPitch());

    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        boolean playerhuh = source.getAttacker() instanceof PlayerEntity;
        if(playerhuh)
        {
            if(!source.isSourceCreativePlayer())
            {
                dropStack(KrathongMod.KRATHONG_ITEM.getDefaultStack());
            }
            this.discard();

        }
        return playerhuh;
    }

    private void updateVelocity() {
        double d = -0.03999999910593033;
        double e = this.hasNoGravity() ? 0.0 : -0.03999999910593033;
        double f = 0.0;
        if (this.isInWater) {
            this.setVelocity(this.getVelocity().multiply(1.0, 1.0, 1.0));
        } else {
            this.setVelocity(this.getVelocity().multiply(1.0, -0.98, 1.0));
        }

    }
    public boolean isPushable() {
        return true;
    }
    private boolean checkKrathongInWater() {
        Box box = this.getBoundingBox();
        int i = MathHelper.floor(box.minX);
        int j = MathHelper.ceil(box.maxX);
        int k = MathHelper.floor(box.minY);
        int l = MathHelper.ceil(box.minY + 0.001);
        int m = MathHelper.floor(box.minZ);
        int n = MathHelper.ceil(box.maxZ);
        boolean bl = false;
        this.waterLevel = -1.7976931348623157E308;
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int o = i; o < j; ++o) {
            for(int p = k; p < l; ++p) {
                for(int q = m; q < n; ++q) {
                    mutable.set(o, p, q);
                    FluidState fluidState = this.getWorld().getFluidState(mutable);
                    if (fluidState.isIn(FluidTags.WATER)) {
                        float f = (float)p + fluidState.getHeight(this.getWorld(), mutable);
                        this.waterLevel = Math.max((double)f, this.waterLevel);
                        bl |= box.minY < (double)f;
                    }
                }
            }
        }

        return bl;
    }


}
