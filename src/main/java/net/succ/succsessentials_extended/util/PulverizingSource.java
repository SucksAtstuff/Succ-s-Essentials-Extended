package net.succ.succsessentials_extended.util;

/**
 * Represents the logical source category of a pulverizing recipe.
 * This is used ONLY for recipe ID generation and readability.
 *
 * It does NOT affect ingredients or logic — purely namespacing.
 */
public enum PulverizingSource {

    ORE("ore"),
    DEEPSLATE("deepslate"),
    RAW("raw"),
    COAL("coal"),
    INGOT("ingot"),
    GEM("gem"),
    DUST("dust"),
    MISC("misc");

    private final String id;

    PulverizingSource(String id) {
        this.id = id;
    }

    /**
     * @return a safe, lowercase string suitable for recipe IDs
     */
    public String id() {
        return id;
    }
}
