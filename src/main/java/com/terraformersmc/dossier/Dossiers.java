package com.terraformersmc.dossier;

import com.terraformersmc.dossier.generator.BlockTagsDossier;
import com.terraformersmc.dossier.generator.ItemTagsDossier;
import com.terraformersmc.dossier.generator.LootTablesDossier;
import com.terraformersmc.dossier.generator.RecipesDossier;

import java.util.LinkedList;
import java.util.List;

public class Dossiers {
    public List<ItemTagsDossier> itemTagsDossiers = new LinkedList<>();
    public List<BlockTagsDossier> blockTagsDossiers = new LinkedList<>();
    public List<RecipesDossier> recipesDossiers = new LinkedList<>();
    public List<LootTablesDossier> lootTablesDossiers = new LinkedList<>();

    private Dossiers() {
    }

    public static Dossiers builder() {
        return new Dossiers();
    }

    public Dossiers add(ItemTagsDossier dossier) {
        this.itemTagsDossiers.add(dossier);
        return this;
    }

    public Dossiers add(BlockTagsDossier dossier) {
        this.blockTagsDossiers.add(dossier);
        return this;
    }

    public Dossiers add(RecipesDossier dossier) {
        this.recipesDossiers.add(dossier);
        return this;
    }

    public Dossiers add(LootTablesDossier dossier) {
        this.lootTablesDossiers.add(dossier);
        return this;
    }
}
