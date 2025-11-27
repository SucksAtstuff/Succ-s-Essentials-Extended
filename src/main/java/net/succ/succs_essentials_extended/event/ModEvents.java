package net.succ.succs_essentials_extended.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingBreatheEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.succ.succs_essentials_extended.Succs_essentials_extended;
import net.succ.succs_essentials_extended.block.ModBlocks;
import net.succ.succs_essentials_extended.effect.ModEffects;
import net.succ.succs_essentials_extended.item.ModItems;
import net.succ.succs_essentials_extended.potion.ModPotions;
import net.succ.succs_essentials_extended.villager.ModVillagers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Succs_essentials_extended.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();



    @SubscribeEvent
    public static void onLivingBreathe(LivingBreatheEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) return;

    }

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event){
        PotionBrewing.Builder builder = event.getBuilder();


    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){

    }

    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent event){

    }
}
