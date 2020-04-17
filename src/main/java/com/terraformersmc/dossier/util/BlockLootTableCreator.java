package com.terraformersmc.dossier.util;

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

public class BlockLootTableCreator {
	public static <T> T addExplosionDecayCondition(ItemConvertible item, LootFunctionConsumingBuilder<T> functionBuilder) {
		return BlockLootTableGenerator.addExplosionDecayLootFunction(item, functionBuilder);
	}

	public static <T> T addSurvivesExplosionCondition(ItemConvertible item, LootConditionConsumingBuilder<T> lootConditionConsumingBuilder) {
		return BlockLootTableGenerator.addSurvivesExplosionLootCondition(item, lootConditionConsumingBuilder);
	}

	public static LootTable.Builder drops(ItemConvertible item) {
		return BlockLootTableGenerator.create(item);
	}

	public static LootTable.Builder drops(Block block, LootCondition.Builder conditionBuilder, LootEntry.Builder<?> child) {
		return BlockLootTableGenerator.create(block, conditionBuilder, child);
	}

	public static LootTable.Builder dropsWithSilkTouch(Block block, LootEntry.Builder<?> child) {
		return BlockLootTableGenerator.createForNeedingSilkTouch(block, child);
	}

	public static LootTable.Builder dropsWithShears(Block block, LootEntry.Builder<?> child) {
		return BlockLootTableGenerator.createForNeedingShears(block, child);
	}

	public static LootTable.Builder dropsWithSilkTouchShears(Block block, LootEntry.Builder<?> child) {
		return BlockLootTableGenerator.createForNeedingSilkTouchShears(block, child);
	}

	public static LootTable.Builder drops(Block block, ItemConvertible lootWithoutSilkTouch) {
		return BlockLootTableGenerator.createForBlockWithItemDrops(block, lootWithoutSilkTouch);
	}

	public static LootTable.Builder drops(ItemConvertible item, LootTableRange count) {
		return BlockLootTableGenerator.create(item, count);
	}

	public static LootTable.Builder drops(Block block, ItemConvertible lootWithoutSilkTouch, LootTableRange count) {
		return BlockLootTableGenerator.createForBlockWithItemDrops(block, lootWithoutSilkTouch, count);
	}

	public static LootTable.Builder dropsWithSilkTouch(ItemConvertible item) {
		return BlockLootTableGenerator.createForNeedingSilkTouch(item);
	}

	public static LootTable.Builder pottedPlantDrops(ItemConvertible item) {
		return BlockLootTableGenerator.createForPottedPlant(item);
	}

	public static LootTable.Builder slabsDrops(Block block) {
		return BlockLootTableGenerator.createForSlabs(block);
	}

	public static <T extends Comparable<T> & StringIdentifiable> LootTable.Builder dropsWithCertainProperty(Block block, Property<T> property, T comparable) {
		return BlockLootTableGenerator.createForMultiblock(block, property, comparable);
	}

	public static LootTable.Builder nameableContainerDrops(Block block) {
		return BlockLootTableGenerator.createForNameableContainer(block);
	}

	public static LootTable.Builder oreDrops(Block block, Item item) {
		return BlockLootTableGenerator.createForOreWithSingleItemDrop(block, item);
	}

	public static LootTable.Builder tallGrassDrops(Block block) {
		return BlockLootTableGenerator.createForTallGrass(block);
	}

	public static LootTable.Builder cropStemDrops(Block block, Item seeds) {
		return BlockLootTableGenerator.createForCropStem(block, seeds);
	}

	public static LootTable.Builder attachedCropStemDrops(Block block, Item seeds) {
		return BlockLootTableGenerator.createForAttachedCropStem(block, seeds);
	}

	public static LootTable.Builder dropsWithShears(ItemConvertible item) {
		return BlockLootTableGenerator.createForBlockNeedingShears(item);
	}

	public static LootTable.Builder leavesDrops(Block leafBlock, Block sapling, float... saplingDropChances) {
		return BlockLootTableGenerator.createForLeaves(leafBlock, sapling, saplingDropChances);
	}

	public static LootTable.Builder cropsDrops(Block block, Item food, Item seeds, LootCondition.Builder condition) {
		return BlockLootTableGenerator.createForCrops(block, food, seeds, condition);
	}

	public static LootTable.Builder dropsNothing() {
		return BlockLootTableGenerator.createEmpty();
	}
}
