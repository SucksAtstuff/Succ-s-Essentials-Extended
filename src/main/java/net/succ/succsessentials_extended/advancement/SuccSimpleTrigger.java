package net.succ.succsessentials_extended.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Simple trigger identical to Create's SimpleCreateTrigger.
 */
public class SuccSimpleTrigger extends CriterionTriggerBase<SuccSimpleTrigger.Instance> {

    public SuccSimpleTrigger(String id) {
        super(id);
    }

    /**
     * Fire this trigger for the given player.
     */
    public void trigger(ServerPlayer player) {
        super.trigger(player, null);
    }

    /** Provide a new instance used for advancement criterion. */
    public Instance instance() {
        return new Instance();
    }

    /** Codec required by NeoForge for datapack compatibility. */
    @Override
    public Codec<Instance> codec() {
        return Instance.CODEC;
    }

    // ==================================================================
    // Instance
    // ==================================================================
    public static class Instance extends CriterionTriggerBase.Instance {

        public static final Codec<Instance> CODEC =
                RecordCodecBuilder.create(inst -> inst.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player")
                                .forGetter(Instance::player)
                ).apply(inst, Instance::new));

        private final Optional<ContextAwarePredicate> player;

        public Instance() { this.player = Optional.empty(); }

        public Instance(Optional<ContextAwarePredicate> player) {
            this.player = player;
        }

        @Override
        protected boolean test(@Nullable List<Supplier<Object>> suppliers) {
            return true; // always fires
        }

        public Optional<ContextAwarePredicate> player() {
            return player;
        }
    }
}
