package com.terraformersmc.dossier.mixin;

import net.minecraft.block.Block;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableRange;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionConsumingBuilder;
import net.minecraft.loot.entry.LootEntry;
import net.minecraft.loot.function.LootFunctionConsumingBuilder;
import net.minecraft.state.property.Property;
import net.minecraft.util.StringIdentifiable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockLootTableGenerator.class)
public interface BlockLootTableCreator {
	@Invoker("addExplosionDecayLootFunction")
	static <T> T addExplosionDecayCondition(ItemConvertible item, LootFunctionConsumingBuilder<T> functionBuilder) {
		return null;
	}

	@Invoker("addSurvivesExplosionLootCondition")
	static <T> T addSurvivesExplosionCondition(ItemConvertible item, LootConditionConsumingBuilder<T> lootConditionConsumingBuilder) {
		return null;
	}

	@Invoker("create")
	static LootTable.Builder drops(ItemConvertible item) {
		return null;
	}

	@Invoker("create")
	static LootTable.Builder drops(Block block, LootCondition.Builder conditionBuilder, LootEntry.Builder<?> child) {
		return null;
	}

	@Invoker("createForNeedingSilkTouch")
	static LootTable.Builder dropsWithSilkTouch(Block block, LootEntry.Builder<?> child) {
		return null;
	}

	@Invoker("createForNeedingShears")
	static LootTable.Builder dropsWithShears(Block block, LootEntry.Builder<?> child) {
		return null;
	}

	@Invoker("createForNeedingSilkTouchShears")
	static LootTable.Builder dropsWithSilkTouchShears(Block block, LootEntry.Builder<?> child) {
		return null;
	}

	@Invoker("createForBlockWithItemDrops")
	static LootTable.Builder drops(Block block, ItemConvertible lootWithoutSilkTouch) {
		return null;
	}

	@Invoker("create")
	static LootTable.Builder drops(ItemConvertible item, LootTableRange count) {
		return null;
	}

	@Invoker("createForBlockWithItemDrops")
	static LootTable.Builder drops(Block block, ItemConvertible lootWithoutSilkTouch, LootTableRange count) {
		return null;
	}

	@Invoker("createForNeedingSilkTouch")
	static LootTable.Builder dropsWithSilkTouch(ItemConvertible item) {
		return null;
	}

	@Invoker("createForPottedPlant")
	static LootTable.Builder pottedPlantDrops(ItemConvertible item) {
		return null;
	}

	@Invoker("createForSlabs")
	static LootTable.Builder slabsDrops(Block block) {
		return null;
	}

	@Invoker("createForMultiblock")
	static <T extends Comparable<T> & StringIdentifiable> LootTable.Builder dropsWithCertainProperty(Block block, Property<T> property, T comparable) {
		return null;
	}

	@Invoker("createForNameableContainer")
	static LootTable.Builder nameableContainerDrops(Block block) {
		return null;
	}

	@Invoker("createForOreWithSingleItemDrop")
	static LootTable.Builder oreDrops(Block block, Item item) {
		return null;
	}

	@Invoker("createForTallGrass")
	static LootTable.Builder tallGrassDrops(Block block) {
		return null;
	}

	@Invoker("createForCropStem")
	static LootTable.Builder cropStemDrops(Block block, Item seeds) {
		return null;
	}

	@Invoker("createForAttachedCropStem")
	static LootTable.Builder attachedCropStemDrops(Block block, Item seeds) {
		return null;
	}

	@Invoker("createForBlockNeedingShears")
	static LootTable.Builder dropsWithShears(ItemConvertible item) {
		return null;
	}

	@Invoker("createForLeaves")
	static LootTable.Builder leavesDrops(Block leafBlock, Block sapling, float... saplingDropChances) {
		return null;
	}

	@Invoker("createForCrops")
	static LootTable.Builder cropsDrops(Block block, Item food, Item seeds, LootCondition.Builder condition) {
		return null;
	}

	@Invoker("createEmpty")
	static LootTable.Builder dropsNothing() {
		return null;
	}
}
