package org.julioju.lbstrawmap.domain.enumeration;

/**
 * The YesNoPartial enumeration.
 */
public enum YesNoPartial {
    OUI("Oui"),
    NON("Non"),
    PARTIEL("Partiel");

    private final String value;

    YesNoPartial(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
