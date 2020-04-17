package com.terraformersmc.dossier.generator;

import com.google.common.collect.Maps;
import com.terraformersmc.dossier.Dossiers;
import com.terraformersmc.dossier.provider.DossierBlockTagsProvider;
import com.terraformersmc.dossier.provider.DossierItemTagsProvider;
import com.terraformersmc.dossier.provider.DossierLootTablesProvider;
import com.terraformersmc.dossier.provider.DossierRecipesProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class DossierGenerator<P extends DataProvider & Consumer<F>, F> {


	protected P provider;

	/**
	 * Create data from your Dossiers
	 *
	 * @param modId    Your mod's mod id.
	 * @param enabled  Must be true in order to run
	 * @param dossiers Your dossiers describing what data to generate
	 */
	public static void generateData(String modId, boolean enabled, Dossiers dossiers) {
		if (FabricLoader.getInstance().isDevelopmentEnvironment() && enabled) {
			dossiers.blockTagsDossiers.forEach(dossier -> dossier.get().run(modId));
			dossiers.itemTagsDossiers.forEach(dossier -> dossier.get().run(modId));
			dossiers.recipesDossiers.forEach(dossier -> dossier.get().run(modId));
			dossiers.lootTablesDossiers.forEach(dossier -> dossier.get().run(modId));
			try {
				DATA_GENERATORS.get(modId).run();
			} catch (IOException | NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private static final Map<String, EnumMap<DossierType, DataProvider>> DATA_PROVIDERS = new HashMap<>();
	private static final Map<String, DataGenerator> DATA_GENERATORS = new HashMap<>();

	protected void run(String modId) {
		this.setProvider(modId);
		this.getProvider().accept(this.getCustomFunction());
	}

	protected abstract void setProvider(String modId);

	public P getProvider() {
		return this.provider;
	}

	public abstract F getCustomFunction();

	public static void reloadProviders(String modId) {
		Path output = Paths.get("dossier_generated/" + modId);
		DataGenerator generator = new DataGenerator(output, Collections.emptyList());
		EnumMap<DossierType, DataProvider> providers = Maps.newEnumMap(DossierType.class);

		DossierBlockTagsProvider blockTagsProvider = new DossierBlockTagsProvider(generator, modId);
		providers.put(DossierType.BLOCK_TAGS, blockTagsProvider);
		providers.put(DossierType.ITEM_TAGS, new DossierItemTagsProvider(generator, blockTagsProvider, modId));
		providers.put(DossierType.RECIPES, new DossierRecipesProvider(generator, modId));
		providers.put(DossierType.LOOT_TABLES, new DossierLootTablesProvider(generator, modId));

		providers.forEach((t, provider) -> generator.install(provider));
		DATA_GENERATORS.put(modId, generator);
		DATA_PROVIDERS.put(modId, providers);
	}

	public static DataProvider getProvider(String modId, DossierType type) {
		if (!DATA_PROVIDERS.containsKey(modId)) {
			reloadProviders(modId);
		}
		return DATA_PROVIDERS.get(modId).get(type);
	}

}
