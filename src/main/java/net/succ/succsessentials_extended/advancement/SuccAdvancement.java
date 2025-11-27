package net.succ.succsessentials_extended.advancement;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.succ.succsessentials_extended.Succsessentials_extended;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * SuccAdvancement is a Create-style wrapper around Minecraft's Advancement system.
 *
 * It does several things:
 *  - Wraps {@link Advancement.Builder} behind a friendly builder API (SuccAdvancement.Builder).
 *  - Handles a "builtin" trigger for simple advancements, or allows external criteria.
 *  - Stores parent/child relations via {@link #after(SuccAdvancement)}.
 *  - Registers itself into {@link AllSuccAdvancements#ENTRIES} so datagen can iterate them all.
 *  - Provides translation keys and language export via {@link #provideLang(BiConsumer)}.
 *
 * Usage pattern is very similar to Create's AllAdvancements:
 *
 *   public static final SuccAdvancement ROOT = SuccAdvancement.create("root", b -> b
 *       .icon(ModItems.ATHERIUM.get())
 *       .title("Succ's Essentials")
 *       .description("Here be gems.")
 *       .awardedForFree()
 *       .special(TaskType.SILENT)
 *   );
 *
 *   public static final SuccAdvancement SAPPHIRE = SuccAdvancement.create("sapphire", b -> b
 *       .icon(ModItems.SAPPHIRE.get())
 *       .title("Blue Sapphire")
 *       .description("Obtain a polished Sapphire.")
 *       .after(ROOT)
 *       .whenIconCollected()
 *   );
 */
public class SuccAdvancement {

    /**
     * Background texture used for the root advancement, similar to Create.BACKGROUND.
     * You can create this texture in your resources at:
     *   resources/assets/succsessentials/textures/gui/advancements.png
     *
     * If it does not exist yet, Minecraft will show a missing-texture placeholder.
     */
    private static final ResourceLocation BACKGROUND =
            ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, "textures/gui/advancements.png");

    /**
     * Base language prefix for advancement titles/descriptions.
     *
     * Example keys:
     *   advancement.succsessentials.sapphire
     *   advancement.succsessentials.sapphire.desc
     */
    private static final String LANG_PREFIX = "advancement." + Succsessentials_extended.MOD_ID + ".";

    /**
     * Optional suffix appended to the description for SECRET-type tasks.
     * This is purely visual and matches Create's behavior.
     */
    private static final String SECRET_SUFFIX = "\n§7(Hidden Advancement)";

    /**
     * Underlying vanilla Advancement.Builder instance.
     * All criteria, display, parent, etc., are configured here.
     */
    private final Advancement.Builder mcBuilder = Advancement.Builder.advancement();

    /**
     * Built-in simple trigger used if this advancement does not declare external criteria explicitly.
     * This allows us to call {@link #awardTo(Player)} at runtime.
     *
     * If external criteria are used (via Builder.externalTrigger / whenXxx), this is null.
     */
    private SuccSimpleTrigger builtinTrigger;

    /**
     * Parent SuccAdvancement in the logical chain.
     * This is wired into the underlying Advancement's parent reference during datagen.
     */
    private SuccAdvancement parent;

    /**
     * Our user-facing builder wrapper around the raw mcBuilder.
     */
    private final Builder createBuilder = new Builder();

    /**
     * Resulting AdvancementHolder from datagen.
     * Stored so that children can use it as a parent.
     */
    AdvancementHolder datagenResult;

    /**
     * The basic ID of this advancement, without the namespace.
     * Example: "sapphire", "root", "malachite_armor_set", etc.
     */
    private final String id;

    /**
     * Human-readable title and description for the advancement.
     * These are written out as language entries using keys generated from {@link #id}.
     */
    private String title;
    private String description;

    /**
     * Create a new SuccAdvancement.
     *
     * @param id logical ID without namespace (e.g. "sapphire", "root").
     * @param configurator a function configuring the fluent Builder.
     */
    public SuccAdvancement(String id, UnaryOperator<Builder> configurator) {
        this.id = id;

        // Apply the configuration to our Builder (icon, title, desc, parent, triggers, etc.)
        configurator.apply(this.createBuilder);

        // If no external criteria are used, we create a built-in simple trigger.
        // This is exactly how Create works: each advancement gets its own simple trigger unless overridden.
        if (!this.createBuilder.externalTrigger) {
            this.builtinTrigger = AllSuccTriggers.addSimple(id + "_builtin");
            // Add a single criterion "0" that uses this builtin trigger.
            this.mcBuilder.addCriterion("0", this.builtinTrigger.createCriterion(this.builtinTrigger.instance()));
        }

        // If this advancement is SECRET, append the secret suffix to the description.
        if (this.createBuilder.type == TaskType.SECRET && this.description != null) {
            this.description = this.description + SECRET_SUFFIX;
        }

        // Register this advancement into the global list so datagen can discover it.
        AllSuccAdvancements.ENTRIES.add(this);
    }

    // ------------------------------------------------------------------------
    // Internal language key helpers
    // ------------------------------------------------------------------------

    /**
     * Title translation key, e.g. "advancement.succsessentials.sapphire".
     */
    private String titleKey() {
        return LANG_PREFIX + this.id;
    }

    /**
     * Description translation key, e.g. "advancement.succsessentials.sapphire.desc".
     */
    private String descriptionKey() {
        return titleKey() + ".desc";
    }

    // ------------------------------------------------------------------------
    // Runtime helpers (checking / awarding)
    // ------------------------------------------------------------------------

    /**
     * Check if this advancement has already been awarded to the given player.
     *
     * This is exactly what Create does: it only makes sense server-side,
     * and for non-server players we assume it's already "done".
     */
    public boolean isAlreadyAwardedTo(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            // Look up the AdvancementHolder from the server's advancement manager.
            ResourceLocation rl = ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, this.id);
            AdvancementHolder holder = serverPlayer.server.getAdvancements().get(rl);

            if (holder == null) {
                // If the advancement isn't even registered, we treat it as "done"
                // so that advancement behaviours don't spam anything.
                return true;
            }

            // Query player's progress on this advancement.
            return serverPlayer.getAdvancements().getOrStartProgress(holder).isDone();
        }

        // On client or non-server side, we never "award" – treat as already done.
        return true;
    }

    /**
     * Award this advancement to the given player using the built-in trigger.
     *
     * This mirrors Create's behavior:
     *  - It only works if this advancement uses the built-in trigger
     *    (i.e. NOT externalTrigger).
     *  - For external-triggered advancements, direct award is unsupported.
     */
    public void awardTo(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            if (this.builtinTrigger == null) {
                throw new UnsupportedOperationException(
                        "Advancement " + this.id + " uses external triggers and cannot be awarded directly."
                );
            }
            this.builtinTrigger.trigger(serverPlayer);
        }
    }

    // ------------------------------------------------------------------------
    // Datagen integration
    // ------------------------------------------------------------------------

    /**
     * Called during datagen to actually convert this SuccAdvancement into a real
     * {@link AdvancementHolder} and pass it to the consumer.
     *
     * This mirrors Create's CreateAdvancement#save(...) method.
     */
    void save(Consumer<AdvancementHolder> consumer, HolderLookup.Provider registries) {
        // If we have a parent logical SuccAdvancement, wire its datagenResult as parent.
        if (this.parent != null) {
            this.mcBuilder.parent(this.parent.datagenResult);
        }

        // If the user specified a late-bound icon function (requires registry access),
        // we evaluate that here and feed the resulting ItemStack into the display.
        if (this.createBuilder.iconProvider != null) {
            this.createBuilder.icon(this.createBuilder.iconProvider.apply(registries));
        }

        // Build the display info: icon, title, description, background, and type.
        this.mcBuilder.display(
                this.createBuilder.icon,
                Component.translatable(this.titleKey()),
                Component.translatable(this.descriptionKey())
                        .withStyle(s -> s.withColor(0xDBA213)), // same color style as Create (approx)
                this.id.equals("root") ? BACKGROUND : null,
                this.createBuilder.type.advancementType,
                this.createBuilder.type.toast,
                this.createBuilder.type.announce,
                this.createBuilder.type.hide
        );

        // Save as a real AdvancementHolder and retain it so children can reference us.
        ResourceLocation rl = ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, this.id);
        this.datagenResult = this.mcBuilder.save(consumer, rl.toString());
    }

    /**
     * Called from lang datagen to emit the advancement's title and description entries.
     * This mirrors Create's CreateAdvancement#provideLang.
     */
    void provideLang(BiConsumer<String, String> langOut) {
        langOut.accept(titleKey(), this.title);
        langOut.accept(descriptionKey(), this.description);
    }

    // ------------------------------------------------------------------------
    // Static helpers & types
    // ------------------------------------------------------------------------

    /**
     * TaskType describes how loud/visible an advancement is.
     * This is taken directly from Create:
     *
     *  SILENT – no toast, no announce, no hide
     *  NORMAL – toast yes, no announce, no hide
     *  NOISY – toast yes, announce yes, no hide
     *  EXPERT – GOAL type, toast+announce, no hide
     *  SECRET – GOAL type, toast+announce, hidden=true
     */
    public enum TaskType {
        SILENT(AdvancementType.TASK, false, false, false),
        NORMAL(AdvancementType.TASK, true, false, false),
        NOISY(AdvancementType.TASK, true, true, false),
        EXPERT(AdvancementType.GOAL, true, true, false),
        SECRET(AdvancementType.GOAL, true, true, true);

        private final AdvancementType advancementType;
        private final boolean toast;
        private final boolean announce;
        private final boolean hide;

        TaskType(AdvancementType type, boolean toast, boolean announce, boolean hide) {
            this.advancementType = type;
            this.toast = toast;
            this.announce = announce;
            this.hide = hide;
        }
    }

    /**
     * Builder is the fluent, user-facing configuration API.
     *
     * This is what you use in AllSuccAdvancements to define each advancement node.
     * It configures:
     *  - task type (normal / noisy / expert / secret)
     *  - parent advancement (via after(...))
     *  - icon (via ItemLike or ItemStack or a function)
     *  - title & description (stored as plain strings and exported to lang)
     *  - which trigger to use (built-in or external)
     */
    public class Builder {

        /** How "loud" and "visible" this advancement is. Defaults to NORMAL. */
        private TaskType type = TaskType.NORMAL;

        /**
         * If true, the advancement uses external criteria and does *not* have a builtin simple trigger.
         * If false, SuccAdvancement will register a {@link SuccSimpleTrigger} automatically.
         */
        private boolean externalTrigger = false;

        /**
         * Simple counter for naming multiple criteria on the same advancement.
         * Each criterion gets a numeric key: "0", "1", "2", ...
         */
        private int keyIndex = 0;

        /**
         * The icon shown for this advancement in the UI.
         * This must be a fully-initialized stack before save() is called.
         */
        private ItemStack icon = ItemStack.EMPTY;

        /**
         * Optional late-bound icon provider that requires access to registries.
         * If non-null, it will be invoked during save() and its result used as {@link #icon}.
         */
        private Function<HolderLookup.Provider, ItemStack> iconProvider = null;

        Builder() {
        }

        // ---------- Task type / special flags ----------

        public Builder special(TaskType type) {
            this.type = type;
            return this;
        }

        // ---------- Parent linking: after(...) ----------

        /**
         * Set the parent SuccAdvancement. The underlying MC advancement will use
         * parent.datagenResult as its parent field.
         */
        public Builder after(SuccAdvancement other) {
            SuccAdvancement.this.parent = other;
            return this;
        }

        // ---------- Icon configuration ----------

        /**
         * Set the icon using an {@link ItemLike} (e.g. Block or Item).
         */
        public Builder icon(ItemLike itemLike) {
            return this.icon(new ItemStack(itemLike));
        }

        /**
         * Set the icon via an already-constructed {@link ItemStack}.
         */
        public Builder icon(ItemStack stack) {
            this.icon = stack;
            return this;
        }

        /**
         * Set the icon using a function that depends on the registry lookup provider.
         * This is mainly useful for fancy items like trimmed armor etc.
         */
        public Builder icon(Function<HolderLookup.Provider, ItemStack> func) {
            this.iconProvider = func;
            return this;
        }

        // ---------- Text configuration ----------

        /**
         * Set the human-readable title string.
         * The actual translation key will be generated automatically.
         */
        public Builder title(String title) {
            SuccAdvancement.this.title = title;
            return this;
        }

        /**
         * Set the human-readable description string.
         * The actual translation key will be generated automatically.
         */
        public Builder description(String description) {
            SuccAdvancement.this.description = description;
            return this;
        }

        // ---------- Built-in trigger helpers ----------

        /**
         * Use a placement trigger: the advancement uses a criterion that
         * fires when the given block is placed.
         *
         * This marks the advancement as using external triggers (no builtin simple trigger).
         */
        public Builder whenBlockPlaced(Block block) {
            return this.externalTrigger(ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(block));
        }

        /**
         * Use an InventoryChangeTrigger that fires when the icon item itself is obtained.
         */
        public Builder whenIconCollected() {
            return this.externalTrigger(
                    InventoryChangeTrigger.TriggerInstance.hasItems(icon.getItem())
            );
        }

        /**
         * Use an InventoryChangeTrigger that fires when a specific item is obtained.
         */
        public Builder whenItemCollected(ItemLike itemProvider) {
            return this.externalTrigger(
                    InventoryChangeTrigger.TriggerInstance.hasItems(itemProvider)
            );
        }

        /**
         * Use an InventoryChangeTrigger that fires when *any* item in the given tag is obtained.
         */
        public Builder whenItemCollected(TagKey<Item> tag) {
            ItemPredicate predicate = ItemPredicate.Builder.item().of(tag).build();
            return this.externalTrigger(
                    InventoryChangeTrigger.TriggerInstance.hasItems(predicate)
            );
        }

        /**
         * Set up a criterion that is "awarded for free" – can be granted without any item.
         * This is equivalent to Create's awardedForFree().
         */
        public Builder awardedForFree() {
            // InventoryChangeTrigger with an empty item set basically "always true".
            return this.externalTrigger(
                    InventoryChangeTrigger.TriggerInstance.hasItems(new ItemPredicate[]{})
            );
        }

        /**
         * Attach an arbitrary external trigger criterion.
         *
         * This:
         *  - marks the advancement as externalTrigger=true (so no builtin SuccSimpleTrigger is created)
         *  - adds the criterion with a numeric key ("0", "1", ...)
         */
        public Builder externalTrigger(Criterion<?> trigger) {
            SuccAdvancement.this.mcBuilder.addCriterion(String.valueOf(keyIndex), trigger);
            this.externalTrigger = true;
            keyIndex++;
            return this;
        }
    }

    // ------------------------------------------------------------------------
    // Static helper to construct new SuccAdvancement instances
    // ------------------------------------------------------------------------

    /**
     * Static factory helper to mirror Create's `create(id, b -> ...)`.
     *
     * Example:
     *
     *   public static final SuccAdvancement ROOT =
     *       SuccAdvancement.create("root", b -> b
     *           .icon(ModItems.ATHERIUM.get())
     *           .title("Succ's Essentials")
     *           .description("Begin your journey")
     *           .awardedForFree()
     *           .special(TaskType.SILENT)
     *       );
     */
    public static SuccAdvancement create(String id, UnaryOperator<Builder> builder) {
        return new SuccAdvancement(id, builder);
    }
}
