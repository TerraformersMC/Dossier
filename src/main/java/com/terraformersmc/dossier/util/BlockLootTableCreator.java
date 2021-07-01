package com.terraformersmc.dossier.util;

import net.minecraft.block.Block;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionConsumingBuilder;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.LootFunctionConsumingBuilder;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.state.property.Property;
import net.minecraft.util.StringIdentifiable;

public class BlockLootTableCreator {
	public static <T> T applyExplosionDecay(ItemConvertible item, LootFunctionConsumingBuilder<T> functionBuilder) {
		return BlockLootTableGenerator.applyExplosionDecay(item, functionBuilder);
	}

	public static <T> T addSurvivesExplosionCondition(ItemConvertible item, LootConditionConsumingBuilder<T> lootConditionConsumingBuilder) {
		return BlockLootTableGenerator.addSurvivesExplosionCondition(item, lootConditionConsumingBuilder);
	}

	public static LootTable.Builder drops(ItemConvertible item) {
		return BlockLootTableGenerator.drops(item);
	}

	public static LootTable.Builder drops(ItemConvertible item, LootCondition.Builder conditionBuilder, LootPoolEntry.Builder<?> child) {
		return drops(ItemEntry.builder(item), conditionBuilder, child);
	}

	public static LootTable.Builder drops(LootPoolEntry.Builder<?> entry, LootCondition.Builder conditionBuilder, LootPoolEntry.Builder<?> child) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.with(entry
								.conditionally(conditionBuilder)
								.alternatively(child)));
	}

	public static LootTable.Builder dropsWithSilkTouch(Block block, LootPoolEntry.Builder<?> child) {
		return BlockLootTableGenerator.dropsWithSilkTouch(block, child);
	}

	public static LootTable.Builder dropsWithShears(Block block, LootPoolEntry.Builder<?> child) {
		return BlockLootTableGenerator.dropsWithShears(block, child);
	}

	public static LootTable.Builder dropsWithSilkTouchOrShears(Block block, LootPoolEntry.Builder<?> child) {
		return BlockLootTableGenerator.dropsWithSilkTouchOrShears(block, child);
	}

	public static LootTable.Builder drops(Block block, ItemConvertible lootWithoutSilkTouch) {
		return BlockLootTableGenerator.drops(block, lootWithoutSilkTouch);
	}

	public static LootTable.Builder drops(ItemConvertible item, LootNumberProvider count) {
		return BlockLootTableGenerator.drops(item, count);
	}

	public static LootTable.Builder drops(Block block, ItemConvertible lootWithoutSilkTouch, LootNumberProvider count) {
		return BlockLootTableGenerator.drops(block, lootWithoutSilkTouch, count);
	}

	public static LootTable.Builder dropsWithSilkTouch(ItemConvertible item) {
		return BlockLootTableGenerator.dropsWithSilkTouch(item);
	}

	public static LootTable.Builder pottedPlantDrops(ItemConvertible item) {
		return BlockLootTableGenerator.pottedPlantDrops(item);
	}

	public static LootTable.Builder slabDrops(Block block) {
		return BlockLootTableGenerator.slabDrops(block);
	}

	public static <T extends Comparable<T> & StringIdentifiable> LootTable.Builder dropsWithProperty(Block block, Property<T> property, T comparable) {
		return BlockLootTableGenerator.dropsWithProperty(block, property, comparable);
	}

	public static LootTable.Builder nameableContainerDrops(Block block) {
		return BlockLootTableGenerator.nameableContainerDrops(block);
	}

	public static LootTable.Builder oreDrops(Block block, Item item) {
		return BlockLootTableGenerator.oreDrops(block, item);
	}

	public static LootTable.Builder grassDrops(Block block) {
		return BlockLootTableGenerator.grassDrops(block);
	}

	public static LootTable.Builder cropStemDrops(Block block, Item seeds) {
		return BlockLootTableGenerator.cropStemDrops(block, seeds);
	}

	public static LootTable.Builder attachedCropStemDrops(Block block, Item seeds) {
		return BlockLootTableGenerator.attachedCropStemDrops(block, seeds);
	}

	public static LootTable.Builder dropsWithShears(ItemConvertible item) {
		return BlockLootTableGenerator.dropsWithShears(item);
	}

	public static LootTable.Builder leavesDrops(Block leafBlock, Block sapling, float... saplingDropChances) {
		return BlockLootTableGenerator.leavesDrop(leafBlock, sapling, saplingDropChances);
	}

	public static LootTable.Builder cropsDrops(Block block, Item food, Item seeds, LootCondition.Builder condition) {
		return BlockLootTableGenerator.cropDrops(block, food, seeds, condition);
	}

	public static LootTable.Builder dropsNothing() {
		return BlockLootTableGenerator.dropsNothing();
	}
}
