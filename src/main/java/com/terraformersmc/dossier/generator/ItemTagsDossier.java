package com.terraformersmc.dossier.generator;

import com.terraformersmc.dossier.provider.DossierItemTagsProvider;
import com.terraformersmc.dossier.util.CommonValues;
import com.terraformersmc.dossier.util.TransformerFunction;
import net.minecraft.block.Block;
import net.minecraft.data.server.AbstractTagProvider;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.ArrayUtils;

public abstract class ItemTagsDossier extends DossierGenerator<DossierItemTagsProvider, Runnable> implements CommonValues {

    protected AbstractTagProvider.ObjectBuilder<Item> get(TagKey<Item> tag) {
        return this.provider.getOrCreateTagBuilder(tag);
    }

    protected void copyFromBlock(TagKey<Item> tag, TagKey<Block> blockTag) {
        this.provider.copy(blockTag, tag);
    }

    protected AbstractTagProvider.ObjectBuilder<Item> add(TagKey<Item> tag, Item... items) {
        return this.get(tag).add(items);
    }

    protected AbstractTagProvider.ObjectBuilder<Item> add(TagKey<Item> tag, TagKey<Item>... tags) {
        AbstractTagProvider.ObjectBuilder<Item> builder = this.get(tag);
        for (TagKey<Item> itemTag : tags) {
            builder.addTag(itemTag);
        }
        return builder;
    }

    protected AbstractTagProvider.ObjectBuilder<Item> addTransformed(TagKey<Item> tag, TransformerFunction<String, String> transformer, String namespace, String pathTemplate, String... args) {
        return this.add(tag, transformer.apply(pathTemplate, args).stream().map(transformeedPath -> Registry.ITEM.get(new Identifier(namespace, transformeedPath))).toArray(Item[]::new));
    }

    protected AbstractTagProvider.ObjectBuilder<Item> addReplaceTransformed(TagKey<Item> tag, String namespace, String pathTemplate, String key, String... values) {
        return this.addTransformed(tag, TransformerFunction.REPLACE_TRANSFORMER, namespace, pathTemplate, ArrayUtils.add(values, 0, key));
    }

    @Override
    protected void setProvider(String modId) {
        this.provider = (DossierItemTagsProvider) DossierGenerator.getProvider(modId, DossierType.ITEM_TAGS);
    }

    protected abstract void addItemTags();

    @Override
    public Runnable getCustomFunction() {
        return this::addItemTags;
    }
}
