package org.gwtproject.i18n.client;

/**
 * The interface that plural rules must implement.  Implementations of
 * this interface will be used both at compile time (pluralForms) and
 * at run time (select), so implementations must be both translatable
 * and not reference JSNI methods.
 */
public interface PluralRule {

    /**
     * Information about the plural forms supported by this rule which
     * will be used during code generation and by tools to provide
     * information to translators.
     */
    public static class PluralForm {
        private final String name;
        private final String description;
        private final boolean noWarn;

        /**
         * Create the plural form.
         *
         * @param name
         * @param description
         */
        public PluralForm(String name, String description) {
            this(name, description, false);
        }

        /**
         * Create the plural form.
         *
         * @param name
         * @param description
         * @param noWarn if true, do not warn if this form is missing from a
         *     translation.  This is used for those cases where a plural form
         *     is defined for a language, but is very rarely used.
         */
        public PluralForm(String name, String description, boolean noWarn) {
            this.name = name;
            this.description = description;
            this.noWarn = noWarn;
        }

        /**
         * Returns the description.
         */
        public String getDescription() {
            return description;
        }

        /**
         * Returns the name.
         */
        public String getName() {
            return name;
        }

        /**
         * Returns true if the generator should warn if this plural form is not
         * present.
         */
        public boolean getWarnIfMissing() {
            return !noWarn;
        }
    }

    /**
     * Returns the list of values which are valid for this rule.  The
     * default or "other" plural form must be first in the list with
     * an index of 0 -- this form will be used if no other form applies
     * and is also mapped to the default text for a given message.
     *
     * This method will be executed at compile time and may not contain
     * any references, even indirectly, to JSNI methods.
     */
    PluralForm[] pluralForms();

    /**
     * Returns the plural form appropriate for this count.
     *
     * This method will be executed at runtime, so must be translatable.
     *
     * @param n count of items to choose plural form for
     * @return the plural form to use (must be a valid index
     *     into the array returned by pluralForms).
     */
    int select(int n);
}
