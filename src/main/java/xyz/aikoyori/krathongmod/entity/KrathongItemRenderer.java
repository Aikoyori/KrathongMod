package xyz.aikoyori.krathongmod.entity;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import xyz.aikoyori.krathongmod.KrathongMod;

public class KrathongItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {


    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        KrathongEntity kt = new KrathongEntity(KrathongMod.KRATHONG_TYPE, MinecraftClient.getInstance().world);
        kt.getDataTracker().set(KrathongEntity.LIT,stack.getOrCreateNbt().getBoolean("lit"));
        kt.getDataTracker().set(KrathongEntity.FLOWER_ITEM,ItemStack.fromNbt(stack.getOrCreateNbt().getCompound("flower_item")));
        entityRenderDispatcher.render(kt, 0.0d, 0.0d, 0.0d, 0.0F, 1.0F, matrices, vertexConsumers, light);
    }
}
