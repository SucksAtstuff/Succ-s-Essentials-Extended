// ModStructureTagProvider.java
package net.succ.succs_essentials_extended.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succs_essentials_extended.Succs_essentials_extended;
import net.succ.succs_essentials_extended.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModStructureTagProvider extends TagsProvider<Structure> {
    public ModStructureTagProvider(PackOutput output,
                                   CompletableFuture<HolderLookup.Provider> lookupProvider,
                                   @Nullable ExistingFileHelper fileHelper) {
        super(output, Registries.STRUCTURE, lookupProvider, Succs_essentials_extended.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Succs_essentials_extended.MOD_ID, path);
    }


    @Override
    public String getName() {
        return "Structure Tags: " + Succs_essentials_extended.MOD_ID;
    }
}
