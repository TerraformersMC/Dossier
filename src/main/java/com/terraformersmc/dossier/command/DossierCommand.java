package com.terraformersmc.dossier.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.terraformersmc.dossier.Dossier;
import com.terraformersmc.dossier.generator.DossierGenerator;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import java.util.concurrent.CompletableFuture;

public class DossierCommand {
	public static void register() {
		CommandRegistry.INSTANCE.register(false, dispatcher -> {
			LiteralCommandNode<ServerCommandSource> dossierNode = CommandManager
					.literal("dossier")
					.build();

			LiteralCommandNode<ServerCommandSource> generateNode = CommandManager
					.literal("generate")
					.build();

			ArgumentCommandNode<ServerCommandSource, String> modIdNode = CommandManager
					.argument("modId", StringArgumentType.string())
					.suggests(DossierCommand::suggestions)
					.executes(DossierCommand::generate)
					.build();

			dispatcher.getRoot().addChild(dossierNode);
			dossierNode.addChild(generateNode);
			generateNode.addChild(modIdNode);
		});
	}

	public static CompletableFuture<Suggestions> suggestions(final CommandContext<ServerCommandSource> context, final SuggestionsBuilder builder) {
		Dossier.PROVIDERS.keySet().forEach(modId -> {
			if (Dossier.PROVIDERS.get(modId).isEnabled()) {
				builder.suggest(modId);
			}
		});
		return builder.buildFuture();
	}

	public static int generate(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		String modId = StringArgumentType.getString(context, "modId");
		DossierGenerator.reloadProviders(modId);
		Dossier.generateFor(modId);
		context.getSource().sendFeedback(new LiteralText("Data generated for " + modId), false);
		return 1;
	}
}
