package com.terraformersmc.dossier.generator;

import com.terraformersmc.dossier.provider.DossierBlockTagsProvider;
import com.terraformersmc.dossier.util.CommonValues;
import com.terraformersmc.dossier.util.TransformerFunction;
import net.minecraft.block.Block;
import net.minecraft.data.server.AbstractTagProvider;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.ArrayUtils;

public abstract class BlockTagsDossier extends DossierGenerator<DossierBlockTagsProvider, Runnable> implements CommonValues {

	protected AbstractTagProvider.ObjectBuilder<Block> get(Tag.Identified<Block> tag) {
		return this.provider.getOrCreateTagBuilder(tag);
	}

	protected AbstractTagProvider.ObjectBuilder<Block> add(Tag.Identified<Block> tag, Block... blocks) {
		AbstractTagProvider.ObjectBuilder<Block> builder = this.provider.getOrCreateTagBuilder(tag);
		return builder.add(blocks);
	}

	protected AbstractTagProvider.ObjectBuilder<Block> add(Tag.Identified<Block> tag, Tag.Identified<Block>... tags) {
		AbstractTagProvider.ObjectBuilder<Block> builder = this.get(tag);
		for (Tag.Identified<Block> blockTag : tags) {
			builder.addTag(blockTag);
		}
		return builder;
	}

	protected AbstractTagProvider.ObjectBuilder<Block> addTransformed(Tag.Identified<Block> tag, TransformerFunction<String, String> transformer, String namespace, String pathTemplate, String... args) {
		return this.add(tag, transformer.apply(pathTemplate, args).stream().map(transformeedPath -> Registry.BLOCK.get(new Identifier(namespace, transformeedPath))).toArray(Block[]::new));
	}

	protected AbstractTagProvider.ObjectBuilder<Block> addReplaceTransformed(Tag.Identified<Block> tag, String namespace, String pathTemplate, String key, String... values) {
		return this.addTransformed(tag, TransformerFunction.REPLACE_TRANSFORMER, namespace, pathTemplate, ArrayUtils.add(values, 0, key));
	}

	@Override
	protected void setProvider(String modId) {
		this.provider = (DossierBlockTagsProvider) DossierGenerator.getProvider(modId, DossierType.BLOCK_TAGS);
	}

	protected abstract void addBlockTags();

	@Override
	public Runnable getCustomFunction() {
		return this::addBlockTags;
	}
}
