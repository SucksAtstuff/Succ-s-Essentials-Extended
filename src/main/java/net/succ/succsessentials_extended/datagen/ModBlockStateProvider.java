package net.succ.succsessentials_extended.datagen;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.ModBlocks;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Succsessentials_extended.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Registering ingot blocks with their respective item models
        blockWithItem(ModBlocks.CHROMIUM_BLOCK);
        blockWithItem(ModBlocks.TITANIUM_BLOCK);
        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.TITA_CHROME_BLOCK);
        blockWithItem(ModBlocks.BRONZE_BLOCK);
        blockWithItem(ModBlocks.TIN_BLOCK);
        blockWithItem(ModBlocks.BRASS_BLOCK);
        blockWithItem(ModBlocks.ELECTRUM_BLOCK);
        blockWithItem(ModBlocks.INVAR_BLOCK);
        blockWithItem(ModBlocks.CONSTANTAN_BLOCK);
        blockWithItem(ModBlocks.TUNGSTEN_BLOCK);
        blockWithItem(ModBlocks.COBALT_BLOCK);
        blockWithItem(ModBlocks.OSMIUM_BLOCK);
        blockWithItem(ModBlocks.ZINC_BLOCK);

        blockWithItem(ModBlocks.RAW_CHROMIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_TITANIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_TIN_BLOCK);
        blockWithItem(ModBlocks.RAW_TUNGSTEN_BLOCK);
        blockWithItem(ModBlocks.RAW_COBALT_BLOCK);
        blockWithItem(ModBlocks.RAW_OSMIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_ZINC_BLOCK);

        // Registering ingot ores with their respective item models
        simpleBlockWithItem(ModBlocks.CHROMIUM_ORE.get(), cubeAll(ModBlocks.CHROMIUM_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get()));
        simpleBlockWithItem(ModBlocks.TITANIUM_ORE.get(), cubeAll(ModBlocks.TITANIUM_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_TITANIUM_ORE.get()));
        simpleBlockWithItem(ModBlocks.TIN_ORE.get(), cubeAll(ModBlocks.TIN_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_TIN_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_TIN_ORE.get()));
        simpleBlockWithItem(ModBlocks.TUNGSTEN_ORE.get(), cubeAll(ModBlocks.TUNGSTEN_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get()));
        simpleBlockWithItem(ModBlocks.COBALT_ORE.get(), cubeAll(ModBlocks.COBALT_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_COBALT_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_COBALT_ORE.get()));
        simpleBlockWithItem(ModBlocks.OSMIUM_ORE.get(), cubeAll(ModBlocks.OSMIUM_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_OSMIUM_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_OSMIUM_ORE.get()));
        simpleBlockWithItem(ModBlocks.ZINC_ORE.get(), cubeAll(ModBlocks.ZINC_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_ZINC_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_ZINC_ORE.get()));

        panelBlock(ModBlocks.PANEL_BLOCK);

        litOrientableBlock(ModBlocks.ALLOY_FORGER, "alloy_forger");
        litOrientableBlock(ModBlocks.ELECTRIC_FURNACE, "electric_furnace");
        litOrientableBlock(ModBlocks.COAL_GENERATOR, "coal_generator");
        litOrientableBlock(ModBlocks.INFUSER, "infuser");
        litOrientableBlock(ModBlocks.PULVERIZER, "pulverizer");
        litOrientableBlock(ModBlocks.BIO_FUEL_GENERATOR, "bio_fuel_generator");

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

    private void litOrientableBlock(DeferredBlock<Block> block, String textureDir) {

        // Build texture paths dynamically from the folder name
        ResourceLocation side     = modLoc("block/" + textureDir + "/" + textureDir + "_side");
        ResourceLocation top      = modLoc("block/" + textureDir + "/" + textureDir + "_top");
        ResourceLocation front    = modLoc("block/" + textureDir + "/" + textureDir + "_front");
        ResourceLocation frontOn  = modLoc("block/" + textureDir + "/" + textureDir + "_front_on");

        // Unlit model
        ModelFile unlit = models().orientable(
                block.getId().getPath(),
                side,
                front,
                top
        );

        // Lit model
        ModelFile lit = models().orientable(
                block.getId().getPath() + "_on",
                side,
                frontOn,
                top
        );

        // Blockstate logic (rotation + lit switch)
        getVariantBuilder(block.get())
                .forAllStates(state -> {

                    Direction facing = state.getValue(HorizontalDirectionalBlock.FACING);
                    boolean litState = state.getValue(BlockStateProperties.LIT);

                    int rotationY = switch (facing) {
                        case SOUTH -> 180;
                        case WEST  -> 270;
                        case EAST  -> 90;
                        default    -> 0; // NORTH
                    };

                    return ConfiguredModel.builder()
                            .modelFile(litState ? lit : unlit)
                            .rotationY(rotationY)
                            .build();
                });

        // Item model always uses unlit variant
        simpleBlockItem(block.get(), unlit);
    }

    private void panelBlock(DeferredBlock<Block> block) {

        ResourceLocation side = blockTexture(block.get());
        ResourceLocation top  = modLoc("block/alloy_forger/alloy_forger_top");

        ModelFile model = models().cubeBottomTop(
                block.getId().getPath(),
                side,
                top,
                top
        );

        simpleBlockWithItem(block.get(), model);
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
