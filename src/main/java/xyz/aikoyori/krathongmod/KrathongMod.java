package xyz.aikoyori.krathongmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.aikoyori.krathongmod.entity.KrathongEntity;
import xyz.aikoyori.krathongmod.item.KrathongItem;

public class KrathongMod implements ModInitializer {
    public static final String MOD_ID = "krathongmod";
    public static final EntityType<KrathongEntity> KRATHONG_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            makeID("krathong"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, KrathongEntity::new).dimensions(EntityDimensions.fixed(0.4f,4/16f)).build());
    public static final Item KRATHONG_ITEM = Registry.register(
            Registries.ITEM,
            makeID("krathong"),
            new KrathongItem(new FabricItemSettings().maxCount(1))
    );
    public static final ItemGroup KRATHONG_ITEM_GROUP = FabricItemGroup.builder()
            .icon(()->new ItemStack(KRATHONG_ITEM))
            .displayName(Text.translatable("itemGroup.krathongmod.krathong"))
            .entries((displayContext, entries) -> {
                entries.add(KRATHONG_ITEM);
            })
            .build();
    public static final DefaultParticleType INCENSE_SMOKE = FabricParticleTypes.simple();
    @Override
    public void onInitialize() {
        Registry.register(Registries.PARTICLE_TYPE,makeID("incense_smoke"),INCENSE_SMOKE);
    }
    public static Identifier makeID(String string){
        return new Identifier(MOD_ID,string);
    }
}
