package com.terraformersmc.dossier.generator;

import com.terraformersmc.dossier.provider.DossierRecipesProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagKey;

import java.util.function.Consumer;

public abstract class RecipesDossier extends DossierGenerator<DossierRecipesProvider, Consumer<Consumer<RecipeJsonProvider>>> {

    protected InventoryChangedCriterion.Conditions conditionsFrom(ItemConvertible item) {
        return this.conditionsFrom(ItemPredicate.Builder.create().items(item).build());
    }

    protected InventoryChangedCriterion.Conditions conditionsFrom(TagKey<Item> tag) {
        return this.conditionsFrom(this.itemPredicateOf(tag));
    }

    protected ItemPredicate itemPredicateOf(TagKey<Item> tag) {
        return ItemPredicate.Builder.create().tag(tag).build();
    }

    protected InventoryChangedCriterion.Conditions conditionsFrom(ItemPredicate... items) {
        return new InventoryChangedCriterion.Conditions(EntityPredicate.Extended.EMPTY, NumberRange.IntRange.ANY, NumberRange.IntRange.ANY, NumberRange.IntRange.ANY, items);
    }

    @Override
    protected void setProvider(String modId) {
        this.provider = (DossierRecipesProvider) DossierGenerator.getProvider(modId, DossierType.RECIPES);
    }

    protected abstract void addRecipes(Consumer<RecipeJsonProvider> exporter);

    @Override
    public Consumer<Consumer<RecipeJsonProvider>> getCustomFunction() {
        return this::addRecipes;
    }
}
