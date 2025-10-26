package net.mcreator.unusualadventures.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.unusualadventures.procedures.ReturnNotAliveProcedure;
import net.mcreator.unusualadventures.procedures.ReturnIdleProcedure;
import net.mcreator.unusualadventures.procedures.ReturnCombatProcedure;
import net.mcreator.unusualadventures.procedures.GooeyNullOnEntityTickUpdateProcedure;
import net.mcreator.unusualadventures.procedures.GooeyNullNaturalEntitySpawningConditionProcedure;
import net.mcreator.unusualadventures.procedures.GooeyNullEmergeProcedure;
import net.mcreator.unusualadventures.procedures.GooeyNullDiveProcedure;
import net.mcreator.unusualadventures.init.UnusualAdventuresModEntities;
import net.mcreator.unusualadventures.init.UnusualAdventuresModBlocks;

public class GooeyNullEntity extends Monster {

	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(GooeyNullEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> DATA_animation = SynchedEntityData.defineId(GooeyNullEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_tp = SynchedEntityData.defineId(GooeyNullEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_x = SynchedEntityData.defineId(GooeyNullEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_y = SynchedEntityData.defineId(GooeyNullEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_z = SynchedEntityData.defineId(GooeyNullEntity.class, EntityDataSerializers.INT);
	public final AnimationState animationState1 = new AnimationState();
	public final AnimationState animationState2 = new AnimationState();
	public final AnimationState animationState3 = new AnimationState();
	public final AnimationState animationState4 = new AnimationState();
	public final AnimationState animationState5 = new AnimationState();

	public GooeyNullEntity(EntityType<GooeyNullEntity> type, Level world) {
		super(type, world);
		xpReward = 1;
		setNoAi(false);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(TEXTURE, "gooey_null");
		builder.define(DATA_animation, 0);
		builder.define(DATA_tp, 0);
		builder.define(DATA_x, 0);
		builder.define(DATA_y, 0);
		builder.define(DATA_z, 0);
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected boolean canPerformAttack(LivingEntity entity) {
				return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < 4 && this.mob.getSensing().hasLineOfSight(entity);
			}
		});
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, Player.class, false, false));
		this.goalSelector.addGoal(5, new TryFindWaterGoal(this));
	}

	protected void dropCustomDeathLoot(ServerLevel serverLevel, DamageSource source, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(serverLevel, source, recentlyHitIn);
		this.spawnAtLocation(new ItemStack(UnusualAdventuresModBlocks.NULL_BLOCK.get()));
	}

	@Override
	public SoundEvent getAmbientSound() {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.vault.ambient"));
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.breeze.whirl")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.slime.attack"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.death"));
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		if (damagesource.getDirectEntity() instanceof ThrownPotion || damagesource.getDirectEntity() instanceof AreaEffectCloud || damagesource.typeHolder().is(NeoForgeMod.POISON_DAMAGE))
			return false;
		if (damagesource.is(DamageTypes.FALL))
			return false;
		return super.hurt(damagesource, amount);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Texture", this.getTexture());
		compound.putInt("Dataanimation", this.entityData.get(DATA_animation));
		compound.putInt("Datatp", this.entityData.get(DATA_tp));
		compound.putInt("Datax", this.entityData.get(DATA_x));
		compound.putInt("Datay", this.entityData.get(DATA_y));
		compound.putInt("Dataz", this.entityData.get(DATA_z));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Texture"))
			this.setTexture(compound.getString("Texture"));
		if (compound.contains("Dataanimation"))
			this.entityData.set(DATA_animation, compound.getInt("Dataanimation"));
		if (compound.contains("Datatp"))
			this.entityData.set(DATA_tp, compound.getInt("Datatp"));
		if (compound.contains("Datax"))
			this.entityData.set(DATA_x, compound.getInt("Datax"));
		if (compound.contains("Datay"))
			this.entityData.set(DATA_y, compound.getInt("Datay"));
		if (compound.contains("Dataz"))
			this.entityData.set(DATA_z, compound.getInt("Dataz"));
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			this.animationState1.animateWhen(ReturnIdleProcedure.execute(this), this.tickCount);
			this.animationState2.animateWhen(ReturnCombatProcedure.execute(this), this.tickCount);
			this.animationState3.animateWhen(ReturnNotAliveProcedure.execute(this), this.tickCount);
			this.animationState4.animateWhen(GooeyNullEmergeProcedure.execute(this), this.tickCount);
			this.animationState5.animateWhen(GooeyNullDiveProcedure.execute(this), this.tickCount);
		}
	}

	@Override
	public void baseTick() {
		super.baseTick();
		GooeyNullOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
		event.register(UnusualAdventuresModEntities.GOOEY_NULL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) -> {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return GooeyNullNaturalEntitySpawningConditionProcedure.execute(world, x, y, z);
		}, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.15);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 8);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 64);
		builder = builder.add(Attributes.STEP_HEIGHT, 0);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 10);
		return builder;
	}
}