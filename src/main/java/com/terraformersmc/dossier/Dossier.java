package com.terraformersmc.dossier;

import com.terraformersmc.dossier.command.DossierCommand;
import com.terraformersmc.dossier.generator.DossierGenerator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.util.HashMap;
import java.util.Map;

public class Dossier implements ModInitializer {

	public static final Map<String, DossierProvider> PROVIDERS = new HashMap<>();

	@Override
	public void onInitialize() {
		if (!FabricLoader.getInstance().isDevelopmentEnvironment()) {
			throw new RuntimeException("Dossier should not be loaded in a production environment!");
		}
		FabricLoader.getInstance().getEntrypointContainers("dossier", DossierProvider.class).forEach(container -> PROVIDERS.put(container.getProvider().getMetadata().getId(), container.getEntrypoint()));
		DossierCommand.register();
	}

	public static void generateFor(String modId) {
		DossierProvider provider = PROVIDERS.get(modId);
		if (provider == null) {
			throw new RuntimeException("Cannot generate data for mod: '" + modId + "'; no DossierProvider found.");
		}
		DossierGenerator.generateData(modId, provider.isEnabled(), provider.createDossiers());
	}
}
