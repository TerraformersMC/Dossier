package com.terraformersmc.dossier.provider;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.server.AbstractTagProvider;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.nio.file.Path;
import java.util.function.Consumer;

public class DossierBlockTagsProvider extends AbstractTagProvider<Block> implements Consumer<Runnable> {

	private Runnable onConfigure;
	private final String modId;

	public DossierBlockTagsProvider(DataGenerator generator, String modId) {
		super(generator, Registry.BLOCK);
		this.modId = modId;
	}

	@Override
	protected void configure() {
		this.onConfigure.run();
	}

	@Override
	public void accept(Runnable onConfigure) {
		this.onConfigure = onConfigure;
	}

	@Override
	public ObjectBuilder<Block> getOrCreateTagBuilder(TagKey<Block> identified) {
		return super.getOrCreateTagBuilder(identified);
	}

	@Override
	protected Path getOutput(Identifier identifier) {
		return this.root.getOutput().resolve("data/" + identifier.getNamespace() + "/tags/blocks/" + identifier.getPath() + ".json");
	}

	@Override
	public Tag.Builder getTagBuilder(TagKey<Block> identified) {
		return super.getTagBuilder(identified);
	}

	@Override
	public String getName() {
		return "Dossier Block Tags Generator: " + modId;
	}
}
