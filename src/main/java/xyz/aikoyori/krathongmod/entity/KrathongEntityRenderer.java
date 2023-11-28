package xyz.aikoyori.krathongmod.entity;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import xyz.aikoyori.krathongmod.KrathongMod;
import xyz.aikoyori.krathongmod.client.KrathongModClient;

public class KrathongEntityRenderer extends EntityRenderer<KrathongEntity> {

    protected KrathongEntityModel model;

    BlockRenderManager brm;
    public KrathongEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        model =  new KrathongEntityModel(ctx.getPart(KrathongModClient.MODEL_CUBE_LAYER));
        brm = ctx.getBlockRenderManager();
    }

    @Override
    public void render(KrathongEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        matrices.push();
        matrices.translate(0,0.25,0);
        if(entity.hasCustomName())
            renderLabelIfPresent(entity,entity.getName(),matrices,vertexConsumers,light);
        matrices.pop();
        matrices.push();
        matrices.scale(0.5f,0.5f,0.5f);
        matrices.translate(-6/16f,4/16f,-11/16f);
        brm.renderBlockAsEntity(Block.getBlockFromItem(entity.getDataTracker().get(KrathongEntity.FLOWER_ITEM).getItem()).getDefaultState(),matrices,vertexConsumers,light,OverlayTexture.DEFAULT_UV);
        matrices.pop();
        matrices.push();
        matrices.translate(0.0F, 1.5F, 0.0F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getYaw()));
        matrices.scale(-1.0F, -1.0F, 1.0F);
        this.model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(getTexture(entity))), light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, entity.isInvisibleTo(minecraftClient.player) ? 0.15F : 1.0F);


        matrices.pop();
    }

    @Override
    public Identifier getTexture(KrathongEntity entity) {

        return entity.getDataTracker().get(KrathongEntity.LIT)?
                KrathongMod.makeID("textures/entity/krathong_lit.png"):
                KrathongMod.makeID("textures/entity/krathong.png");
    }
}
