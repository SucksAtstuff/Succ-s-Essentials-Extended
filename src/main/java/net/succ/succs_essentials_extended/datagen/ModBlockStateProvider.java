package net.succ.succs_essentials_extended.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.succ.succs_essentials_extended.Succs_essentials_extended;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Succs_essentials_extended.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];

        return models;
    }

    private void leavesBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(),
                models().singleTexture(
                        BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(),
                        ResourceLocation.parse("minecraft:block/leaves"),
                        "all",
                        blockTexture(deferredBlock.get())
                ).renderType("cutout"));
    }


    private void saplingBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlock(deferredBlock.get(), models().cross(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("succsessentials:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItemOther(DeferredBlock<Block> deferredBlock, String appendix){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("succsessentials:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private ModelFile vineFaceModel(String face) {
        return models()
                .withExistingParent("mycelial_sporewood_vine_" + face, mcLoc("block/vine"))
                .texture("vine", modLoc("block/mycelial_sporewood_vine"))
                .renderType("cutout");
    }

    private void fungusBlock(DeferredBlock<Block> block) {
        simpleBlock(
                block.get(),
                models().cross(
                        BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                        blockTexture(block.get())
                ).renderType("cutout")
        );

        simpleBlockItem(
                block.get(),
                new ModelFile.UncheckedModelFile("succsessentials:block/" + block.getId().getPath())
        );
    }
}
