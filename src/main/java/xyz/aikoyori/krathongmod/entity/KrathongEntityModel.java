package xyz.aikoyori.krathongmod.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class KrathongEntityModel extends EntityModel<Entity> {
	private final ModelPart bases;
	private final ModelPart leaves;
	private final ModelPart leaves2;
	private final ModelPart toppings;
	public KrathongEntityModel(ModelPart root) {
		this.bases = root.getChild("bases");
		this.leaves = root.getChild("leaves");
		this.leaves2 = root.getChild("leaves2");
		this.toppings = root.getChild("toppings");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bases = modelPartData.addChild("bases", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData base_r1 = bases.addChild("base_r1", ModelPartBuilder.create().uv(16, 0).cuboid(-2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(18, 18).cuboid(-2.0F, -1.0F, -3.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData base_r2 = bases.addChild("base_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-3.75F, -1.0F, -2.0F, 6.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.75F, 0.0F, -1.5708F, 0.0F));

		ModelPartData leaves = modelPartData.addChild("leaves", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, 21.0F, 0.0F));

		ModelPartData leaflet_r1 = leaves.addChild("leaflet_r1", ModelPartBuilder.create().uv(18, 18).cuboid(-3.1173F, -2.1533F, -0.8467F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.5F, 0.0F, 0.4199F, 0.7119F, -0.5299F));

		ModelPartData leaflet_r2 = leaves.addChild("leaflet_r2", ModelPartBuilder.create().uv(8, 18).cuboid(-3.1173F, -2.1533F, -0.8467F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.5F, 0.0F, -2.7217F, -0.7119F, -2.6117F));

		ModelPartData leaflet_r3 = leaves.addChild("leaflet_r3", ModelPartBuilder.create().uv(8, 15).cuboid(-3.1173F, -2.1533F, -0.8467F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.5F, 0.0F, -1.9907F, 0.7119F, -2.6117F));

		ModelPartData leaflet_r4 = leaves.addChild("leaflet_r4", ModelPartBuilder.create().uv(8, 12).cuboid(-3.1173F, -2.1533F, -0.8467F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.5F, 0.0F, 1.1509F, -0.7119F, -0.5299F));

		ModelPartData leaflet_r5 = leaves.addChild("leaflet_r5", ModelPartBuilder.create().uv(21, 3).cuboid(-3.1173F, -2.1533F, -0.8467F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.5F, 0.0F, -0.7854F, 1.1781F, -1.5708F));

		ModelPartData leaflet_r6 = leaves.addChild("leaflet_r6", ModelPartBuilder.create().uv(20, 0).cuboid(-3.1173F, -2.1533F, -0.8467F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.5F, 0.0F, 2.3562F, -1.1781F, -1.5708F));

		ModelPartData leaflet_r7 = leaves.addChild("leaflet_r7", ModelPartBuilder.create().uv(21, 6).cuboid(-3.1173F, -2.1533F, -0.8467F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.5F, 0.0F, -2.3562F, 0.0F, -2.7489F));

		ModelPartData leaflet_r8 = leaves.addChild("leaflet_r8", ModelPartBuilder.create().uv(8, 21).cuboid(-3.1173F, -2.1533F, -0.8467F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 0.5F, 0.0F, 0.7854F, 0.0F, -0.3927F));

		ModelPartData leaves2 = modelPartData.addChild("leaves2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = leaves2.addChild("cube_r1", ModelPartBuilder.create().uv(6, 9).cuboid(1.0289F, 1.1276F, 1.0289F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, -2.931F, -0.0402F, -2.9271F));

		ModelPartData cube_r2 = leaves2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 9).cuboid(-4.0289F, 1.1276F, 1.0289F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, 1.7609F, 1.3564F, 1.5422F));

		ModelPartData cube_r3 = leaves2.addChild("cube_r3", ModelPartBuilder.create().uv(6, 6).cuboid(-4.0289F, 1.1276F, 1.0289F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, 0.2143F, -0.0231F, 0.2143F));

		ModelPartData cube_r4 = leaves2.addChild("cube_r4", ModelPartBuilder.create().uv(0, 6).cuboid(1.0289F, 1.1276F, 1.0289F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, -1.4624F, 1.3552F, -1.6792F));

		ModelPartData cube_r5 = leaves2.addChild("cube_r5", ModelPartBuilder.create().uv(6, 12).cuboid(1.0289F, 1.1276F, 1.0289F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, -2.7289F, 0.7234F, -2.854F));

		ModelPartData cube_r6 = leaves2.addChild("cube_r6", ModelPartBuilder.create().uv(0, 12).cuboid(-4.0289F, 1.1276F, 1.0289F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, 0.4264F, 0.7568F, 0.2968F));

		ModelPartData cube_r7 = leaves2.addChild("cube_r7", ModelPartBuilder.create().uv(12, 6).cuboid(-4.0289F, 1.1276F, 1.0289F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, 0.0F, -0.7854F, 0.3054F));

		ModelPartData cube_r8 = leaves2.addChild("cube_r8", ModelPartBuilder.create().uv(12, 9).cuboid(1.0289F, 1.1276F, 1.0289F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 0.0F, 0.0F, 0.7854F, -0.3054F));

		ModelPartData toppings = modelPartData.addChild("toppings", ModelPartBuilder.create().uv(4, 15).cuboid(2.0F, -12.0F, -1.05F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 14).cuboid(0.5F, -12.0F, -0.05F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 14).cuboid(0.75F, -11.75F, -2.55F, 1.0F, 10.0F, 1.0F, new Dilation(0.0F))
		.uv(18, 12).cuboid(-2.0F, -6.0F, 0.5F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData wick_r1 = toppings.addChild("wick_r1", ModelPartBuilder.create().uv(0, 3).cuboid(-0.5F, -0.375F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.9848F, -6.625F, 1.5152F, 0.0F, 0.7854F, 0.0F));

		ModelPartData wick_r2 = toppings.addChild("wick_r2", ModelPartBuilder.create().uv(2, 3).cuboid(-0.125F, -0.875F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.25F, -6.125F, 1.25F, 0.0F, -0.7854F, 0.0F));

		ModelPartData flower = toppings.addChild("flower", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -4.0F, -1.75F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData floor_r1 = flower.addChild("floor_r1", ModelPartBuilder.create().uv(7, 27).cuboid(-2.5F, 0.175F, -2.5F, 5.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.25F, 0.0F, 0.0F, 0.7854F, 0.0F));

		ModelPartData floorflower = flower.addChild("floorflower", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.25F, 0.0F));

		ModelPartData floorflower_r1 = floorflower.addChild("floorflower_r1", ModelPartBuilder.create().uv(17, 27).cuboid(-2.5F, -0.025F, -2.5F, 5.0F, 0.0F, 5.0F, new Dilation(0.0F))
		.uv(17, 27).cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new Dilation(0.0F))
		.uv(17, 27).cuboid(-2.5F, 0.025F, -2.5F, 5.0F, 0.0F, 5.0F, new Dilation(0.0F))
		.uv(17, 27).cuboid(-2.5F, 0.05F, -2.5F, 5.0F, 0.0F, 5.0F, new Dilation(0.0F))
		.uv(17, 27).cuboid(-2.5F, 0.075F, -2.5F, 5.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bases.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		leaves.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		leaves2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		toppings.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}