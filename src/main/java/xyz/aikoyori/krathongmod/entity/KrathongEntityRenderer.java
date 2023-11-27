package xyz.aikoyori.krathongmod.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import xyz.aikoyori.krathongmod.KrathongMod;
import xyz.aikoyori.krathongmod.client.KrathongModClient;

public class KrathongEntityRenderer extends EntityRenderer<KrathongEntity> {

    protected KrathongEntityModel model;
    public KrathongEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        model =  new KrathongEntityModel(ctx.getPart(KrathongModClient.MODEL_CUBE_LAYER));
    }

    @Override
    public void render(KrathongEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
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
