package net.succ.succsessentials_extended.advancement;

import java.util.LinkedList;
import java.util.List;

/**
 * Registration holder for all custom advancement triggers.
 */
public class AllSuccTriggers {

    private static final List<CriterionTriggerBase<?>> TRIGGERS = new LinkedList<>();

    /** Add a new simple trigger. */
    public static SuccSimpleTrigger addSimple(String id) {
        var t = new SuccSimpleTrigger(id);
        TRIGGERS.add(t);
        return t;
    }

    /** Called during mod loading to register every trigger. */
    public static void register() {
        TRIGGERS.forEach(trigger ->
                net.minecraft.core.Registry.register(
                        net.minecraft.core.registries.BuiltInRegistries.TRIGGER_TYPES,
                        trigger.getId(),
                        trigger
                )
        );
    }
}
