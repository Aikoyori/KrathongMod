package xyz.aikoyori.krathongmod.client.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class IncenseSmokeParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;
    private float rotateSpeed = 0f;
    double ogScale;
    Random rand = new Random();

    protected IncenseSmokeParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider spriteProvider) {
        super(clientWorld, d, e, f, g, h, i);
        this.spriteProvider = spriteProvider;
        this.velocityMultiplier = 1.01F;
        this.velocityX *= 0.015;
        this.velocityY *= 0.02;
        this.velocityZ *= 0.015;
        this.velocityX += velocityX;
        this.velocityY += velocityY;
        this.velocityZ += velocityZ;

        this.setSpriteForAge(this.spriteProvider);


        this.scale *= (float) (Math.random() * 0.8 + 1.7);
        ogScale = scale;
        int x = (int)(8.0 / (Math.random() * 0.2 + 0.2));
        this.maxAge = (int)Math.max((float)x * 2.5F, 20.0F);

        this.collidesWithWorld = true;
        this.alpha = 0.2f;
        this.angle = (float) (Math.random()*MathHelper.PI)- MathHelper.HALF_PI;
        this.rotateSpeed = (rand.nextBoolean()?-1:1)*(float) ((Math.random()*12.0f)+1.2f);
        this.scale *= rotateSpeed >= 0?1:-1;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(this.spriteProvider);
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.prevAngle = this.angle;
        //System.out.println(this.age);
;

        if (this.age >= this.maxAge || this.world.getFluidState(new BlockPos((int)x,(int)y,(int)z)).getFluid().matchesType(Fluids.LAVA)) {
            this.scale = 0;
            this.markDead();
        }else {
            this.velocityY *= 1.02;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            if (this.collidesWithWorld && this.y == this.prevPosY) {
                this.velocityX *= .7;
                this.velocityZ *= .7;
            }

            this.velocityX *= (double)this.velocityMultiplier;
            this.velocityY *= (double)this.velocityMultiplier;
            this.velocityZ *= (double)this.velocityMultiplier;
            if (this.onGround) {
                this.velocityX *= 0.699999988079071;
                this.velocityZ *= 0.699999988079071;
            }
        }

        if (!this.dead) {

            if(age/((float)(maxAge))>7/8.0)
            {
                this.scale = (float) (scale*((maxAge-age)/(maxAge*1f)*8));
                //this.scale = this.scale*0.8f;
            }
            else{
                this.alpha = MathHelper.lerp((float) age /maxAge,0.2f,1f);
                this.scale *= 1.02;
            }
            this.red = MathHelper.lerp((float) age /maxAge,1f,0.5f);
            this.green = MathHelper.lerp((float) age /maxAge,1f,0.5f);
            this.blue = MathHelper.lerp((float) age /maxAge,1f,0.5f);
            this.scale = Math.copySign(scale,rotateSpeed);
            if(!this.onGround) this.angle += ((float) (rotateSpeed* MathHelper.PI/360.0f));
            //if(!this.onGround) velocityY-=(0.001f*(1.0f/new Vec3d(this.velocityX,this.velocityY,this.velocityZ).length()));
            this.move(this.velocityX, this.velocityY, this.velocityZ);
        }

    }


    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }
    @Environment(EnvType.CLIENT)
    public static class IncenseSmokeFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public IncenseSmokeFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new IncenseSmokeParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
