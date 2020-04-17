package com.terraformersmc.dossier.generator;

import com.mojang.datafixers.util.Pair;
import com.terraformersmc.dossier.provider.DossierLootTablesProvider;
import com.terraformersmc.dossier.util.BlockLootTableCreator;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Function;

public abstract class LootTablesDossier extends DossierGenerator<DossierLootTablesProvider, Runnable> {

	@Override
	protected void setProvider(String modId) {
		this.provider = (DossierLootTablesProvider) DossierGenerator.getProvider(modId, DossierType.LOOT_TABLES);
	}

	protected abstract void addLootTables();

	protected void drops(Block block, ItemConvertible loot) {
		this.drops(block, BlockLootTableCreator.drops(loot));
	}

	protected void drops(Block block) {
		this.drops(block, block);
	}

	protected void drops(Block block, LootTable.Builder builder) {
		List<Pair<Identifier, LootTable.Builder>> lootTables = this.provider.lootTables.get(LootContextTypes.BLOCK);
		lootTables.add(new Pair<>(block.getLootTableId(), builder));
		this.provider.lootTables.put(LootContextTypes.BLOCK, lootTables);
	}

	protected void pottedPlantDrops(Block block) {
		this.drops(block, (blockx) -> BlockLootTableCreator.pottedPlantDrops(((FlowerPotBlock) blockx).getContent()));
	}

	protected void drops(Block block, Function<Block, LootTable.Builder> function) {
		this.drops(block, function.apply(block));
	}


	@Override
	public Runnable getCustomFunction() {
		return this::addLootTables;
	}
}
