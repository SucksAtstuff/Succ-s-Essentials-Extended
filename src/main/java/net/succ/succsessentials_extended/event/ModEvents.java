package net.succ.succsessentials_extended.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingBreatheEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.succ.succsessentials_extended.Succsessentials_extended;
import net.succ.succsessentials_extended.recipe.ModRecipes;
import net.succ.succsessentials_extended.recipe.hammering.HammerRecipe;
import net.succ.succsessentials_extended.recipe.hammering.HammerRecipeInput;
import net.succ.succsessentials_extended.recipe.wirecutting.WireCutterRecipe;
import net.succ.succsessentials_extended.recipe.wirecutting.WireCutterRecipeInput;
import net.succ.succsessentials_extended.util.ModTags;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = Succsessentials_extended.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    /* =====================================================================
     *                           HAMMERING (INGOT → PLATE)
     * ===================================================================== */

    @SubscribeEvent
    public static void onRightClickHammer(PlayerInteractEvent.RightClickItem event) {

        // Safety: require sneaking
        if (!event.getEntity().isShiftKeyDown()) return;

        ItemStack usedStack = event.getItemStack();
        ItemStack otherHand =
                event.getHand() == InteractionHand.MAIN_HAND
                        ? event.getEntity().getOffhandItem()
                        : event.getEntity().getMainHandItem();

        // Determine which stack is the hammer
        ItemStack hammer;
        ItemStack input;

        if (usedStack.is(ModTags.Items.HAMMERS)) {
            hammer = usedStack;
            input = otherHand;
        } else if (otherHand.is(ModTags.Items.HAMMERS)) {
            hammer = otherHand;
            input = usedStack;
        } else {
            return;
        }

        Level level = event.getLevel();
        if (level.isClientSide) return;

        HammerRecipeInput recipeInput =
                new HammerRecipeInput(input, hammer);

        level.getRecipeManager()
                .getRecipeFor(ModRecipes.HAMMERING_TYPE.get(), recipeInput, level)
                .ifPresent(holder -> {

                    HammerRecipe recipe = holder.value();

                    // Consume ingot
                    input.shrink(1);

                    // Damage hammer in correct slot
                    EquipmentSlot slot =
                            hammer == event.getEntity().getMainHandItem()
                                    ? EquipmentSlot.MAINHAND
                                    : EquipmentSlot.OFFHAND;

                    hammer.hurtAndBreak(
                            recipe.durabilityCost(),
                            event.getEntity(),
                            slot
                    );

                    // Give plate
                    event.getEntity().addItem(recipe.output().copy());

                    event.setCanceled(true);
                });
    }

    /* =====================================================================
     *                           WIRE CUTTING (PLATE → WIRE)
     * ===================================================================== */

    @SubscribeEvent
    public static void onRightClickWireCutter(PlayerInteractEvent.RightClickItem event) {

        // Safety: require sneaking
        if (!event.getEntity().isShiftKeyDown()) return;

        ItemStack usedStack = event.getItemStack();
        ItemStack otherHand =
                event.getHand() == InteractionHand.MAIN_HAND
                        ? event.getEntity().getOffhandItem()
                        : event.getEntity().getMainHandItem();

        ItemStack cutter;
        ItemStack input;

        if (usedStack.is(ModTags.Items.WIRE_CUTTERS)) {
            cutter = usedStack;
            input = otherHand;
        } else if (otherHand.is(ModTags.Items.WIRE_CUTTERS)) {
            cutter = otherHand;
            input = usedStack;
        } else {
            return;
        }

        Level level = event.getLevel();
        if (level.isClientSide) return;

        WireCutterRecipeInput recipeInput =
                new WireCutterRecipeInput(input, cutter);

        level.getRecipeManager()
                .getRecipeFor(ModRecipes.WIRE_CUTTING_TYPE.get(), recipeInput, level)
                .ifPresent(holder -> {

                    WireCutterRecipe recipe = holder.value();

                    // Consume plate
                    input.shrink(1);

                    // Damage cutter
                    EquipmentSlot slot =
                            cutter == event.getEntity().getMainHandItem()
                                    ? EquipmentSlot.MAINHAND
                                    : EquipmentSlot.OFFHAND;

                    cutter.hurtAndBreak(
                            recipe.durabilityCost(),
                            event.getEntity(),
                            slot
                    );

                    // Give wire
                    event.getEntity().addItem(recipe.output().copy());

                    event.setCanceled(true);
                });
    }

    /* =====================================================================
     *                           OTHER EVENTS (UNCHANGED)
     * ===================================================================== */

    @SubscribeEvent
    public static void onLivingBreathe(LivingBreatheEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) return;
    }

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {}

    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent event) {}
}
