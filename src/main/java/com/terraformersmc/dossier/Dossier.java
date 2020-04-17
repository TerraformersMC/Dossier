package com.terraformersmc.dossier;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.terraformersmc.dossier.generator.DossierGenerator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.LiteralText;

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
		CommandRegistry.INSTANCE.register(false, dispatcher -> dispatcher.register(CommandManager.literal("dossier").then(CommandManager.literal("generate").then(CommandManager.argument("modId", StringArgumentType.string()).suggests((context, builder) -> {
			Dossier.PROVIDERS.keySet().forEach(builder::suggest);
			return builder.buildFuture();
		}).executes(context -> {
			String modId = StringArgumentType.getString(context, "modId");
			DossierGenerator.reloadProviders(modId);
			Dossier.generateFor(modId);
			context.getSource().sendFeedback(new LiteralText("Data generated for " + modId), false);
			return 0;
		})))));
	}

	public static void generateFor(String modId) {
		DossierProvider provider = PROVIDERS.get(modId);
		if (provider == null) {
			throw new RuntimeException("Cannot generate data for mod: '" + modId + "'; no DossierProvider found.");
		}
		DossierGenerator.generateData(modId, provider.isEnabled(), provider.createDossiers());
	}
}
