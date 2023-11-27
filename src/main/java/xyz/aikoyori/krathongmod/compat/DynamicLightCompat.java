package xyz.aikoyori.krathongmod.compat;

import dev.lambdaurora.lambdynlights.api.DynamicLightHandler;
import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;
import dev.lambdaurora.lambdynlights.api.item.ItemLightSource;
import dev.lambdaurora.lambdynlights.api.item.ItemLightSources;
import net.minecraft.item.ItemStack;
import xyz.aikoyori.krathongmod.KrathongMod;
import xyz.aikoyori.krathongmod.entity.KrathongEntity;

import static dev.lambdaurora.lambdynlights.api.DynamicLightHandlers.registerDynamicLightHandler;


public class DynamicLightCompat implements DynamicLightsInitializer {
    @Override
    public void onInitializeDynamicLights() {
        registerDynamicLightHandler(KrathongMod.KRATHONG_TYPE, entity -> {
            int luminance = 0;
            if (entity.getDataTracker().get(KrathongEntity.LIT))
                luminance = 15;
            return luminance;
        });
        ItemLightSources.registerItemLightSource(new ItemLightSource(KrathongMod.makeID("krathong"),KrathongMod.KRATHONG_ITEM,true) {
            @Override
            public int getLuminance(ItemStack itemStack) {
                return itemStack.getOrCreateNbt().getBoolean("lit")?15:0;
            }
        });
    }

}
