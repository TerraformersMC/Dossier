package com.terraformersmc.dossier;

import com.terraformersmc.dossier.generator.BlockTagsDossier;
import com.terraformersmc.dossier.generator.ItemTagsDossier;
import com.terraformersmc.dossier.generator.LootTablesDossier;
import com.terraformersmc.dossier.generator.RecipesDossier;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class Dossiers {
	public List<Supplier<ItemTagsDossier>> itemTagsDossiers = new LinkedList<>();
	public List<Supplier<BlockTagsDossier>> blockTagsDossiers = new LinkedList<>();
	public List<Supplier<RecipesDossier>> recipesDossiers = new LinkedList<>();
	public List<Supplier<LootTablesDossier>> lootTablesDossiers = new LinkedList<>();

	private Dossiers() {
	}

	public static Dossiers builder() {
		return new Dossiers();
	}

	public Dossiers addItemTags(Supplier<ItemTagsDossier> dossier) {
		this.itemTagsDossiers.add(dossier);
		return this;
	}

	public Dossiers addBlockTags(Supplier<BlockTagsDossier> dossier) {
		this.blockTagsDossiers.add(dossier);
		return this;
	}

	public Dossiers addRecipes(Supplier<RecipesDossier> dossier) {
		this.recipesDossiers.add(dossier);
		return this;
	}

	public Dossiers addLootTables(Supplier<LootTablesDossier> dossier) {
		this.lootTablesDossiers.add(dossier);
		return this;
	}
}
