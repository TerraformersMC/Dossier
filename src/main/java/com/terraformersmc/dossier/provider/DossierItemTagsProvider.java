package com.terraformersmc.dossier.provider;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.server.AbstractTagProvider;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.function.Function;

public class DossierItemTagsProvider extends AbstractTagProvider<Item> implements Consumer<Runnable> {

	private final Function<Tag.Identified<Block>, Tag.Builder> tagCopier;
	private final String modId;
	private Runnable onConfigure;

	public DossierItemTagsProvider(DataGenerator generator, DossierBlockTagsProvider blockTagsProvider, String modId) {
		super(generator, Registry.ITEM);
		this.tagCopier = blockTagsProvider::getTagBuilder;
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
	public ObjectBuilder<Item> getOrCreateTagBuilder(Tag.Identified<Item> tag) {
		return super.getOrCreateTagBuilder(tag);
	}

	public void copy(Tag.Identified<Block> identified, Tag.Identified<Item> identified2) {
		Tag.Builder builder = this.getTagBuilder(identified2);
		Tag.Builder builder2 = this.tagCopier.apply(identified);
		builder2.streamEntries().forEach(builder::add);
	}

	@Override
	protected Path getOutput(Identifier identifier) {
		return this.root.getOutput().resolve("data/" + identifier.getNamespace() + "/tags/items/" + identifier.getPath() + ".json");
	}

	@Override
	public String getName() {
		return "Dossier Item Tags Generator: " + modId;
	}
}
