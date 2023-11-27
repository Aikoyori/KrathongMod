package xyz.aikoyori.krathongmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import xyz.aikoyori.krathongmod.KrathongMod;
import xyz.aikoyori.krathongmod.entity.KrathongEntityModel;
import xyz.aikoyori.krathongmod.entity.KrathongEntityRenderer;
import xyz.aikoyori.krathongmod.entity.KrathongItemRenderer;

public class KrathongModClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(KrathongMod.makeID("krathong"), "main");
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(KrathongMod.KRATHONG_TYPE, KrathongEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, KrathongEntityModel::getTexturedModelData);

        BuiltinItemRendererRegistry.INSTANCE.register(KrathongMod.KRATHONG_ITEM, new KrathongItemRenderer());
    }
}
