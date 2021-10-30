package org.lbstraw.map.domain.enumeration;

/**
 * The YesNoPartial enumeration.
 */
public enum YesNoPartial {
    /**
     *
     *       * Oui
     *
     */
    OUI("Oui"),
    /**
     *
     *       * Non
     *
     */
    NON("Non"),
    /**
     *
     *       * Partiel
     *
     */
    PARTIEL("Partiel");

    private final String value;

    YesNoPartial(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
