package net.succ.succsessentials_extended.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.block.entity.ModBlockEntities;
import net.succ.succsessentials_extended.block.entity.custom.*;
import net.succ.succsessentials_extended.item.ModItems;

@EventBusSubscriber(modid = Succsessentials_extended.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModBusEvents {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.COAL_GENERATOR_BE.get(), CoalGeneratorBlockEntity::getEnergyStorage);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.ALLOY_FORGER_BE.get(), AlloyForgerBlockEntity::getEnergyStorage);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.ELECTRIC_FURNACE_BE.get(), ElectricFurnaceBlockEntity::getEnergyStorage);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.INFUSER_BE.get(), InfuserBlockEntity::getEnergyStorage);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.PULVERIZER_BE.get(), PulverizerBlockEntity::getEnergyStorage);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.BIO_FUEL_GENERATOR_BE.get(), BiofuelGeneratorBlockEntity::getEnergyStorage);


    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){

    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){



    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {

    }
}

