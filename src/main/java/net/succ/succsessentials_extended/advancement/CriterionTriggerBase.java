package net.succ.succsessentials_extended.advancement;

import com.google.common.collect.Maps;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.succ.succsessentials_extended.Succsessentials_extended;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Supplier;

/**
 * Core trigger base system exactly like Create, updated for NeoForge 1.21.
 *
 * This class:
 *  - Stores the trigger ID
 *  - Manages listeners per player
 *  - Fires listeners using Create-style Instance#test(...)
 */
public abstract class CriterionTriggerBase<T extends CriterionTriggerBase.Instance>
        implements CriterionTrigger<T> {

    /** Unique trigger ID, fully namespaced. */
    protected final ResourceLocation id;

    /** All listeners per-player. */
    protected final Map<PlayerAdvancements, Set<Listener<T>>> listeners = Maps.newHashMap();

    public CriterionTriggerBase(String id) {
        this.id = ResourceLocation.fromNamespaceAndPath(Succsessentials_extended.MOD_ID, id);
    }

    /** NeoForge 1.21: CriterionTrigger does NOT define getId(), so no @Override. */
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public void addPlayerListener(PlayerAdvancements adv, Listener<T> listener) {
        listeners.computeIfAbsent(adv, k -> new HashSet<>()).add(listener);
    }

    @Override
    public void removePlayerListener(PlayerAdvancements adv, Listener<T> listener) {
        Set<Listener<T>> set = listeners.get(adv);
        if (set != null) {
            set.remove(listener);
            if (set.isEmpty()) listeners.remove(adv);
        }
    }

    @Override
    public void removePlayerListeners(PlayerAdvancements adv) {
        listeners.remove(adv);
    }

    /**
     * Create-style trigger firing pipeline.
     */
    protected void trigger(ServerPlayer player, @Nullable List<Supplier<Object>> suppliers) {
        var adv = player.getAdvancements();
        var set = listeners.get(adv);

        if (set == null || set.isEmpty()) return;

        List<Listener<T>> passed = new ArrayList<>();

        for (Listener<T> listener : set) {
            T inst = listener.trigger();
            if (inst.test(suppliers)) passed.add(listener);
        }

        for (Listener<T> listener : passed)
            listener.run(adv);
    }

    // -----------------------------------------------------------------
    // Instance class used for Create-style custom testing
    // -----------------------------------------------------------------
    public abstract static class Instance implements SimpleCriterionTrigger.SimpleInstance {

        /**
         * Create-style test function.
         * NeoForge doesn't use this signature anymore, but OUR trigger pipeline does.
         */
        protected abstract boolean test(@Nullable List<Supplier<Object>> suppliers);
    }
}
